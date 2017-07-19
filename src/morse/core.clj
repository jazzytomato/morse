(ns morse.core
  (:gen-class)
  (:require [clojure.edn :as edn]
            [clojure.string :as str]))

(def morse-dictionary (edn/read-string (slurp "resources/morse-dictionary.edn")))
(def alphabet (map char (concat (range 65 91))))

(defn obfuscate [s]
  (-> s
       (str/replace #"\.+" #(str (count %)))
       (str/replace #"\-+" #(str (nth alphabet (- (count %) 1))))))

(defn split-map-join [f re join-s]
  "Returns a fn that takes a string s that will be split with regex re
   Map each item with function f, join the result with string join-s"
  (fn [s]
    (->> (str/split s re)
         (map f)
         (remove nil?)
         (str/join join-s))))

(defn morsify [s]
  "Convert the input string s to morse code"
  ((-> (split-map-join morse-dictionary #"" \|)
       (split-map-join #"\s" \/)
       (split-map-join #"\n" \newline))
    s))

(defn encode [s]
  (if (re-matches #"[a-zA-Z0-9\s\.,]*" s)
    (obfuscate (morsify (str/upper-case s)))
    (throw (ex-info "The input contains invalid characters" {}))))

(defn -main [& args]
  (if-let [file (first args)]
    (spit "output.txt" (encode (slurp file)))
    (do
      (println "Type something to encode:")
      (println (encode (read-line))))))