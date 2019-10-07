(defproject coding-test "0.1.0-SNAPSHOT"
  :description "Pizza eaten, lets hack"
  :url "https://example.com"
  :license {:name "GNU GPLv3+"
            :url "https://www.gnu.org/licenses/gpl-3.0.en.html"}
  :dependencies [[compojure "1.6.1"]
                 [org.clojure/clojure "1.10.1"]
                 [respeced "0.0.1"]
                 [ring/ring-core "1.7.1"]
                 [ring/ring-jetty-adapter "1.7.1"]
                 [ring/ring-json "0.5.0"]
                 [ring/ring-mock "0.4.0"]]
  :main coding-test.calculator.core
  :test-paths ["test"])

