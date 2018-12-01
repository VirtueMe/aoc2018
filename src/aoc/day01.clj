(ns aoc.day01
  (:gen-class))

(use 'aoc.core)

(def input01 (vec (map #(Integer. %) (get-lines "days/01.txt"))))

(defn calculate
  "Calculates the resulting frequency"
  [input]
  (loop [sum 0 list input]
    (if (empty? list)
      sum
      (recur (+ sum (first list)) (rest list)))))
      
(defn findDuplicate
  "What is the first frequency your device reaches twice"
  [input]
  (loop [sum 0 list input sums [0]]
    ;; (println sum)
    (if (empty? list)
      (recur sum input sums)
      (let [newSum (+ sum (first list))]
        (if (some #(= newSum %) sums)
          newSum
          (recur newSum (rest list) (conj sums newSum)))))))

(defn solve
  "solve the day01 puzzle part 1"
  ([]
    (println (calculate input01)))
  ([input]
    (calculate input)))
    
(defn solve2
  "solve the day01 puzzle part 2"
  ([]
    (println (findDuplicate input01)))
  ([input]
    (findDuplicate input)))
    
  