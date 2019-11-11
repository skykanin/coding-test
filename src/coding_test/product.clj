(ns coding-test.product
  "Contains function and spec for the first task"
  (:require [clojure.spec.alpha :as s]))

;; Function specification for highest-prod
(s/fdef highest-prod
  :args (s/cat :ns (s/coll-of int? :min-count 3))
  :ret int?)

(defn highest-prod
  "Returns the highest product of a triplet
  of integers from a list"
  [ns]
  (let [sorted (sort ns)
        prod #(apply * %)]
    (max (prod (take-last 3 sorted))
         (prod (cons (last sorted)
                     (take 2 sorted))))))
