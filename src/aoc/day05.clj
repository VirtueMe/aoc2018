(ns aoc.day05
  (:gen-class))

(use 'aoc.core)

(def input05 (first (get-lines "days/05.txt")))

(defn calc-params
  ""
  [hit one two list parsed]
  (if hit
    (if (empty? parsed)
      [(drop 2 list) parsed]
      [(concat [(last parsed)] (drop 2 list)) (butlast parsed)])
    [(drop 1 list) (concat parsed [one])]))
  
(defn hit?
  ""
  [one two]
  (if (Character/isLowerCase one)
    (and (Character/isUpperCase two) (= one (Character/toLowerCase two)))
    (and (Character/isLowerCase two) (= one (Character/toUpperCase two)))))

;; Part 1
(defn parse
  ""
  [input]
  (loop [list (seq input) parsed '()]
    (if (empty? list)
      parsed
      (let [[one two] (take 2 list)]
        ;; (println one " : " two " : " list " : " parsed)
        (if (nil? two)
          (concat parsed [one])
          (let [hit (hit? one two)]
            (let [params (calc-params hit one two list parsed)]
              (recur (first params) (second params)))))))))

;; Part 2
(defn only-lower
  ""
  [list item]
  ;; (println list " : " item)
  (if (Character/isLowerCase (first item))
    (conj list (first item))
    list))

(defn strip
  [coll chars]
  (remove #((set chars) %) coll))
     
(defn create-pair
  ""
  [char]
  [(Character/toUpperCase char) char])

(defn sum-pair
  ""
  [total list pair]
  (- total (+ (list (first pair)) (list (second pair)))))
    
(defn best-parse
  ""
  [input]
  (let [list (frequencies input)]
    (let [total (count input) pairs (map create-pair (reduce #(only-lower %1 %2) [] list))]
      (apply min (map count (map parse (map #(strip input %) pairs)))))))

(defn solve05-1
  ""
  ([]
    (count (parse input05)))
  ([input]
    (count (parse input))))
    
(defn solve05-2
  ""
  ([]
    (best-parse input05))
  ([input]
    (best-parse input)))