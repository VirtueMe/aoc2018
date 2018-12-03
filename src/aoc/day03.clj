(ns aoc.day03
  (:gen-class))

(use 'aoc.core)

(def hit "X")
(def free ".")
(def side 1000)
(def square (vec (repeat (* side side) free)))

;; ["#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2"]
(def input03 (get-lines "days/03.txt"))

;; Part 1
(def claim-regex #"#(?<name>[^\s]+) @ (?<x>[0-9]+),(?<y>[0-9]+): (?<w>[0-9]+)x(?<h>[0-9]+)")

(defn create-claim
  "Map line againt regex to get claim node"
  [input]
  (let [matcher (re-matcher claim-regex input)]
    (if (.matches matcher)
      { :name (.group matcher "name") :x (Integer. (.group matcher "x")) :y (Integer. (.group matcher "y")) :w (Integer. (.group matcher "w")) :h (Integer. (.group matcher "h")) :size (* (Integer. (.group matcher "w")) (Integer. (.group matcher "h")))}
      nil)))

(def isfree? (partial = free))

(defn apply-claim-row
  "apply occupied columns in row"
  [input el pos times]
    (loop [list (range times) result input]
      (if (empty? list)
        result
        (let [y (+ pos (first list))]
          ;;(println pos " " y)
          (recur (rest list) (assoc result y (if (isfree? (nth result y)) el hit)))))))

(defn set-claim
  "apply claim to area"
  [input el]
  (loop [list (range (el :h)) result input]
    (if (empty? list)
      result
      (let [y (first list)]
        ;; (println y)
        (recur (rest list) (apply-claim-row result (el :name) (+ (* side (+ y (el :y))) (el :x)) (el :w)))))))

(defn create-claims-list
  ""
  [input]
  (map create-claim input))
  
(defn map-claims 
  [claims]
  (reduce #(set-claim %1 %2) square claims))

(defn get-layout
  ""
  [claims]
    (let [result (map-claims claims)]
      (frequencies result)))
      
(defn calculate-size
  "calculate the overlapping area"
  [input]
    (let [result (get-layout (create-claims-list input))]
        (result hit)))

;; Part 2

(defn intact?
  [claims item]
  (let [claim (first (filter #(= (% :name) (item 0)) claims))]
    (if (nil? claim )
      false
      (= (claim :size) (item 1)))))
    
(defn get-intact
  ""
  [input]
  (let [claims (create-claims-list input)]
    (let [layout (get-layout claims)]
      (filter #(intact? claims %) layout))))

;; Solutions
(defn solve03-1
  "Solve day 03 part 1"
  ([]
    (calculate-size input03))
  ([input]
    (calculate-size input)))

(defn solve03-2
  "Solve day 03 part 2"
  ([]
    ((first (get-intact input03)) 0))
  ([input]
    ((first (get-intact input)) 0)))
