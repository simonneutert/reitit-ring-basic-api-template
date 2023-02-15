(ns web.api)

(defn routes [& routes]
  (cons "/api" routes))
