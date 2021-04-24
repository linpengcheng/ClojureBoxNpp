(ns npp-convert)

(defn convert []
  (->> *command-line-args*
       (apply str ,)
       (#(str "Hello " % "! "))
       ;class
       print))
       
(convert)