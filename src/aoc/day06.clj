(ns aoc.day06
  (:gen-class))

(use 'aoc.core)


(defn to-int
  ""
  [input]
  (Integer. input))

(def input06 (map-indexed #(cons (char (+ 65 %)) (map to-int %2)) (map split-cs-words (get-lines "days/06.txt"))))

(def nohit ".")

(defn create-board
  ""
  [w h]
  (repeat (* w h) { :holder nohit :hits []}))

(defn boundary
  ""
  [input]
  (list (apply max (map second input)) (apply max (map #(nth % 2) input))))
  
(defn map-board
  ""
  [input]
  (let [[w h] (boundary input)]
    (create-board w h)))
  