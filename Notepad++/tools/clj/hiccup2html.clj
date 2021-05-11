(use '[hiccup.core :as hiccup])
(->> *command-line-args* 
     first
     slurp
     read-string
     html
     print)