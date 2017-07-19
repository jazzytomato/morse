(defproject morse "0.1.0-SNAPSHOT"
  :description "Simple morse code parser and obfuscator"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-alpha16"]]
  :main ^:skip-aot morse.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
