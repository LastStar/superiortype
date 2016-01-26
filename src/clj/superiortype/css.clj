(ns superiortype.css
  (:refer-clojure :exclude [+ - * /])
  (:require [garden.def :refer [defstylesheet defstyles defkeyframes]]
            [garden.units :as u]
            [garden.selectors :as sel]
            [garden.arithmetic :refer [+ - * /]]
            [superiortype.colors :as col]
            [superiortype.css.settings :as set]
            [superiortype.css.animations :as anm]
            [superiortype.css.mixins :as mix]
            [superiortype.css.partials :as prt]))


(defstyles site
  ;; Keyframes
  anm/slide-right
  anm/slide-left
  anm/slide-up
  anm/section-slide-down
  anm/section-slide-up
  anm/header-slide-up
  anm/bounce-top
  anm/bounce-bottom-left
  anm/bounce-bottom-right
  anm/rainbow
  anm/reveal-from-right
  ;; Partials
  prt/basic
  prt/fonts
  prt/media-queries
  [:body
   (mix/font set/default-size)
   {:font-family :VeganSans
    :min-width (u/rem 60)
    :margin :auto
    :line-height 1.5}
   [:&.first-aid {:background-color col/mercury}]
   [:&.custom {:background-color col/emperor}]
   [:header#header
    {:position :relative
     :top 0
     :background-color col/clear
     :z-index 10
     :height set/double-size
     :display :flex
     :flex-direction :row
     :justify-content :space-between}
    [:div.menu
     mix/full-width
     {:margin 0
      :padding [[(u/rem 1.25) set/default-size (u/rem 0.25) set/double-size]]}
     [:h1
      {:transition-property :font-size
       :transition-duration set/default-duration
       :display :inline-block
       :margin 0}
      (mix/font set/bigger-size)
      [:a
       {:font-weight :bold}]]
     [:nav
      {:animation [[anm/slide-left "2500ms" :ease :forwards]]
       :display :inline-block}
      [:a
       {:display :inline-block
        :padding-left set/double-size}
       [:&:first-child {:padding-left set/triple-size}]]
      [:&.visible {:animation [[anm/slide-right set/short-duration :ease :forwards]]}]]]]
   [:section#cart
    {:margin-top set/triple-size
     :background-color col/clear
     :position :absolute}
    [:button.wish-list-button
     mix/wish-button
     {:background-color col/cyan
      :transition-property :font-size
      :transition-duration set/default-duration
      :position :fixed
      :right set/double-size
      :top set/default-size
      :z-index 20}
     mix/wish-button-hover
     [:&.small
      (mix/font set/smaller-size)
      {:top (u/rem 1.2)}]]
    [:div.wish-list
     mix/full-width
     {:background-color col/baby-blue
      :box-shadow "rgba(0,0,0,0.4) 0px 0.125rem 1rem -0.5rem"
      :position :fixed
      :top 0
      :z-index 20}
     [:&.checking-out
      {:background-color col/pale-canary
       :height (u/percent 100)
       :overflow :scroll}
      [:&.superior
       {:background-color col/electric-violet}
       [:h2
        {:font-weight 800
         :font-style :italic}]
       [:table
        {:color col/white}
        [:td :th {:border-color col/white}]]]
      [:div.content
       [:table
        {:width (u/percent 45)
         :transition-duration set/default-duration}
        [:th :td (mix/font set/smaller-size)]
        [:td.name :th.total (mix/font set/default-size)]
        [:tr.superior-list
         [:td
          [:&>div
           {:display :flex
            :justify-content :space-between}
           [:h2
            {:margin-bottom set/default-size}]]]]]
       [:form#checkout
        {:margin "3rem 0 0 3%"
         :width (u/percent 45)
         :float :left
         :transform-origin "center right"
         :transform "translateX(45%)"
         :opacity 0
         :animation-delay "500ms"
         :animation [[anm/reveal-from-right set/default-duration :forwards :ease-out]]}
        [:.column
         {:width (u/percent 50)
          :float :left}
         [:label
          {:display :block
           :margin-bottom set/default-size}
          [:&.grey {:color col/nobel}]
          [:input
           (mix/font set/default-size)
           {:display :block
            :border-radius "0.5rem"
            :padding "0.5rem"
            :width (u/percent 75)}
           [:&.zip {:width (u/percent 25)}]]
          [:select
           (mix/font set/default-size)
           ^:prefix {:appearance :none}
           {:background "url('/img/down-arrow.svg') #fff no-repeat center right 0.5rem"
            :display :block
            :width (u/percent 80)
            :border-radius (u/rem (* set/modular 0.25))
            :background-color col/white
            :padding "0.5rem"}]]
         [:div.pay-box
          {:margin-top (u/rem 8)}
          [:a {:text-decoration :underline}]
          [:input
           ^:prefix {:appearance :none}
           {:background-image "url('/img/check-unchecked.svg')"
            :background-repeat :no-repeat
            :background-origin 0
            :background-size :cover
            :margin-top set/default-size
            :width set/double-size
            :height set/double-size}
           [:&:checked {:background-image "url('/img/check-checked.svg')"}]]
          [:button
           (mix/font set/bigger-size)
           {:padding "0.5rem 1rem"
            :color col/black
            :border-radius set/big-size
            :border :none
            :background-color col/white
            :margin "3rem 0"
            :cursor :pointer}
           [:&:hover
            {:background-color col/salmon}]]]]]]]
     [:header
      {:display :flex
       :justify-content :space-between
       :align-items "flex-start"}
      [:h2
       (mix/font set/larger-size)
       {:margin "3rem 3rem 1rem"
        :font-weight 600}]
      [:button.back-button
       mix/back-button
       {:margin "3rem 2rem 0"}
       mix/back-button-hover
       [:&.wish-list
        {:background-color col/white}
        [:&:hover
         {:background-color col/baby-blue}]]]]]
    [:div.content
     {:display :flex}
     [:table
      {:transition-property :width
       :transition-duration "0ms"
       :border-collapse :collapse
       :margin "2rem 2rem 2rem 3rem"
       :width "calc(100vw - 5rem)"}
      [:tbody>tr:last-child
       [:th :td {:border-bottom :none}]]
      [:th :td
       {:font-weight :normal
        :text-align :left
        :padding set/default-size
        :border-bottom "thin solid col/black"}
       [:&.price :&.remove
        {:width (u/rem 7)
         :text-align :right}]
       [:&.total
        {:text-align :right}]
       [:&.checkout
        {:text-align :right}
        [:button.checkout
         mix/button-padding
         {:border-radius set/bigger-size
          :border :none
          :background col/white
          :float :right}
         [:&:hover {:background col/pale-canary}]]]]
      [:button
       (mix/font set/default-size)
       {:border :none
        :background :none
        :cursor :pointer
        :font-family :VeganSans}]
      [:select
       ^:prefix {:appearance :none}
       (mix/font set/default-size)
       {:border-radius set/bigger-size
        :padding "0.5rem 2rem 0.5rem 1rem"
        :background "url('/img/down-arrow.svg') #fff no-repeat center right 0.5rem"}]]]]
   [:main#app
    [:&>.fade
     {:opacity 0.4}
     [:button {:display (mix/important "none")}]]
    [:svg
     {:width "100vw"
      :height "95vh"}
     [:g
      [:text
       (mix/font set/huge-size)]]
     [:g.top
      {:animation [[anm/bounce-top "3s" :infinite :alternate :ease]]}
      [:text
       {:font-family :VeganSans
        :font-weight 600
        :fill col/golden-fizz}]
      [:circle
       {:stroke col/golden-fizz
        :stroke-width 24
        :fill col/clear}]]
     [:g.bottom-right
      {:animation [[anm/bounce-bottom-right "2s" :infinite :alternate :ease-out]]}
      [:text
       {:font-family :KundaBook
        :font-style :Italic}]
      [:circle
       {:stroke :none
        :fill col/red}]]
     [:g.bottom-left
      {:animation [[anm/bounce-bottom-left "1.44s" :infinite]]}
      [:text
       {:font-family :Hrot
        :font-weight 200
        :fill col/electric-violet}]
      [:circle
       {:stroke col/electric-violet
        :fill col/white}]]
     [:circle.down
      {:animation [[anm/rainbow "4s" :infinite]]
       :cursor :pointer}]]
    [:header#mix/font
     mix/full-width
     {:display :flex
      :padding "1rem 0 2rem"
      :justify-content :space-between
      :align-items :flex-end
      :box-shadow "rgba(0,0,0,0.2) 0px 0.125rem 1rem 0px"
      :position :fixed
      :top 0
      :height (u/rem 8)
      :z-index 3
      :background-color col/white}
     [:&.small
      (mix/font set/smaller-size)
      {:padding-bottom "1.25rem"
       :animation [[anm/header-slide-up "500ms" :ease :forwards]]}
      [:&:hover :&.hover
       [:&.fade
        {:display :none
         :opacity 0}]]
      [:nav.fonts
       [:h2
        (mix/font set/bigger-size)]
       [:a
        {:margin-bottom "0.5rem"
         :width set/double-size
         :height set/default-size}]]
      [:button
       (mix/font set/smaller-size)
       {:transition-property "margin-bottom, margin-right"
        :transition-duration set/short-duration
        :margin-bottom 0}
       [:&.right-spaced {:margin-right (u/rem 9)}]]
      [:nav.sections
       {:margin-bottom 0}]]
     [:&.fade
      {:display :none
       :opacity 0}]
     [:nav.fonts
      {:display :flex
       :margin-left set/double-size
       :align-items :center}
      [:div.links
       {:display :flex
        :width (u/rem 6)}]
      [:h2
       (mix/font set/huge-size)
       {:transition-property "font-size, width"
        :transition-duration set/default-duration
        :font-weight :normal
        :display :inline-block
        :margin 0
        :width (u/rem 25)
        :font-family :inherit}]
      [:a
       {:transition-property "width, height, margin-bottom"
        :transition-duration set/default-duration
        :background-position-y 0
        :background-position-x (u/percent 50)
        :background-repeat "no-repeat"
        :background-color "rgba(0, 0, 0, 0)"
        :background-size :contain
        :display :inline-block
        :border :none
        :width set/double-size
        :height set/double-size
        :color "rgba(0, 0, 0, 0)"}
       [:&.previous
        {:background-image "url(/img/previous.svg)"}]
       [:&.next
        {:background-image "url(/img/next.svg)"}]]]
     [:div.buttons
      {:display :flex
       :width (u/percent 55)
       :justify-content :space-between
       :margin-right set/double-size}]
     [:nav.sections
      {:transition-property "height, margin-bottom"
       :transition-duration set/default-duration
       :display :flex
       :align-items "flex-start"
       :justify-content :space-between
       :margin-bottom set/default-size}
      [:a
       {:font-family :VeganSans
        :padding "0.33rem 1rem"
        :text-align :center
        :border-radius set/default-size
        :transition-property "background-color"
        :transition-duration set/short-duration
        :cursor :pointer}
       [:&:active
        {:transform "scale(0.8)"
         :transition-property :transform
         :transition-duration set/short-duration}]
       [:&:hover {:background-color col/alabaster}]
       [:&.active
        {:font-weight 700
         :background-color col/nobel
         :transition-duration set/short-duration}]]]
     [:button
      mix/wish-button
      {:margin-bottom set/default-size}
      mix/wish-button-hover]
     prt/wish-box]
    [:&>div>div>.wish-box:last-child
     {:top (u/rem 9)
      :padding-top set/quadruple-size
      :position :fixed
      :z-index 2
      :background-color col/white
      :padding set/triple-size
      :overflow :hidden
      :margin 0}]
    [:ul#fonts
     [:li {:cursor :pointer}
      [:div [:a {:padding 0}]]
      [:ul.styles {:margin 0}
       [:li:first-child {:padding-top 0}]]]]
    [:ul#fonts :ul.styles
     {:padding 0
      :margin "0 2rem"}
     [:li
      {:border-bottom (mix/thin-border col/nobel)
       :padding-top set/default-size}
      [:&.fade {:opacity "0.1"}]
      [:&.false {:background-color "#fffffe"}]
      [:div.tools
       {:font-family (mix/important "VeganSans")}
       [:&>div {:float :left}]
       [:button
        (mix/font set/smaller-size)
        {:background-position-x 0
         :background-position-y (u/percent 50)
         :background-repeat "no-repeat"
         :background-color "rgba(0, 0, 0, 0)"
         :display :inline-block
         :margin 0
         :border :none
         :cursor :pointer}
        [:&:first-child {:padding-right "0.5rem"}]
        [:&:last-child {:padding-left "0.5rem"}]
        [:&.wish
         (mix/font set/bigger-size)
         mix/wish-button
         {:width :auto
          :height :auto
          :float :right
          :display :block
          :transition "transform 250ms"
          :transform "translateY(4rem)"
          :position :relative
          :z-index 2}
         mix/wish-button-hover]
        [:&.smaller
         [:&:hover
          {:transform "scale(0.8)"}]
         [:&:active
          {:transform "scale(1.0)"}]]
        [:&.list:hover {:transform "translateY(0.3rem)"}]
        [:&.bigger
         [:&:hover {:transform "scale(1.2)"}]
         [:&:active {:transform "scale(1.0)"}]]]
       [:a (mix/font set/smaller-size)]
       [:span
        {:display :inline-block
         :text-align :center
         :width set/quadruple-size}
        [:&.name
         (mix/font set/smaller-size)
         {:width (u/rem 20)
          :text-align :left
          :padding-left set/default-size}]]]
      [:a
       {:display :block
        :padding "2rem 0"
        :font-family :inherit
        :font-weight :inherit
        :font-style :inherit}]
      [:input
       {:width "calc(100vw - 2rem)"
        :position :relative
        :border :none
        :padding "2rem 0"
        :font-family :inherit
        :font-weight :inherit
        :font-style :inherit}]]
     prt/wish-box]
    [:section#styles
     [:ul.styles
      {:padding-top (u/rem 9)}]]
    [:section#glyphs
     {:margin "6rem 6rem 0"}
     [:select
      ^:prefix {:appearance :none}
      {:top (u/rem 8)
       :position :fixed
       :border-radius set/default-size
       :padding "0.75rem 1rem"
       :margin [[set/double-size 0]]
       :left "-30rem"
       :z-index 10
       :opacity 0}
      [:&.visible
       {:opacity 0.9
        :left set/default-size}
       (mix/font set/default-size)]]
     [:img
      mix/full-width
      {:padding [[set/triple-size 0]]}]]
    [:section#details
     {:margin "0 6rem"
      :padding "6rem 0"}
     [:.main {:display :flex}
      [:.description
       {:width (u/percent 73)
        :margin-right (u/percent 2)}
       (mix/font set/bigger-size)]
      [:.features
       {:width (u/percent 20)
        :padding-left (u/percent 5)}]]
     [:div.opentype
      {:margin-top set/double-size
       :clear :both}
      [:img
       mix/full-width]]]
    [:section#inuse
     mix/full-width
     {:margin-top (u/rem 6)
      :overflow :hidden
      :float :left}
     [:nav
      {:transform "translate(2rem, 32vw)"
       :position :absolute
       :width "96vw"
       :z-index 2}
      [:div
       {:width (u/percent 50)
        :height set/double-size
        :float :left}
       [:&:last-child {:text-align :right}]
       [:button
        mix/button-padding
        {:display :inline-block
         :background-repeat "no-repeat"
         :background-color col/white
         :opacity 0.8
         :margin 0
         :padding-bottom "0.5rem"
         :border :none
         :border-radius set/default-size
         :cursor :pointer}]]]
     [:div.figure-wrapper
      {:transition "transform cubic-bezier(0.4, 0, 0.2, 1) 1s"
       :z-index 1}
      [:figure
       mix/full-width
       {:margin 0
        :float :left}
       [:figcaption
        {:transform "translate(2rem, 2rem)"
         :background-color col/white
         :border-radius set/double-size
         :padding "0.25rem 1rem"
         :opacity "0.8"
         :position :absolute}
        [:span
         {:padding [[0 set/default-size 0 0]]
          :display :inline-block}
         [:&:last-child {:padding 0}]
         [:&:first-child
          {:font-weight :bold}]]]
       [:img
        mix/full-width
        {:max-height "100vw"
         :float :left}]]]]
    [:section#foundry
     [:header
      [:h2
       (mix/font set/larger-size)
       {:width (u/percent 48)
        :margin "0 10%"
        :padding-top (u/rem 6)
        :padding-bottom set/double-size
        :font-weight :normal}]]
     [:div.about-address
      {:background-color col/spring-green
       :display :flex}
      [:div.text
       (mix/font set/bigger-size)
       {:flex 6
        :margin-left (u/percent 10)
        :padding-top set/quadruple-size}]]
     [:div.contact
      {:flex 8
       :overflow :visible}
      [:address
       (mix/font set/larger-size)
       {:transition-property :transform
        :transition-duration "1750ms"
        :transition-timing-function "cubic-bezier(0.18, 0.89, 0.32, 1.28)"
        :margin-left (u/percent 10)
        :width (u/percent 70)
        :padding set/triple-size
        :font-style :normal
        :background-color col/nobel
        :margin-top set/triple-size
        :margin-bottom set/default-size
        :border-radius set/double-size}
       [:&.visible {:transform "translateX(0rem)"}]
       [:&.hidden {:transform "translateX(70rem)"}]
       [:a
        (mix/font set/bigger-size)
        {:border-radius set/triple-size
         :padding "0.5rem 1rem"}
        [:&:hover
         {:background-color (mix/important col/white)
          :color (mix/important col/black)}]]
       [:div.email
        {:margin-top set/default-size}
        [:a
         (mix/font set/bigger-size)
         {:color col/white
          :background-color col/emperor}]]
       [:nav.social
        {:margin-top set/triple-size
         :line-height 1
         :display :flex
         :justify-content :space-between}
        [:a
         {:color col/white}
         [:&.facebook
          {:background-color "#0060ff"}]
         [:&.twitter
          {:background-color "#00beff"}]
         [:&.instagram
          {:background-color "#a04aff"}]]]]]
     [:.lost
      mix/full-width
      {:background-color col/spring-green}
      [:p
       (mix/font set/bigger-size)
       {:margin-left (u/percent 10)
        :padding-top set/large-size
        :width (u/percent 40)}]
      [:.fonts
       {:width (u/percent 90)
        :margin-left (u/percent 10)}
       [:span
        {:padding "0 2rem 0 0"
         :display :inline-block
         :line-height 1.5}
        [:a
         {:display :inline-block
          :transition-property :transform
          :transition-duration set/short-duration
          :font-size "6vw"
          :color col/white
          :padding "0 1rem 0 0"
          :font-family :inherit}
         [:&:hover
          {:transform "scale(1.10)"
           :color col/black}]]]]]
     [:.people
      {:background-color col/spring-green
       :display :flex}
      [:.person
       {:width (u/percent 48)
        :margin-top set/triple-size
        :padding set/double-size}
       [:.photo
        [:img
         mix/full-width
         {:border-radius (u/percent 50)}
         [:p
          (mix/font set/bigger-size)]]]]]
     [:.studio
      [:img
       {:border :none
        :padding 0
        :margin 0
        :vertical-align :middle
        :width (u/percent 100)}]]]
    [:section#custom
     {:position :absolute
      :top 0}
     [:header
      [:h2
       (mix/font set/large-size)
       {:color col/purple
        :width (u/percent 80)
        :margin "0 10%"
        :padding-top (u/rem 6)
        :padding-bottom set/double-size
        :font-weight :normal}]]
     [:div.text
      {:width (u/percent 40)
       :margin "0 10% 0 10%"
       :float :left}
      [:p (mix/font set/bigger-size)
       [:span.email
        {:margin-top set/default-size}
        [:a
         mix/button-padding
         {:display :inline-block
          :color col/white
          :border-radius set/default-size
          :background-color col/black}
         [:&:hover
          {:background-color (mix/important col/white)
           :color (mix/important col/black)}]]]]]
     [:ul.advantages
      {:width (u/percent 30)
       :margin "0 10% 0 0"
       :float :left}
      [:li {:margin "0 0 2rem 0"}
       [:&:before
        (mix/font set/bigger-size)
        {:content "\"• \""}]
       [:&.red:before {:color col/red}]
       [:&.yellow:before {:color col/golden-fizz}]]]
     [:div.cases
      {:clear :both
       :font-family :VeganSans}
      [:&>div
       {:margin-bottom set/quadruple-size}
       [:&.fade {:opacity 0.4}]
       [:h3
        (mix/font set/monster-size)
        {:margin 0
         :padding 0}]
       [:div.details
        {:width (u/percent 80)
         :margin "0 10% 0 10%"
         :display :flex
         :justify-content :space-between}
        [:&>div
         {:margin "2rem 1rem"
          :font-family :VeganSans}]]
       [:img mix/full-width]]]]
    [:section#first-aid
     {:display :flex
      :padding set/double-size}
     [:div.column
      {:flex 1
       :height (u/percent 100)
       :margin-right set/double-size}
      [:h2
       (mix/font set/default-size)
       {:font-weight :normal
        :width (u/percent 75)
        :margin-bottom set/double-size
        :line-height set/bigger-size}]
      [:h3
       (mix/font set/bigger-size)
       {:font-weight :normal
        :margin-bottom (u/rem 0.5)}
       [(sel/& (sel/not (sel/first-child))) {:margin-top set/triple-size}]]
      [:h4
       {:margin-bottom set/default-size}
       [:button
        mix/button-padding
        (mix/font set/default-size)
        {:background-color col/gallery
         :border-radius set/default-size
         :cursor :pointer
         :position :relative
         :z-index 2
         :border :none}
        [:&.opened {:background-color col/paris-daisy}]]]
      [:&:first-child
       [:p
        (mix/font set/smaller-size)
        {:width (u/percent 75)
         :margin-bottom set/default-size}]]
      [(sel/& (sel/not (sel/first-child)))
       [:p
        (mix/font set/smaller-size)
        {:margin [[0 set/double-size 0 set/default-size]]
         :width (u/percent 75)
         :max-height (u/rem 0)
         :position :relative
         :z-index 1
         :opacity 0}
        [:&.opened
         {:animation [[anm/section-slide-down set/default-duration :alternate :forwards]]}]
        [:&.closed
         {:animation [[anm/section-slide-up set/short-duration :alternate :forwards]]}]]]]]]])
