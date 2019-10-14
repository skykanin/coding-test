(ns coding-test.product
  "Contains function and spec for the first task"
  (:require [clojure.spec.alpha :as s]))

;; Function specification for highest-prod
(s/fdef highest-prod
  :args (s/cat :ns (s/coll-of int? :min-count 3))
  :ret int?)

(defn highest-prod
  "Returns the highest product of the three
  largest numbers in a list"
  [ns]
  (apply * (take-last 3 (sort ns))))
