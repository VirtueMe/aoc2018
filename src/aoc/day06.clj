(ns aoc.day06
  (:gen-class))

(use 'aoc.core)


(defn to-int
  ""
  [input]
  (Integer. input))

(def alpha (vec (concat (map #(char (+ 65 %)) (range 26)) (map #(char (+ (+ 32 65) %)) (range 26)))))

(def input06 (map-indexed #(cons (alpha %) (map to-int %2)) (map split-cs-words (get-lines "days/06.txt"))))

(def input06-test
  (list
    (list \A 1 1)
    (list \B 1 6)
    (list \C 8 3)
    (list \D 3 4)
    (list \E 5 5)
    (list \F 8 9)))


(def nohit \.)

(def SLOT { :holder nohit :hits {}})

(defn create-board
  ""
  [w h piece]
  (repeat (* w h) piece))

(defn boundary
  ""
  [input]
  (list (+ 2 (apply max (map second input))) (+ 1 (apply max (map #(nth % 2) input)))))

(defn get-edge
  ""
  [w h input]
  (let [end (dec h) len (dec w)]
    ;; (println end len)
    (loop [counter 0 result (take len input) list (drop len input)]
      ;; (println result)
      (if (= counter end)
        (concat result (take-last len input))
        (recur (inc counter) (concat result (take 2 list)) (drop w list))))))

(defn get-coords
  ""
  [index width]
  { :x (rem index width) :y (quot index width) })

(defn calc-distance
  ""
  [coords item]
  (+ (Math/abs (- (second item) (coords :x))) (Math/abs (- (nth item 2) (coords :y)))))

(defn walk
  ""
  [index width slot item]
  (let [coords (get-coords index width)]
    (let [distance (calc-distance coords item)]
      ;; (println index distance (first item) (second item) (nth item 2) (rem index w) (quot index w))
      (if (= 0 distance)
        { :holder (first item) :hits { (first item) distance } }
        (let [current (or (first (vals (:hits slot))) -1)]
          ;; (println current " : " distance)
          (if ( or (> current distance) (= -1 current))
            { :holder (first item) :hits { (first item) distance } }
            (if (= current distance)
              { :holder nohit :hits (:hits slot) }
              slot)))))))



(defn map-board
  ""
  [input]
  (let [[w h] (boundary input)]
    (loop [board (create-board w h SLOT) list input]
      ;; (println (first board))
      (if (empty? list)
        ;; (partition w board)
        ;; (frequencies (map #(:holder %) board))
        (apply dissoc (frequencies (map #(:holder %) board)) (set (map #(:holder %) (get-edge w h board))))
        ;; (get-edge w h board)
        (let [current (first list)]
          (recur (map-indexed #(walk % w %2 current) board) (rest list)))))))

(defn calc-max-area
  ""
  [input]        
  (apply max (vals (map-board input))))
  
;; Part 2
(defn if-limit
  ""
  [sum limit value]
  (if (> limit sum) sum value))

(defn calc-limit
  ""
  [input limit width index value]
    (let [coords (get-coords index width)]
      (if-limit (apply + (map #(calc-distance coords %) input)) limit value)))

(defn calc-dist-sum
  ""
  [input limit]
  (let [[w h] (boundary input)]
    (let [board (create-board w h 0) partial-calc (partial calc-limit input limit w)]
      (filter #(> % 0) (map-indexed #(partial-calc % %2) board)))))

(defn solve06-1
  ""
  ([]
    (calc-max-area input06))
  ([input]
    (calc-max-area input)))

(defn solve06-2
  ""
  ([]
    (calc-dist-sum input06 10000))
  ([input]
    (map-board input 10000))
  ([input limit]
    (map-board input limit)))
