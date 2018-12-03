(ns aoc.day03-test
  (:require [clojure.test :refer :all]
            [aoc.day03 :refer :all]))

(def inputstring "#1 @ 1,3: 4x4");

(deftest should-create-element-from-string-test
  (testing "should result in { :name "1" :x 1 :y 3 :w 4 :h 4 }"
    (is (= { :name "1" :x 1 :y 3 :w 4 :h 4 } (create-claim inputstring)))))
    
(deftest should-tell-that-pos-is-free-test
  (testing "should result in true"
    (is (= true (isfree? free)))))

(deftest should-tell-that-pos-is-not-free-when-hit-test
  (testing "should result in false"
    (is (= false (isfree? hit)))))
    
(deftest should-tell-that-pos-is-not-free-for-other-than-free-test
  (testing "should result in false"
    (is (= false (isfree? "1019")))))

(deftest should-return-correct-size-testing
  (testing "should return 16"
    (is (= 16 (size-el (create-claim inputstring))))))