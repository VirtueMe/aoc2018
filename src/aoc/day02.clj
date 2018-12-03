(ns aoc.day02
  (:gen-class))

(use 'aoc.core)

(def input02 (vec (get-lines "days/02.txt")))

(defn count-words
  [words-to-count]
  ;; (reduce record-word-count {} words-to-count)
  (frequencies words-to-count))

;; PART 1

;; First solution
(defn create-count-map
  [input]
  (loop [item input, result {}]
    (if (empty? item)
      result
      (let [letter (keyword (str (first item)))]
        ;;(println letter)
        (if (contains? result letter)
          (recur (rest item) (update result letter inc))
          (recur (rest item) (assoc result letter 1)))))))

(defn create-count-maps
  [input]
  (loop [list input result []]
    (if (empty? list)
      result
      (recur (rest list) (conj result (create-count-map (first list)))))))

(defn update-count
  [input k should]
  (update input k #(let [value %] (if should (inc value) value))))

(defn has-value
  [list value]
  (not (empty? (filter (fn [[k v]] (= v value)) list))))

(defn count-them
  [input]
  (loop [lists input result { :2 0 :3 0 }]
    (if (empty? lists)
      result
      (let [list (first lists)]
        ;; (println "2: " (has-value list 2) " 3: " (has-value list 3))
        (recur (rest lists) (update-count (update-count result :3 (has-value list 3)) :2 (has-value list 2)))))))

(defn checksum
  [input]
  (* (input :2) (input :3)))

;; Second solution
(defn make-letters-lists
  "Split string to letters"
  [input]
  (map split-letters input))

(defn count-letters-list
  "Count all occurances of letters in string"
  [input]
  (map count-words (make-letters-lists input)))

(defn pair-values
  "Return a pair of :2 :3 with the corresponding count value 0 or 1"
  [list]
  { :2 (if (has-value list 2) 1 0) :3 (if (has-value list 3) 1 0)})

(defn filter-letters
  "Maps string to pair values"
  [input]
  (map pair-values (count-letters-list input)))

(defn solve-part1-solution2
  "Solves the puzzle"
  [input]
  (checksum (reduce #(merge-with + %1 %2) (filter-letters input))))

;; Part 2
(defn remove-at
  [s index]
  (str (.substring s 0 index) (.substring s (inc index))))

(defn range-it
  [input]
  (let [item (first input)]
    (range (count item))))

(defn remove-at-list
  [list index]
  (map #(remove-at % index) list))

(defn make-word-lists
  [input]
  (map #(remove-at-list input %) (range-it input)))

(defn count-words-list
  [input]
  (map #(count-words %) (make-word-lists input)))

(defn find-match
  [list]
  (filter (fn [[k v]] (= v 2)) list))

(defn filter-lists
  [input]
  (flatten (filter #(not (empty? %)) (map #(find-match %) input))))

(defn find-box-match
  [input]
  (first (filter-lists (count-words-list input))))

;; Results

(defn solve02-1
  ([]
    (solve-part1-solution2 input02))
  ([input]
    (solve-part1-solution2 input)))

(defn solve02-2
  ([]
    (find-box-match input02))
  ([input]
    (find-box-match input)))
