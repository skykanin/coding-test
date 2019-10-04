(ns coding-test.ex1
  (:require [clojure.spec.alpha :as s]))

;; Function specification for highest-prod
(s/fdef highest-prod
  :args (s/cat :ns (s/coll-of int? :min-count 3))
  :ret int?)

(defn highest-prod
  "Returns the highest product of three numbers in the list"
  [ns]
  (apply * (take-last 3 (sort ns))))
