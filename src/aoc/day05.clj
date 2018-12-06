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
      [(concat [(peek parsed)] (drop 2 list)) (vec (butlast parsed))])
    [(drop 1 list) (conj parsed one)]))
  
(defn hit?
  ""
  [one two]
  (= 32 (Math/abs (- (int one) (int two)))))

;; Part 1
(defn parse
  ""
  [input]
  (loop [list (seq input) parsed []]
    (if (empty? list)
      parsed
      (let [[one two] list]
        ;; (println one " : " two " : " list " : " parsed)
        (if (nil? two)
          (concat parsed [one])
          (let [hit (hit? one two)]
            (let [[newlist newparsed] (calc-params hit one two list parsed)]
              (recur newlist newparsed))))))))

;; Part 1 second solution
;; I didn't like the speed, nor the complexity of parse, so I rewrote it without using a buffer.
(defn parse2
  ""
  [input]
  (loop [list (if (string? input) input (apply str input)) pos 0]
    (if (= (count list) (inc pos))
      list
      (let [[a b] (seq (subs list pos (+ pos 2)))]
        (if (hit? a b)
          (recur (str (subs list 0 pos) (subs list (+ pos 2))) (if (= pos 0) pos (dec pos)))
          (recur list (inc pos)))))))
          

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

(defn best-parse
  ""
  [input]
  (let [list (frequencies input)]
    (let [pairs (map create-pair (reduce #(only-lower %1 %2) [] list))]
      (apply min (map count (map parse (map #(strip input %) pairs)))))))

(defn best-parse2
  ""
  [input]
  (let [list (frequencies input)]
    (let [pairs (map create-pair (reduce #(only-lower %1 %2) [] list))]
      (apply min (map count (map parse2 (map #(strip input %) pairs)))))))

(defn solve05-1
  ""
  ([]
    (count (parse2 input05)))
  ([input]
    (count (parse2 input))))
    
(defn solve05-2
  ""
  ([]
    (best-parse2 input05))
  ([input]
    (best-parse2 input)))