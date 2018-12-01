(ns aoc.core)

(use 'clojure.java.io)
(use '[clojure.string :only (split)])

(defn get-lines
  "read file into collection"
  [fname]
  (with-open [r (reader fname)]
    (doall (line-seq r))))