(ns morse.core-test
  (:require [clojure.test :refer :all]
            [morse.core :as m :refer :all]
            [clojure.java.io :as io])
  (:import (java.io FileNotFoundException)
           (clojure.lang ExceptionInfo)))

(deftest obfuscate-test
  (testing "encrypt morse code letters and ignore other characters"
    (are [x y] (= (m/obfuscate x) y)
               "" ""
               ".-.." "1A2"
               "--.." "B2"
               "." "1"
               "...." "4"
               "-.-" "A1A"
               "---.--." "C1B1"
               ".-_..+\n" "1A_2+\n")))

(deftest morsify-test
  (testing "encode a string of text into a collection of morse code letters"
    (is (= (m/morsify "") ""))
    (is (= (m/morsify "HELLO!!!!") "....|.|.-..|.-..|---"))))


(deftest encode-test
  (testing "encode a string of text into obfuscated morse code"
    (is (= (m/encode "I AM IN TROUBLE")
           "2/1A|B/2|A1/A|1A1|C|2A|A3|1A2|1")))
  (testing "reject a string containing invalid characters"
    (is (thrown? ExceptionInfo
                 (m/encode "Here's something I can't handle: apostrophes.")))))

(deftest main-test
  (testing "should fail when the argument provided is not a valid file path"
    (is (thrown? FileNotFoundException
                 (m/-main "test/morse/nothing.txt"))))
  (testing "should accept a file as input and produce an output file with encoded data"
    (do
      (m/-main "test/morse/sample.txt")
      (is (.exists (io/file "output.txt")))
      (is (= (slurp "output.txt") "4|1|1A2|1A2|C\n2/1A|B/2|A1/A|1A1|C|2A|A3|1A2|1"))
      (.delete (io/file "output.txt")))))
