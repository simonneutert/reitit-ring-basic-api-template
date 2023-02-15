(ns web.api.math
  (:require [clojure.data.json :as js]))

(defn sumup [{{{:keys [x y]} :query} :parameters}]
  {:status 200
   :body   {:total (+ x y)}})

(def routes
  [["/math" {:get {:parameters {:query {:x int?, :y int?}}
                   :responses  {200 {:body {:total int?}}}
                   :handler    sumup}}]
   ["/mathj" {:post {:summary "negotiated request & response (json, edn, transit)"
                     :parameters {:body {:x int?, :y int?}}
                     :responses {200 {:body {:total int?}}}
                     :handler (fn [{{{:keys [x y]} :body} :parameters}]
                                {:status 200
                                 :body {:total (+ x y)}})}}]])
