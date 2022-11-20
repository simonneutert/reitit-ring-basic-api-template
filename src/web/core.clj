(ns web.core
  (:require [muuntaja.core :as m]
            [reitit.ring :as ring]
            [reitit.coercion.spec]
            [reitit.ring.coercion :as rrc]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [reitit.ring.middleware.parameters :as parameters]
            [ring.adapter.jetty :as jetty]
            [web.api :as api]
            [web.api.math :as math]))

(def app
  (ring/ring-handler
   (ring/router
    (api/routes math/routes)
    ;; router data affecting all routes
    {:data {:coercion   reitit.coercion.spec/coercion
            :muuntaja   m/instance
            :middleware [parameters/parameters-middleware
                         rrc/coerce-request-middleware
                         muuntaja/format-response-middleware
                         rrc/coerce-response-middleware]}})
   (ring/create-default-handler)))

(defn start []
  (println "server running in port 3000")
  (jetty/run-jetty #'app {:port 3000, :join? false}))

(comment
  (start))