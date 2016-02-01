(ns superiortype.css.fonts
  (:refer-clojure :exclude [* /])
  (:require [garden.def :refer [defstyles]]
            [garden.stylesheet :refer [at-font-face]]
            [garden.arithmetic :refer [* /]]
            [superiortype.css.settings :as set]
            [superiortype.css.mixins :as mix]))

(def fonts-desc
  {:Hrot
   {:normal
    [[100 "Hrot_Hair"]
     [200 "Hrot_Thin"]
     [300 "Hrot_Light"]
     [400 "Hrot_Regular"]
     [500 "Hrot_Medium"]
     [600 "Hrot_Semibold"]
     [700 "Hrot_Bold"]
     [800 "Hrot_Black"]]
    :italic
    [[100 "Hrot_Hair_Italic"]
     [200 "Hrot_Thin_Italic"]
     [300 "Hrot_Light_Italic"]
     [400 "Hrot_Italic"]
     [500 "Hrot_Medium_Italic"]
     [600 "Hrot_Semibold_Italic"]
     [700 "Hrot_Bold_Italic"]
     [800 "Hrot_Black_Italic"]]}
   :KundaBook
   {:normal
    [[400 "Kunda_Book_Regular"]
     [500 "Kunda_Book_Medium"]
     [600 "Kunda_Book_Semibold"]
     [700 "Kunda_Book_Bold"]]
    :italic
    [[400 "Kunda_Book_Italic"]
     [500 "Kunda_Book_Medium_Italic"]
     [600 "Kunda_Book_Semibold_Italic"]
     [700 "Kunda_Book_Bold_Italic"]]}
   :VeganSans
   {:normal
    [[300 "Vegan_Sans_Light"]
     [400 "Vegan_Sans_Regular"]
     [500 "Vegan_Sans_Medium"]
     [600 "Vegan_Sans_Semibold"]
     [700 "Vegan_Sans_Bold"]
     [800 "Vegan_Sans_Black"]]
    :italic
    [[300 "Vegan_Sans_Light_Italic"]
     [400 "Vegan_Sans_Italic"]
     [500 "Vegan_Sans_Medium_Italic"]
     [600 "Vegan_Sans_Semibold_Italic"]
     [700 "Vegan_Sans_Bold_Italic"]
     [800 "Vegan_Sans_Black_Italic"]]}
   :Kendo
   {:normal
    [[400 "Kendo"]]}
   :Signer
   {:normal
    [[400 "Signer"]]}
   :Tungsten
   {:normal
    [[400 "Tungsten"]]}
   :Negramot
   {:normal
    [[400 "Negramot"]]}
   :Dres
   {:normal
    [[400 "Dres_Eight"]]}
   :Kakao
   {:normal
    [[400 "Kakao_Script"]]}
   :MotelSans
   {:normal
    [[400 "Motel_Sans_Regular"]]}
   :MotelSlab
   {:normal
    [[400 "Motel_Slab_Bold"]]}
   :PramenSans
   {:normal
    [[400 "Pramen_Sans_Semibold"]]}
   :PramenSlab
   {:normal
    [[400 "Pramen_Slab_Semibold"]]}
   :Steiner
   {:normal
    [[400 "Steiner_Bold"]]}})

(defstyles faces
  (for [font fonts-desc]
    (let [[font-name styles-groups] font]
      (for [style-group styles-groups]
        (let [[font-style styles] style-group]
          (for [[weight file] styles]
            (at-font-face
              {:font-family font-name
               :font-style font-style
               :font-weight weight
               :src (str "url(\"/fonts/" file ".woff\") format(\"woff\")")})))))))

(defstyles styles
  [:.hair
    {:font-weight 100}]
  [:.thin
    {:font-weight 200}]
  [:.light
    {:font-weight 300}]
  [:.regular
    {:font-weight :normal}]
  [:.medium
    {:font-weight 500}]
  [:.bold
    {:font-weight :bold}]
  [:.semibold
    {:font-weight 600}]
  [:.italic
    {:font-style :italic}]
  [:.black
    {:font-weight 800}]
  [:.vegan-sans
   {:font-family :VeganSans}]
  [:.kunda-book
   {:font-family :KundaBook}]
  [:.hrot
   {:font-family :Hrot}]
  [:.kendo
   {:font-family :Kendo}]
  [:.signer
   {:font-family :Signer}]
  [:.tungsten
   {:font-family :Tungsten}]
  [:.negramot
   {:font-family :Negramot}]
  [:.dres
   {:font-family (mix/important "Dres")}]
  [:.kakao
   {:font-family (mix/important "Kakao")}]
  [:.motel-sans
   {:font-family (mix/important "MotelSans")}]
  [:.motel-slab
   {:font-family (mix/important "MotelSlab")}]
  [:.pramen-sans
   {:font-family (mix/important "PramenSans")}]
  [:.pramen-slab
   {:font-family (mix/important "PramenSlab")}]
  [:.steiner
   {:font-family (mix/important "Steiner")}])

(defn- modular [power]
  (let [f (if (neg? power) / *)]
    (reduce (fn [s _] (apply f [s set/modular])) set/default-size (range 1 power))))

(def size
  {:smaller (modular -1)
   :default (modular 1)
   :bigger (modular 2)
   :big (modular 3)
   :larger (modular 4)
   :large (modular 5)
   :huge (modular 6)
   :epic (modular 7)
   :monster (modular 8)})

