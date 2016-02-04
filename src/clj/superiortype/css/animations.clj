(ns superiortype.css.animations
  (:require [garden.def :refer [defkeyframes]]
            [garden.units :as u]
            [superiortype.colors :as c]
            [superiortype.css.settings :as s]))

(defkeyframes header-slide-up
  [:0% {:height (u/rem 8)}]
  [:100% {:height (u/rem 2.5)}])

(defkeyframes slide-right
  [:0% {:transform "translateX(-40rem)" :opacity 0}]
  [:70% {:transform "translateX(-3rem)" :opacity 0}]
  [:100% {:transform "translateX(0rem)" :opacity 1}])

(defkeyframes slide-left
  [:0% {:transform "translateX(0rem)" :opacity 1}]
  [:30% {:transform "translateX(-3rem)" :opacity 0}]
  [:100% {:transform "translateX(-40rem)" :opacity 0}])

(defkeyframes slide-up
  [:0% {:transform "translateY(10rem)"}]
  [:100% {:transform "translateY(0rem)"}])

(defkeyframes section-slide-down
  [:0%
   {:opacity 0
    :max-height (u/rem 0)}]
  [:99.9%
   {:opacity 0
    :max-height (u/rem 20)
    :margin-bottom s/default-size}]
  [:100%
   {:opacity 1
    :max-height (u/rem 20)
    :margin-bottom s/default-size}])

(defkeyframes section-slide-up
  [:0% {:opacity 1
        :margin-bottom s/default-size
        :max-height (u/rem 20)}]
  [:100% {:opacity 0}
        :max-height (u/rem 0)])

(defkeyframes bounce-top
  [:0% {:transform "translate(50vw, -10vh) scale(0.5)"}]
  [:30% {:transform "translate(50vw, 40vh) scale(3.5)"}]
  [:70% {:transform "translate(50vw, 40vh) scale(3.5)"}]
  [:100% {:transform "translate(50vw, -10vh) scale(0.5)"}])

(defkeyframes bounce-bottom-left
  [:0% {:transform "translate(0, 110vh) scale(0.5)"}]
  [:20% {:transform "translate(35vw, 40vh) scale(3.5)"}]
  [:70 {:opacity 1}]
  [:80% {:transform "translate(35vw, 40vh) scale(3.0)"
         :opacity 0.9}]
  [:100% {:transform "translate(0, 110vh) scale(0.5)"}])

(defkeyframes bounce-bottom-right
  [:0% {:transform "translate(110vw, 110vh) scale(2.5)"}]
  [:93% {:transform "translate(50vw, 40vh) scale(2.9)"
         :fill c/white}]
  [:97% {:transform "translate(50vw, 40vh) scale(3)"}]
  [:100% {:transform "translate(50vw, 40vh) scale(3)"
          :fill c/white}])

(defkeyframes rainbow
  [:0% {:fill c/electric-violet :transform "translate(2rem, 92vh) scale(1)"}]
  [:40% {:fill c/red :transform "translate(2rem, 88vh) scale(1.2)"}]
  [:50% {:transform "translate(2rem, 88vh) scale(1)"}]
  [:60% {:transform "translate(2rem, 88vh) scale(1.2)"}]
  [:70% {:transform "translate(2rem, 88vh) scale(1)"}]
  [:80% {:fill c/golden-fizz :transform "translate(2rem, 88vh) scale(1.4)"}]
  [:100% {:fill c/white :transform "translate(2rem, 93vh)"}])

(defkeyframes reveal-from-right
  [:0% {:transform "translateX(45%)" :opacity 0.7}]
  [:100% {:transform "translateX(0)" :opacity 1}])

