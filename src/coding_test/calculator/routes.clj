(ns coding-test.calculator.routes
  (:require [compojure.core :refer [defroutes POST]
             compojure.route :refer [not-found]
             ring.middleware.json :refer [wrap-json-body
                                          wrap-json-response]]))
             

(defn expression-handler
  "Handles POST request for computing mathematical expression"
  [req]
  ())
