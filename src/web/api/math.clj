(ns web.api.math)

(def routes
  ["/math" {:get {:parameters {:query {:x int?, :y int?}}
                  :responses  {200 {:body {:total int?}}}
                  :handler    (fn [{{{:keys [x y]} :query} :parameters}]
                                {:status 200
                                 :body   {:total (+ x y)}})}}])
