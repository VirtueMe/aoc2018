(defproject aoc "0.1.0-SNAPSHOT"
  :description "A Clojure library designed to solve the puzzles of Advent of Code 2018."
  :url "https://github.com/VirtueMe/aoc2018"
  :license {:name "Eclipse Public License"
  :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"] [org.clojure/core.match "0.3.0-alpha5"]]
  :main ^:skip-aot aoc.core
  :target-path "target/%s"
  :jvm-opts ["-Xmx1G"]
  :profiles {:uberjar {:aot :all}})
