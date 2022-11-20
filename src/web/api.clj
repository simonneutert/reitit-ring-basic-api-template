(ns web.api)

(defn routes [& routes]
  (apply conj ["/api"] routes))
