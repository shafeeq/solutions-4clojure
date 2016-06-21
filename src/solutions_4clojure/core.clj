(ns solutions-4clojure.core
  (:gen-class))

;; solutions to problems at 4clojure.com
;; Some easy solutions are excluded

;; #20 - Penultimate Element
;; Write a function which returns the second to last element from a sequence.
(fn [x] (second (reverse x)))

;; #21 - Nth Element
;; Write a function which returns the Nth element from a sequence.
(fn [seq n] (last (take (+ 1 n) seq)))

;; #22 - Count a Sequence
;; Write a function which returns the total number of elements in a sequence.
(fn
  [x]
  (loop [c 0 seq x] (if (empty? seq) c (recur (inc c) (rest seq)))))

;; #23 - Reverse a Sequence
;; Write a function which reverses a sequence.
(fn
  [x]
  (loop
   [rev_seq [] seq x]
    (if
     (empty? seq)
      rev_seq
      (recur (cons (first seq) rev_seq) (rest seq)))))

;; #24 - Sum It All Up
;; Write a function which returns the sum of a sequence of numbers.
#(reduce + %1)

;; #25 - Find the odd numbers
;; Write a function which returns only the odd numbers from a sequence.
#(filter (fn [x] (odd? x)) %1)

;; #26 - Fibonacci Sequence
;; Write a function which returns the first X fibonacci numbers.
#(map (fn fib [n]
        (if (<= n 2)
          1
          (+ (fib (- n 1))
             (fib (- n 2)))))
      (range 1 (+ 1 %1)))

;; #27 - 27Palindrome Detector
;; Write a function which returns true if the given sequence is a palindrome.
#(= (seq %1) (reverse %1))

;; #28 - Flatten a Sequence
;; Write a function which flattens a sequence.
(fn flattenx
  [x]
  (if (sequential? x)
    (if (empty? x)
      '()
      (concat
       (flattenx (first x))
       (flattenx (rest x))))
    [x]))

;; # 29 - Get the Caps
;; Write a function which takes a string and returns a new string containing only the capital letters.
#(reduce str (re-seq #"[A-Z]" %1))

;; # 30 - Compress a Sequence
;; Write a function which removes consecutive duplicates from a sequence.
(fn [x]
  (loop [compressed [] sequence x]
    (if (empty?  sequence)
      compressed
      (if (= (last compressed) (first sequence))
        (recur compressed (rest sequence))
        (recur (concat compressed [(first sequence)]) (rest sequence))))))

;; #31 -  Pack a Sequence 
;; Write a function which packs consecutive duplicates into sub-lists.
(fn packseq
  ([x] (packseq nil [(first x)]  (rest x)))
  ([packed current x]
   (if (empty? x)
     (reverse (conj packed current))
     (if (= (last current) (first x))
       (recur packed (conj current (first x)) (rest x))
       (recur (conj packed current) [(first x)] (rest x))))))

;; #32 - Duplicate a Sequence
;; Write a function which duplicates each element of a sequence.
(fn [x]
  (->> x
       (map (fn [i] [i i]))
       (apply concat)))

;; #33 - Replicate a Sequence
;; Write a function which replicates each element of a sequence a variable number of times.
(fn [x times]
  (->> x
       (map (fn [elt] (repeat times elt)))
       (apply concat)))

;; #34 - Implement range
;; Write a function which creates a list of all integers in a given range.
#(reductions + %1 (repeat (- %2 %1 1) 1))

;; #38 - Maximum value
;; Write a function which takes a variable number of parameters and returns the maximum value.
(fn [& args]
  ((comp first reverse sort) args))

;; #39 - Interleave Two Seqs
;; Write a function which takes two sequences and returns the first item from each, then the second item from each, then the third, etc.
mapcat (fn [& args] args)

;; #40 - Interpose a Seq
;; Write a function which separates the items of a sequence by an arbitrary value.
(fn [x y]
  (cons (first y) (mapcat #(list x %1) (rest y))))

;; #41 - Drop Every Nth Item
;; Write a function which drops every Nth item from a sequence.
(fn [x n]
  (loop [d [] count 1 seq x]
    (if (empty? seq)
      d
      (if (= (mod count n) 0)
        (recur d (inc count) (rest seq))
        (recur (conj d (first seq)) (inc count) (rest seq))))))

;; #42 - Factorial Fun
;; Write a function which calculates factorials.
#(apply * (range 1 (inc %1)))

;; #43 - Reverse Interleave
;; Write a function which reverses the interleave process into x number of subsequences.
#(partition (int (/ (count %1) %2)) (apply interleave (partition %2 %1)))

;; #44 - Rotate Sequence
;; Write a function which can rotate a sequence in either direction.
(fn [nn x]
  (let [len (count x) n (mod nn len)]
    (if (pos? n)
      (take len (drop n (apply concat (repeat 2 x))))
      (take len (drop (+ len n) (apply concat (repeat 2 x)))))))

;; #46 - Flipping out
;; Write a higher-order function which flips the order of the arguments of an input function.
(fn [f]
  (fn [& args] (apply f (reverse args))))

;; #49 - Split a sequence
;; Write a function which will split a sequence into two parts.
#(list (take %1 %2) (drop %1 %2))

;; #50 - Split by Type
;; Write a function which takes a sequence consisting of items with different types and splits 
;; them up into a set of homogeneous sub-sequences. The internal order of each sub-sequence 
;; should be maintained, but the sub-sequences themselves can be returned in any order 
;; (this is why 'set' is used in the test cases).
#(vals (group-by type %1))

