(ns coding-test.calculator.route-test
  (:require [clojure.test :refer [deftest is]]
            [coding-test.calculator.routes :refer [expression-handler]]
            [ring.middleware.json :refer [wrap-json-body]]
            [ring.mock.request :as mock]
            [ring.util.response :refer [header response status]]))

(def json-expr-handler
  (wrap-json-body expression-handler {:keywords? true}))

(defn- test-handler
  "Returns the result of a mock request on the handler"
  [expr]
  (json-expr-handler
   (-> (mock/request :post "/calc")
       (mock/json-body {:expression expr}))))

(defn- construct-rep
  "Construct http response with json header given a body"
  [sts body]
  (-> body
      response
      (header "Content-Type" "application/json")
      (status sts)))

(deftest valid-input-test
  (is (= (test-handler "-1 * (2 * 6 / 3)")
         (construct-rep 200 {:result "-4"})))
  (is (= (test-handler "-1 * ( 2 * 6 / 3 )")
         (construct-rep 200 {:result "-4"})))
  (is (= (test-handler "10 * 34 / (9 + 11)")
         (construct-rep 200 {:result "17"})))
  (is (= (test-handler "-66 / 3 + 100")
         (construct-rep 200 {:result "78"})))
  (is (= (test-handler "-66 / (2 + 4)")
         (construct-rep 200 {:result "-11"}))))

(deftest invalid-input-test
  (is (= (test-handler "")
         (construct-rep 400 {:error "Empty expression"})))
  (is (= (test-handler "10 * 34 / (9 + 11))")
         (construct-rep 400 {:error "Malformed expression, parens aren't balanced"})))
  (is (= (test-handler "-66 / 3 + 100_")
         (construct-rep 400 {:error "Illegal character/s in expression"})))
  (is (= (test-handler "-66 / @(2 + 4)")
         (construct-rep 400 {:error "Illegal character/s in expression"}))))
