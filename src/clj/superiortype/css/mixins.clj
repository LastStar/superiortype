(ns superiortype.css.mixins
  (:require [garden.units :as u]
            [superiortype.colors :as c]
            [superiortype.css.settings :as set]))

(defn important
  "Adds !important css directive to the content"
  [content]
  [[content "!important"]])

(defn font
  ([size] {:font-size size}))

(defn thin-border
  ([] thin-border c/black)
  ([color] [[:thin :solid color]]))

(def button-padding {:padding [[(u/rem 0.35) set/default-size]]})

(def full-width {:width (u/percent 100)})

(def full-display-width {:width (u/vw 100)})

(def wish-button
  (merge
   button-padding
   (font set/default-size)
   {:cursor :pointer
    :color c/black
    :background-color c/white
    :border-radius set/default-size
    :border (thin-border c/cyan)
    :font-family :VeganSans
    :font-weight :normal}))

(def wish-button-hover
  [:&:hover
   {:background-color c/cyan}])

(def back-button
  (merge
    button-padding
    (font set/default-size)
    {:cursor :pointer
     :color c/black
     :background-color c/nobel
     :border-radius set/default-size
     :border :none
     :font-family :VeganSans
     :font-weight :normal}))

(def back-button-hover
  [:&:hover
   {:background-color c/white}])

