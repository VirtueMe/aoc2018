(ns aoc.day04
  (:gen-class))

(use 'aoc.core)

(def input04 (get-lines "days/04.txt"))

(defstruct event :date :event)

(defn create-event
  ""
  [string]
  (struct event (subs string 1 17) (split-words (subs string 19 ))))

(defn map-events 
  ""
  [events]
  (map create-event events))

(defn sort-events
  ""
  [events]
  (sort #(compare (%1 :date) (%2 :date)) events))