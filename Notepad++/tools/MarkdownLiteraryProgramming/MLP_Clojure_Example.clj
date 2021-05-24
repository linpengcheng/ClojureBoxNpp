;# Markdown Literary Programming's Example

; Author: Lin Pengcheng

; My Blog: [The Grand Unified Programming Theory: The Pure Function Pipeline Data Flow with Principle-based Warehouse/Workshop Model](https://github.com/linpengcheng/PurefunctionPipelineDataflow)

; Reference: [Markdown Literary programming that don't break the syntax of any programming language](https://github.com/linpengcheng/PurefunctionPipelineDataflow/blob/master/doc/markdown_literary_programming.md)

;## example01: Code with syntax highlighting.

(defn f [[evens odds total amax amin] x]
  (let [[evens odds] (cond 
                       (even? x) [(inc evens ) odds]
                       (odd? x)  [evens (inc odds)]
                       :else     [evens odds])
        total (+ total x)
        amax  (max amax x)
        amin  (min amin x)]   
     [evens odds total amax amin]))
;;The comment of the code requires at least two line comment characters
(reduce f [0 0 0 ##-Inf ##Inf] [5 6 8 -3 -9 11 156 6 7])

;return `[4 5 187 156 -9]`

;## example02: Mermaid flow chart

; - Notepad++ preview HTML plugin (IE 11) don't display letter, 
; - It can display letter using chrome open saved html 
;   if set save html in config.

;```mermaid
;graph LR
;      A-->B
;      B-->C
;      C-->A
;      D-->C
;```

;## example03: Image

;![CowPlot](./image/cowplot.png)

;## example04: UI + javascript/Clojurescript(sci)

; Reference: [sci-script-tag](https://borkdude.github.io/sci-script-tag/)

; - Notepad++ preview HTML plugin (IE 11) don't run UI script, 
; - It can run UI script using chrome open saved html 
;   if set save html in config.

;<button onclick = "user.myAlert()">
;        Click me</button>

;<script type="application/x-sci">
;  (defn myAlert []
;    (js/alert "alert!"))
;</script>