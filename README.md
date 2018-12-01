# Advent of Code-2018

A Clojure library designed to solve the puzzles of Advent of Code 2018.

## Usage

There will be 25 puzzles divided into 2 parts each day. To run the puzzles use the command ```lein repl```.

Inside the repl, choose the day you want to use by ```(use 'aoc.dayXX)```. Where XX is either 01-09 or 10-25. You get the solution for the first part by running ```(solve)```, the second part by running ```(solve2)```

``` clojure
(use 'aoc.dayXX)
(solve)
(solve2)
```

## License

Copyright © 2018 Benny Thomas

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
