(ns coding-test.product-test
  (:require [clojure.test :as t]
            [respeced.test :as rt]
            [coding-test.product :refer [highest-prod]]))

;; Validating the function input arguments for highest-prod
(t/deftest valid-args
  (t/is (= 120 (highest-prod '(1 2 3 4 5 6))))
  (t/is (= 120 (highest-prod [1 2 3 4 5 6])))
  (t/is (= -6 (highest-prod (list 1 3 -2))))
  (t/is (= 75 (highest-prod [2 3 -5 -5])))
  (t/is (= 120 (highest-prod [4 -3 -10 -1])))
  (t/is (= 1500 (highest-prod [4 -3 -10 5 -1 -30])))
  (t/is (= 1200 (highest-prod [-9 8 -10 -15])))
  (t/is (= 168 (highest-prod [-4 7 3 8])))
  (t/is (= -90 (highest-prod [-10 -3 -5 -6 -20])))
  (t/is (= 168 (highest-prod [1 -4 3 -6 7 0]))))

(t/deftest invalid-args
  (rt/with-instrumentation `highest-prod
    (t/is (rt/caught? `highest-prod (highest-prod [30 20])))
    (t/is (rt/caught? `highest-prod (highest-prod '(3 -4))))
    (t/is (rt/caught? `highest-prod (highest-prod "hello")))
    (t/is (rt/caught? `highest-prod (highest-prod [:a :b :c :d])))))

