(ns aoc.core)

(use 'clojure.java.io)
(use '[clojure.string :only (split)])

(defn split-letters
  [str]
  (split str #""))

(defn split-words
  [str]
  (split str #" "))

(defn get-lines
  "read file into collection"
  [fname]
  (with-open [r (reader fname)]
    (doall (line-seq r))))
