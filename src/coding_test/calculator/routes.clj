(ns coding-test.calculator.routes
  (:require [coding-test.calculator.logic :refer [balanced-parens?
                                                  calculate
                                                  illegal-char?]]
            [compojure.core :refer [defroutes POST]]
            [compojure.route :refer [not-found]]
            [ring.middleware.json :refer [wrap-json-body
                                          wrap-json-response]]))

(defn error-handler-rep
  "HTTP error response template function"
  [status msg]
  {:status  status
   :headers {"Content-Type" "application/json"}
   :body    {:error msg}})

(defn expression-handler
  "Handles POST request for computing mathematical expression"
  [{{expr :expression :as body} :body}]
  (cond
    (empty? expr) (error-handler-rep 400 "Empty expression")
    (illegal-char? expr)
    (error-handler-rep 400 "Illegal character/s in expression")
    (not (balanced-parens? expr))
    (error-handler-rep
     400
     "Malformed expression, parens aren't balanced")
    :else
    {:status 200
     :headers {"Content-Type" "application/json"}
     :body {:result (str (calculate expr))}}))

(defroutes app-routes
  (POST "/calc" request (wrap-json-response
                         (wrap-json-body
                          expression-handler
                          {:keywords? true})))
  (not-found (wrap-json-response
              (constantly
               (error-handler-rep 404 "Could not find route")))))
