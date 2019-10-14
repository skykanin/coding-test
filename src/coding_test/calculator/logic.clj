(ns coding-test.calculator.logic
  (:refer-clojure :exclude [replace])
  (:require [clojure.string :refer [replace split]]))

(def precedence
  {"+" 2
   "-" 2
   "*" 3
   "/" 3})

(defn clean
  "Sets spaces between parens and next char,
  then splits string on spaces"
  [string]
  (-> string
      (replace #"\(" #(str % " "))
      (replace #"\)" #(str " " %))
      (split #" ")
      (->> (filterv (comp not empty?)))))

(defn balanced-parens?
  "Check if the expression contains balanced parenthesis"
  [string]
  (let [balance-fn (fn [acc el]
                     (case el
                       \( (inc acc)
                       \) (dec acc)
                       acc))
        res (reduce balance-fn 0 string)]
    (zero? res)))

(defn illegal-char?
  "Checks if the expression contains illegal characters"
  [string]
  (seq (re-find #"[^0-9\+\-\*\/\(\)\s]" string)))

(defn operator?
  "Checks if token is an operator"
  [token]
  (contains? #{"+" "-" "*" "/"} token))

(defn paren?
  "Checks if token is a parethesis"
  [token]
  (contains? #{"(" ")"} token))

(defn infix->postfix
  "Convert list of infix tokens to postfix"
  [expr]
  (loop [[e & el :as input] expr
         [o & os :as operators] '()
         output '()]
    (cond
      (and (operator? e) (paren? o)) (recur el (cons e operators) output)
      (operator? e) (if (empty? operators)
                      (recur el (cons e operators) output)
                      (let [input-el (precedence e)
                            operator-el (precedence o)]
                        (if (<= input-el operator-el)
                          (recur el (cons e os) (cons o output))
                          (recur el (cons e operators) output))))
      (paren? e) (case e
                   "(" (recur el (cons e operators) output)
                   ")" (let [[take [_ & drop]]
                             (split-with (comp not #{"("}) operators)]
                         (recur el drop (into output take))))
      (empty? input) (reverse (cons o output))
      :else (recur el operators (cons e output)))))

(defn compute-postfix
  "Computes the postfix expression"
  [expr]
  (let [reducer (fn [[x y & ys :as stack] el]
                  (case el
                    "*" (cons (* x y) ys)
                    "+" (cons (+ x y) ys)
                    "-" (cons (- y x) ys)
                    "/" (cons (/ y x) ys)
                    (cons (Integer/parseInt el) stack)))]
    (first (reduce reducer [] expr))))

(def calculate (comp compute-postfix infix->postfix clean))
