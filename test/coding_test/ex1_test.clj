(ns coding-test.ex1-test
  (:require [clojure.test :as t]
            [respeced.test :as rt]
            [coding-test.ex1 :refer [highest-prod]]))

;; Validating the function input arguments for highest-prod
(t/deftest valid-args
  (t/is 120 (highest-prod '(1 2 3 4 5 6)))
  (t/is 120 (highest-prod [1 2 3 4 5 6]))
  (t/is -6 (highest-prod (list 1 3 -2))))

(t/deftest invalid-args
  (rt/with-instrumentation `highest-prod
    (t/is (rt/caught? `highest-prod (highest-prod [30 20])))
    (t/is (rt/caught? `highest-prod (highest-prod '(3 -4))))
    (t/is (rt/caught? `highest-prod (highest-prod "hello")))
    (t/is (rt/caught? `highest-prod (highest-prod [:a :b :c :d])))))
  
