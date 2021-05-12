(use 'hiccup.core)
(->> *command-line-args* 
     first
     slurp
     read-string
     eval
     html
     print)
