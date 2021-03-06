(defproject superiortype "0.3.0-SNAPSHOT"
  :description "Web pages for the Superior Type Foundry"
  :url "http://reframed.superiortype.com"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.7.228"]
                 [org.clojure/core.async "0.2.374"]
                 [reagent "0.6.0-alpha"]
                 [re-frame "0.7.0-alpha"]
                 [secretary "1.2.3"]
                 [facjure/gardener "0.1.0"]
                 [garden "1.3.0"]]

  :source-paths ["src/clj"]

  :plugins [[lein-cljsbuild "1.1.2"]
            [lein-figwheel "0.5.0-3" :exclusions [cider/cider-nrepl]]
            [lein-garden "0.2.6"]]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"
                                    "resources/public/css/compiled/site.css"]

  :garden {:builds [{:id "screen"
                     :source-paths ["src/clj" "src/cljc"]
                     :stylesheet superiortype.css/site
                     :compiler {:vendors ["webkit"]
                                :output-to "resources/public/css/compiled/site.css"
                                :pretty-print? true}}]}

  :figwheel {:css-dirs ["resources/public/css/compiled"]}

  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src/cljs" "src/cljc"]

                        :figwheel {:on-jsload "superiortype.core/mount-root"}

                        :compiler {:main superiortype.core
                                   :output-to "resources/public/js/compiled/app.js"
                                   :output-dir "resources/public/js/compiled/out"
                                   :asset-path "js/compiled/out"
                                   :source-map-timestamp true}}

                       {:id "min"
                        :source-paths ["src/cljs" "src/cljc"]
                        :compiler {:main superiortype.core
                                   :output-to "resources/public/js/compiled/app.js"
                                   :optimizations :advanced
                                   :pretty-print false}}]})
