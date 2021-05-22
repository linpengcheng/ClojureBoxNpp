; Author: Lin Pengcheng
; My Blog: [The Grand Unified Programming Theory: The Pure Function Pipeline Data Flow with Principle-based Warehouse/Workshop Model](https://github.com/linpengcheng/PurefunctionPipelineDataflow)

(use 'hiccup.core)
(->> *command-line-args* 
     first
     slurp
     read-string
     eval
     html
     print)
