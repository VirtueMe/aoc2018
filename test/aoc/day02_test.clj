(ns aoc.day02-test
  (:require [clojure.test :refer :all]
            [aoc.day02 :refer :all]))
            
(deftest part1-example1-test
  (testing "should result in [[\"1\" \"2\"] [\"3\" \"4\"]]"
    (is (= [["1" "2"] ["3" "4"]] (make-letters-lists ["12" "34"])))))