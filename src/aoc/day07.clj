(ns aoc.day07
  (:gen-class))

(use 'aoc.core)

(def input07 (get-lines "days/07.txt"))

(def steps-regex #"Step (?<first>[^\s]+) must be finished before step (?<next>[^\s]+) can begin.")

(defstruct step :first :next)

(defn create-step
  "Map line againt regex to get steps"
  [input]
  (let [matcher (re-matcher steps-regex input)]
    (if (.matches matcher)
      (struct step (.group matcher "first") (.group matcher "next"))
      nil)))

(defn create-steps
  ""
  [input]
  (map create-step input))