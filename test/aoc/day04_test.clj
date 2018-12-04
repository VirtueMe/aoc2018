(ns aoc.day04-test
  (:require [clojure.test :refer :all]
            [aoc.day04 :refer :all]))
            
(def event "[1518-10-05 00:10] falls asleep")

(def events [
  "[1518-10-05 00:10] falls asleep"
  "[1518-07-22 23:53] Guard #1949 begins shift"
  "[1518-07-06 00:55] wakes up"
  "[1518-07-16 00:38] falls asleep"
  ])

(deftest can-split-event-from-string-test
  (testing "should result in { :date \"1518-10-05 00:10\" :event [ \"falls\" \"asleep\" ] }"
    (is (= { :date "1518-10-05 00:10" :event [ "falls" "asleep" ] } (create-event event)))))

(deftest can-sort-events-test
  (testing "should result in sorted list"
    (is (= "1518-07-06 00:55" ((first (sort-events (map-events events))) :date)))))