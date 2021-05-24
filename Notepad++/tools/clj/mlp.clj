; # Markdown literary programming for Clojure

; Author: Lin Pengcheng

; My Blog: [The Grand Unified Programming Theory: The Pure Function Pipeline Data Flow with Principle-based Warehouse/Workshop Model](https://github.com/linpengcheng/PurefunctionPipelineDataflow)

; Reference: [Markdown Literary programming that don't break the syntax of any programming language](https://github.com/linpengcheng/PurefunctionPipelineDataflow/blob/master/doc/markdown_literary_programming.md)

; ## Config

(def npp-tools "C:/Notepad++/tools") 

; md2html-method: 
; - :cmark-gfm     (support button, don't support TOC)
; - :MultiMarkdown (support TOC, button display fail)
; - :pandoc (Don't use, Notepad++ preview HTML plugin don't support)
; - :bootleg (don't recommended)
 
(def md2html-method :cmark-gfm)
(def save-md-file?   false)
(def save-html-file? false) 

; ## Deps

(use 'babashka.fs
     'clojure.java.shell
     'clojure.string)

; ## Var

(def src-path-txt  (first *command-line-args*))
(def md-path-txt   (str src-path-txt ".md"))
(def html-path-txt (str src-path-txt ".html"))

(def npp_mlp_dir (str npp-tools "/MarkdownLiteraryProgramming"))

(def re-blank-line    #"(?m)^(\s*)[\r\n]+")
(def re-code-blocks   #"(?m)^\s*[^;]([.\s\S\w\r\n]*?)\r\n;") ;"
(def re-line-comment  #"(?m)^\s*;[ ]?") ;"

; ## md2html Method
       
; ### bootleg (don't recommended)
; ```
;; (use 'babashka.pods)
;; (load-pod "bootleg")
;; (use 'pod.retrogradeorbit.bootleg.markdown)   
;; (defn bootleg2html [md-txt]
;;   (let [html-txt (markdown md-txt :data :html)]
;;     (if save-md-file? 
;;       (spit md-path-txt md-txt :encoding "utf-8")
;;       (delete-if-exists md-path-txt))
;;     (if save-html-file? 
;;       (spit html-path-txt html-txt :encoding "utf-8")
;;       (delete-if-exists html-path-txt))
;;     html-txt))
; ```

; ### MultiMarkdown (option methon)

(defn mmd-fenced-code-blocks [x]
 (-> x
     first
     (#(str "\r\n```language-clojure\r\n" % "\r\n```\r\n; "))))

(defn mmd2html [md-txt]
 (let [mmdexe (str npp-tools "/MultiMarkdown/bin/multimarkdown.exe")
       _ (spit md-path-txt md-txt :encoding "utf-8")
       _ (sh mmdexe "--to=html" 
                    (str "--output=" html-path-txt) 
                    md-path-txt)
       html-txt (slurp html-path-txt :encoding "utf-8")]
    (when-not save-md-file? 
      (delete-if-exists md-path-txt))
    (when-not save-html-file? 
      (delete-if-exists html-path-txt))
   html-txt))
   
(def mmd-head-txt 
  (-> npp_mlp_dir
      (str , "/MLP_common_head_mmd.md")
      (slurp , :encoding "utf-8")
      (clojure.string/replace , #"%npp_mlp_dir%" npp_mlp_dir))) 

    
; ### cmark_gfm (recommended method)
      
(defn gfm-fenced-code-blocks [x]
  (-> x
      first
      (#(str "\r\n```clojure\r\n" % "\r\n```\r\n; "))))

(defn gfm2html [md-txt]
  (let [gfmexe (str npp-tools "/cmark_gfm/cmark-gfm.exe")
        _ (spit md-path-txt md-txt :encoding "utf-8")
        html-txt (-> (sh gfmexe "--to" "html" md-path-txt)
                     :out
                     (clojure.string/replace ,  
                         #"class=\"language-mermaid\"" ;"
                         "class=\"mermaid\""))]
    (when-not save-md-file? 
      (delete-if-exists md-path-txt))
    (if save-html-file? 
      (spit html-path-txt html-txt :encoding "utf-8")
      (delete-if-exists html-path-txt))
    html-txt))
    
(def gfm-head-txt 
  (-> npp_mlp_dir
      (str , "/MLP_common_head_gfm.md")
      (slurp , :encoding "utf-8")
      (clojure.string/replace , #"%npp_mlp_dir%" npp_mlp_dir))) 

; ### pandoc (Don't use, Notepad++ preview HTML plugin don't support)
;```
;;(defn pandoc2html [md-txt]
;;  (let [pandocexe (str npp-tools "/pandoc64/pandoc.exe")
;;        _ (spit md-path-txt md-txt :encoding "utf-8")
;;        _ (sh pandocexe "--from=markdown" 
;;                        "--to=html" 
;;                        "-o" html-path-txt 
;;                        "--toc"
;;                        md-path-txt)
;;        html-txt (slurp html-path-txt :encoding "utf-8")]
;;    (when-not save-md-file? 
;;      (delete-if-exists md-path-txt))
;;    (when-not save-html-file? 
;;      (delete-if-exists html-path-txt))
;;    html-txt))
;```
    
; ### United md2html-method API

;; add Fenced code blocks
(defn fenced-code-blocks [txt md2html-method]
  (case md2html-method
    :cmark-gfm 
       (clojure.string/replace txt re-code-blocks gfm-fenced-code-blocks)
    :MultiMarkdown 
       (clojure.string/replace txt re-code-blocks mmd-fenced-code-blocks)
    ;; :pandoc
       ;; (clojure.string/replace txt re-code-blocks mmd-fenced-code-blocks)
    ;; :bootleg
       ;; (clojure.string/replace , re-code-blocks mmd-fenced-code-blocks)
    (clojure.string/replace txt re-code-blocks gfm-fenced-code-blocks))) 

(defn common-head-tail [txt md2html-method]
  (let [md-tail-txt "\r\n\r\n</body></html>\r\n\r\n"]
    (case md2html-method
      :cmark-gfm 
         (str gfm-head-txt txt md-tail-txt)
      :MultiMarkdown 
         (str mmd-head-txt txt md-tail-txt)
      ;; :pandoc 
         ;; (str gfm-head-txt txt md-tail-txt)
      ;; :bootleg
         ;; (str gfm-head-txt txt md-tail-txt)
      (str gfm-head-txt txt md-tail-txt))))
      
(defn md2html [txt md2html-method]
  (case md2html-method
    :cmark-gfm 
       (gfm2html txt)
    :MultiMarkdown 
       (mmd2html txt)
    ;; :pandoc 
       ;; (pandoc2html txt)   
    ;; :bootleg
       ;; (bootleg2html txt)
    (gfm2html txt))) 
      
; ## Main

(-> src-path-txt
    (slurp , :encoding "utf-8")
    (clojure.string/replace , re-blank-line ";\r\n") ;line comment let blank line to md blank line
    (fenced-code-blocks , md2html-method)
    (clojure.string/replace , re-line-comment "") ;remove all first line comment chars at line ahead
    (common-head-tail , md2html-method)
    (md2html , md2html-method)
    println)

; ## End
