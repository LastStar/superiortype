(ns superiortype.css.partials
  (:require [garden.def :refer [defstyles]]
            [garden.units :as u]
            [garden.stylesheet :refer [at-font-face at-media]]
            [gardener.respond :as respond]
            [superiortype.colors :as col]
            [superiortype.css.animations :as anm]
            [superiortype.css.mixins :as mix]
            [superiortype.css.settings :as set]
            [superiortype.css.fonts :as font]))

(defstyles basic
  [:h1 :h2 :h3 :h4 :h5 :a :a:hover :select :input
   {:font-family :VeganSans
    :border :none
    :padding 0
    :margin 0
    :line-height 1.2}]
  [:a
   {:color col/black
    :text-decoration :none
    :font-weight :normal}]
  [:ul
   {:list-style-type :none
    :-webkit-padding-start 0}]
  [:button
   (mix/font (:default font/size))
   {:font-family :VeganSans}]
  [:input:focus :button:focus :select:focus {:outline :none}]
  ["::-webkit-input-placeholder" {:color col/black}]
  ["::-moz-placeholder" {:color col/black}]
  [":-ms-input-placeholder" {:color col/black}]
  [:input:-moz-placeholder {:color col/black}]
  [:p {:margin 0}])

(defstyles wish-box
 [:div.wish-box
  {:display :flex
   :flex-direction :row
   :align-items :flex-end
   :justify-content :space-between
   :clear :both
   :overflow :hidden}
  [:&>div
   {:font-family :VeganSans
    :width (u/percent 23)
    :margin set/default-space
    :padding set/default-space
    :border-radius set/default-space
    :cursor :pointer
    :transform "translateY(30rem)"
    :animation [[anm/slide-up set/long-duration :forwards :ease-out]]}
   [:&:first-child {:margin-left 0}]
   [:header
    {:display :flex
     :justify-content :space-between
     :align-items :center}
    [:h5
     (mix/font (:bigger font/size))]
    [:.price
     (mix/font (:smaller font/size))
     {:font-family :VeganSans
      :display :inline-block
      :border-radius set/default-space
      :padding [[(u/rem 0.35) (u/rem 0.5) (u/rem 0.25)]]
      :background-color col/white
      :text-align :center}]]
   [:.description
    {:clear :both
     :transition-property :opacity
     :transition-duration set/short-duration
     :opacity 0}
    [:h6
     (mix/font (:default font/size))
     {:margin [[set/double-space 0 0]]}]
    [:p {:margin 0}]]
   [:&:hover
    [:.description {:opacity 1}]]
   [:&.demo
    {:background-color col/concrete}
    [:header
     [:h5
      {:margin 0
       :font-style :italic
       :font-weight 300}]
     [:.price {:margin "0.3rem 0 0.25rem 0"}]]]
   [:&.basic
    {:background-color col/baby-blue
     :animation-delay set/short-duration}
    [:h5 {:font-weight :normal}]]
   [:&.premium
    {:background-color col/cyan
     :animation-delay set/long-duration}]
   [:&.superior
    {:background-color col/electric-violet
     :color col/white
     :animation-delay set/longest-duration
     :max-height (u/rem 20)}
    [:h5
     {:font-weight 900}]
    [:.price
     {:color col/black}]
    [:.description
     {:margin "10rem 0 0 0"}]]]
  [:button
   mix/back-button
   {:margin-bottom set/default-space}]])

(defstyles media-queries
  (respond/tablet
    [:html
     (mix/font (u/px 13))])
  (respond/laptop
    [:html
     (mix/font (u/px 14))])
  (respond/desktop
    [:html
     (mix/font (u/px 20))])
  (respond/hd
    [:html
     (mix/font (u/px 26))]))

