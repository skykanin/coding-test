(ns coding-test.product
  "Contains function and spec for the first task"
  (:require [clojure.spec.alpha :as s]))

;; Function specification for highest-prod
(s/fdef highest-prod
  :args (s/cat :ns (s/coll-of int? :min-count 3))
  :ret int?)

(defn highest-prod
  "Returns the highest product of the three
  integers in a list"
  [ns]
  (->> ns
       (map #(Math/abs %))
       sort
       (take-last 3)
       (apply *)))
