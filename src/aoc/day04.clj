(ns aoc.day04
  (:gen-class))

(use 'aoc.core)

(def hit "X")
(def free "")
(def hour (vec (repeat 60 free)))


(def input04 (get-lines "days/04.txt"))

(defstruct event :date :time :event)
(defstruct guard :name :events )
(defstruct watch :date :hour)

(defn create-event
  ""
  [string]
  (struct event (subs string 6 11) (subs string 12 17) (split-words (subs string 19 ))))

(defn map-events 
  ""
  [events]
  (map create-event events))

(defn sort-events
  ""
  [events]
  (sort events))
  
(defn create-guard 
  "creates a guard struct from a event struct"
  [input]
  (struct guard ((:event input) 1) []))
  
(defn show-stats
  ""
  [input]
  [(:name input) (count (:events input))])

(defn create-hour
  ""
  [start stop]
  (vec (concat (take start hour) (repeat (- stop start) hit) (drop stop hour))))

(defn map-time
  ""
  [time]
  (Integer. (subs (:time time) 3)))
  
(defn map-hour
  ""
  [input]
  (let [[start stop] input]
    (struct watch (:date start) (create-hour (map-time start) (map-time stop)))))
  
(defn map-to-hour
  ""
  [input]
  (loop [list input result []]
    (if (empty? list)
      result
      (let [pair (take 2 list)]
        (recur (drop 2 list) (conj result (map-hour pair)))))))

(defn map-to-guard-hour
  ""
  [input]
  (struct guard (:name input) (map-to-hour (:events input))))
  
(defn events-by-guard
  ""
  [events]
  (loop [list (rest events) results [] current (create-guard (first events))]
    (if (empty? list)
      results
      (let [next (first list)]
        (if (= "Guard" ((:event next) 0))
          (recur (rest list) (conj results current) (create-guard next))
          (recur (rest list) results (update-in current [:events] conj next)))))))

(defn merge-events
  ""
  [inputs el]
  (let [item (inputs (:name el))]
    ;; (println el)
    (if (nil? item)
      (into inputs { (:name el) el })
      (update-in inputs [ (:name el) :events ] concat (:events el) ))))

(defn remove-empty
  ""
  [input]
  (filter #(> (count (:events %)) 0) input))

(defn highest-minutes
  ""
  [item1 item2]
  (if (> (count (second item1)) (count (second item2)))
    item1
    item2))

(defn find-indexed-map
  ""
  [sleeper]
  (map-indexed vector (apply mapv str (map #(:hour %) (:events sleeper)))))

(defn find-highest-minute
  ""
  [sleeper]
  (reduce #(highest-minutes %1 %2) (find-indexed-map sleeper)))
  
(defn find-index
  ""
  [sleeper]
  (first (find-highest-minute sleeper)))
    
(defn calcsum
  ""
  [sleeper]
  (* (Integer. (subs (:name sleeper) 1)) (find-index sleeper)))

(defn calc-minutes
  ""
  [input]
  (reduce #(+ %1 (%2 "X")) 0 (map #(frequencies (:hour %)) input)))
    
(defn add-sleep-time
  ""
  [input]
  (into input { :sleep (calc-minutes (:events input)) }))


(defn compare-sleep
  ""
  [item1 item2]
  (if (> (:sleep item1) (:sleep item2))
    item1
    item2))  

(defn find-sleeper
  ""
  [input]
  (reduce #(compare-sleep %1 %2) (map add-sleep-time (vals (reduce #(merge-events %1 %2) {} (remove-empty (map map-to-guard-hour (events-by-guard (map-events (sort input))))))))))

;; Part 2
(defn add-sleep-time-minute
  ""
  [input]
  (into input { :sleep (find-highest-minute input) }))

(defn compare-sleep-min
  ""
  [item1 item2]
  (if (> (count (second (:sleep item1))) (count (second (:sleep item2))))
    item1
    item2))  

(defn find-sleeper-min
  ""
  [input]
  (reduce #(compare-sleep-min %1 %2) (map add-sleep-time-minute (vals (reduce #(merge-events %1 %2) {} (remove-empty (map map-to-guard-hour (events-by-guard (map-events (sort input))))))))))

(defn calcsum-min
  ""
  [sleeper]
  (* (Integer. (subs (:name sleeper) 1)) (first (:sleep sleeper))))


(defn solve04-1
  ""
  ([]
    (calcsum (find-sleeper input04)))
  ([input]
    (calcsum (find-sleeper input))))

(defn solve04-2
  ""
  ([]
    (calcsum-min (find-sleeper-min input04)))
  ([input]
    (calcsum-min (find-sleeper-min input))))
