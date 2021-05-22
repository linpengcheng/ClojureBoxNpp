; Author: Lin Pengcheng
; My Blog: [The Grand Unified Programming Theory: The Pure Function Pipeline Data Flow with Principle-based Warehouse/Workshop Model](https://github.com/linpengcheng/PurefunctionPipelineDataflow)

(ns npp-convert)

(defn convert []
  (->> *command-line-args*
       (apply str ,)
       (#(str "Hello " % "! "))
       ;class
       print))
       
(convert)
