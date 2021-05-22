; # Markdown literary programming for Clojure

; Author: Lin Pengcheng

; My Blog: [The Grand Unified Programming Theory: The Pure Function Pipeline Data Flow with Principle-based Warehouse/Workshop Model](https://github.com/linpengcheng/PurefunctionPipelineDataflow)

; Reference: [Markdown Literary programming that don't break the syntax of any programming language](https://github.com/linpengcheng/PurefunctionPipelineDataflow/blob/master/doc/markdown_literary_programming.md)

; ## Config

(def npp-tools "C:/Notepad++/tools")   

; ## Deps

(use 'babashka.fs
     'clojure.string)

; ## Var

(def src-path-txt (first *command-line-args*))

(def npp_mlp_dir (str npp-tools "/MarkdownLiteraryProgramming"))
(def md-head-txt 
  (-> npp_mlp_dir
     (str , "/MLP_common_head.md")
     (slurp , :encoding "utf-8")
     (clojure.string/replace , #"%npp_mlp_dir%" npp_mlp_dir))) 
(def md-tail-txt "</body></html>")

(def re-blank-line    #"(?m)^(\s*)[\r\n]+")
(def re-code-blocks   #"(?m)^\s*[^;]([.\s\S\w\r\n]*?)\r\n;") ;"
(def re-line-comment  #"(?m)^\s*;[ ]?") ;"

; ## Function

; ### Fenced code blocks

(defn fenced-code-blocks [x]
  (-> x
      first
      (#(str "\r\n```language-clojure\r\n" % "\r\n```\r\n; "))))
       
; ### bootleg (option methon)
; ```
;; (use 'babashka.pods)
;; (load-pod "bootleg")
;; (use 'pod.retrogradeorbit.bootleg.markdown)   
;; (defn bootleg2html [md-txt]
;;   (markdown md-txt :data :html))
; ```

; ### MultiMarkdown (recommended method)

(use 'clojure.java.shell)
(defn mmd2html [md-txt]
  (let [mmdexe (str npp-tools "/MultiMarkdown/bin/multimarkdown.exe")
        md-path-txt   (str src-path-txt ".md")
        html-path-txt (str src-path-txt ".html")
        _ (spit md-path-txt md-txt :encoding "utf-8")
        _ (sh mmdexe "--to=html" 
                     (str "--output=" html-path-txt) 
                     md-path-txt)
        html-txt (slurp html-path-txt :encoding "utf-8")]
    (delete-if-exists md-path-txt)
    (delete-if-exists html-path-txt)
    html-txt))
    
; ## Main

(-> src-path-txt
    (slurp , :encoding "utf-8")
    (clojure.string/replace , re-blank-line ";\r\n") ;line comment let blank line to md blank line
    (clojure.string/replace , re-code-blocks fenced-code-blocks) ; add Fenced code blocks
    (clojure.string/replace , re-line-comment "") ;remove all line comments
    (#(str md-head-txt % md-tail-txt)) 
    mmd2html
    println)

; ## End
