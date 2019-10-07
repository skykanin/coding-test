(ns coding-test.calculator.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.params :as ring-params]
            [coding-test.calculator.routes [app-routes]]))

(defn -main
  "Entrypoint for rest api calculator"
  [& args]
  (run-jetty (ring-params/wrap-params app-routes)))
            
