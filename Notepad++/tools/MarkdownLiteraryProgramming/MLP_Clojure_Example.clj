;# Markdown literary programming Example

;## example01: Code with syntax highlighting.

;```clojure
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
;```

;return `[4 5 187 156 -9]`

;## example02: Mermaid flow chart

;```mermaid
;graph LR
;      A-->B
;      B-->C
;      C-->A
;      D-->C
;```

;## example03: Image

;![cowplot](../image/cowplot.png)

