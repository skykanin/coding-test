(ns coding-test.logic
  (:refer-clojure :exclude [replace])
  (:require [clojure.string :refer [replace split]]))

(def operator
  {"+" :plus
   "-" :minus
   "*" :prod
   "/" :div
   "(" :lparen
   ")" :rparen})

(def precedence
  {:plus 2
   :minus 2
   :prod 3
   :div 3
   :lparen 10
   :rparen 10})

(defn clean
  "Sets spaces between parens and next char,
  then splits string on spaces"
  [string]
  (-> string
      (replace #"[(]+" #(str % " "))
      (replace #"[)]+" #(str " " %))
      (split #" ")))

#_(defn balanced-parens?
    "Check if the expression contains balanced parenthesis"
    [expr] 
    (let [balance-fn (fn [acc el]
                       (case))
          res (reduce balance-fn 0 expr)]))

(defn tokenise
  "Tokenise input expression"
  [expr]
  (map (fn [el]
         (if-let [res (operator el)]
           res
           el)) expr))

(defn operator?
  "Checks if token is an operator"
  [token]
  (contains? #{:plus :minus :prod :div} token))

(defn paren?
  "Checks if token is a parethesis"
  [token]
  (contains? #{:lparen :rparen} token))

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
                   :lparen (recur el (cons e operators) output)
                   :rparen (let [[take [_ & drop]]
                                 (split-with (comp not #{:lparen}) operators)]
                             (recur el drop (into output take))))
      (empty? input) (reverse (cons o output))
      :else (recur el operators (cons e output)))))

(defn compute-postfix
  "Computes the postfix expression"
  [expr]
  (let [reducer (fn [[x y & ys :as stack] el]
                  (case el
                    :prod (cons (* x y) ys)
                    :plus (cons (+ x y) ys)
                    :minus (cons (- y x) ys)
                    :div (cons (/ y x) ys)
                    (cons (Integer/parseInt el) stack)))]
    (first (reduce reducer [] expr))))
