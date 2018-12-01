(ns aoc.day01-test
  (:require [clojure.test :refer :all]
            [aoc.day01 :refer :all]))

(deftest part1-example1-test
  (testing "should result in 3"
    (is (= 3 (solve [1 -2, 3, 1])))))

(deftest part1-example2-testing
  (testing "should result in 3"
    (is (= 3 (solve [1 1 1])))))

(deftest part1-example3-testing
  (testing "should result in 0"
    (is (= 0 (solve [1 1 -2])))))

(deftest part1-example4-testing
  (testing "should result in -6"
    (is (= 0 (solve2 [-1 -2 -3])))))

(deftest part2-example1-test
  (testing "should result in 2"
    (is (= 2 (solve2 [1 -2, 3, 1])))))

(deftest part2-example2-testing
  (testing "should result in 0"
    (is (= 0 (solve2 [1 -1])))))

(deftest part2-example3-testing
  (testing "should result in 10"
    (is (= 10 (solve2 [3 3 4 -2 -4])))))

(deftest part2-example4-testing
  (testing "should result in 5"
    (is (= 5 (solve2 [-6 3 8 5 -6])))))

(deftest part2-example5-testing
  (testing "should result in 14"
    (is (= 14 (solve2 [7 7 -2 -7 -4])))))
