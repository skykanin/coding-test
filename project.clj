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
  :plugins [[lein-cljfmt "0.6.4"]] ;[lein-figwheel "0.5.19"]
  ;; :profiles {:dev {:dependencies [[cljs-http "0.1.46"]
  ;;                                 [cider/piggieback "0.4.2"]
  ;;                                 [com.bhauman/figwheel-main "0.2.3"]
  ;;                                 [com.bhauman/rebel-readline-cljs "0.1.4"]
  ;;                                 [org.clojure/clojurescript "1.10.339"]
  ;;                                 [org.clojure/core.async "0.4.500"]
  ;;                                 [reagent "0.9.0-rc1"]]
  ;;                  :repl-options {:nrepl-middleware [cider.piggieback/wrap-cljs-repl]}
  ;;                  :clean-targets ^{:protect false} ["target/public"]
  ;;                  :resource-paths ["resources" "target"]}}
  :test-paths ["test"])
  ;; :aliases {"fig"       ["trampoline" "run" "-m" "figwheel.main"]
  ;;           "fig:build" ["trampoline" "run" "-m" "figwheel.main" "-b" "website" "-r"]})
