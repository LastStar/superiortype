(defproject superiortype "0.3.0-SNAPSHOT"
  :description "Web pages for the Superior Type"
  :url "http://reframed.superiortype.com"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.145"]
                 [reagent "0.5.1"]
                 [re-frame "0.4.1"]
                 [secretary "1.2.3"]
                 [facjure/gardener "0.1.0"]
                 [garden "1.2.5"]]

  :source-paths ["src/clj"]

  :plugins [[lein-cljsbuild "1.1.0"]
            [lein-figwheel "0.4.1" :exclusions [cider/cider-nrepl]]
            [lein-garden "0.2.6"] ]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"
                                    "resources/public/css/compiled/site.css"]

  :garden {:builds [{:id "screen"
                     :source-paths ["src/clj"]
                     :stylesheet superiortype.css/site
                     :compiler {:vendors ["webkit"]
                                :output-to "resources/public/css/compiled/site.css"
                                :pretty-print? true}}]}

  :figwheel {:css-dirs ["resources/public/css/compiled"]}

  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src/cljs"]

                        :figwheel {:on-jsload "superiortype.core/mount-root"}

                        :compiler {:main superiortype.core
                                   :output-to "resources/public/js/compiled/app.js"
                                   :output-dir "resources/public/js/compiled/out"
                                   :asset-path "js/compiled/out"
                                   :source-map-timestamp true}}

                       {:id "min"
                        :source-paths ["src/cljs"]
                        :compiler {:main superiortype.core
                                   :output-to "resources/public/js/compiled/app.js"
                                   :optimizations :advanced
                                   :pretty-print false}}]})
