(ns coding-test.calculator.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.params :refer [wrap-params]]
            [coding-test.calculator.routes :refer [app-routes]]))

(defn -main
  "Entrypoint for rest api calculator"
  [& _]
  (run-jetty (wrap-params app-routes)
             {:host "localhost"
              :port 3008}))

