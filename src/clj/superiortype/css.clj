(ns superiortype.css
  (:refer-clojure :exclude [+ - * /])
  (:require [garden.def :refer [defstylesheet defstyles defkeyframes]]
            [garden.units :as u]
            [garden.selectors :as sel]
            [garden.arithmetic :refer [+ - * /]]
            [garden.stylesheet :refer [at-font-face at-media]]
            [gardener.respond :as respond]
            [superiortype.colors :as c]
            [superiortype.css.settings :as s]
            [superiortype.css.mixins :as m]
            [superiortype.css.animations :as an]))

(defstyles basic
  [:h1 :h2 :h3 :h4 :h5 :a :a:hover :select :input
   {:font-family :VeganSans
    :border :none
    :padding 0
    :margin 0
    :line-height 1.2}]
  [:a
   {:color c/black
    :text-decoration :none
    :font-weight :normal}]
  [:ul
   {:list-style-type :none
    :-webkit-padding-start 0}]
  [:button
   (m/font s/default-size)
   {:font-family :VeganSans}]
  [:input:focus :button:focus :select:focus {:outline :none}]
  ["::-webkit-input-placeholder" {:color c/black}]
  ["::-moz-placeholder" {:color c/black}]
  [":-ms-input-placeholder" {:color c/black}]
  [:input:-moz-placeholder {:color c/black}]
  [:p {:margin 0}])

(defstyles fonts
  ;; FIXME dynamic m/font styles for all like this
  (at-font-face
    {:font-family :Hrot
     :font-style :normal
     :font-weight 100
     :src "url(\"/fonts/Hrot_Hair.woff\") format(\"woff\")"})
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
   {:font-family (m/important "Dres")}]
  [:.kakao
   {:font-family (m/important "Kakao")}]
  [:.motel-sans
   {:font-family (m/important "MotelSans")}]
  [:.motel-slab
   {:font-family (m/important "MotelSlab")}]
  [:.pramen-sans
   {:font-family (m/important "PramenSans")}]
  [:.pramen-slab
   {:font-family (m/important "PramenSlab")}]
  [:.steiner
   {:font-family (m/important "Steiner")}])

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
    :margin s/default-size
    :padding s/default-size
    :border-radius s/default-size
    :cursor :pointer
    :transform "translateY(30rem)"
    :animation [[an/slide-up s/long-duration :forwards :ease-out]]}
   [:&:first-child {:margin-left 0}]
   [:header
    {:display :flex
     :justify-content :space-between
     :align-items :center}
    [:h5
     (m/font s/bigger-size)]
    [:.price
     (m/font s/smaller-size)
     {:font-family :VeganSans
      :display :inline-block
      :border-radius s/default-size
      :padding [[(u/rem 0.35) (u/rem 0.5) (u/rem 0.25)]]
      :background-color c/white
      :text-align :center}]]
   [:.description
    {:clear :both
     :transition-property :opacity
     :transition-duration s/short-duration
     :opacity 0}
    [:h6
     (m/font s/default-size)
     {:margin [[s/double-size 0 0]]}]
    [:p {:margin 0}]]
   [:&:hover
    [:.description {:opacity 1}]]
   [:&.demo
    {:background-color c/concrete}
    [:header
     [:h5
      {:margin 0
       :font-style :italic
       :font-weight 300}]
     [:.price {:margin "0.3rem 0 0.25rem 0"}]]]
   [:&.basic
    {:background-color c/baby-blue
     :animation-delay s/short-duration}
    [:h5 {:font-weight :normal}]]
   [:&.premium
    {:background-color c/cyan
     :animation-delay s/long-duration}]
   [:&.superior
    {:background-color c/electric-violet
     :color c/white
     :animation-delay s/longest-duration
     :max-height (u/rem 20)}
    [:h5
     {:font-weight 900}]
    [:.price
     {:color c/black}]
    [:.description
     {:margin "10rem 0 0 0"}]]]
  [:button
   m/back-button
   {:margin-bottom s/default-size}]])

(defstyles media-queries
  (respond/tablet
    [:html
     (m/font (u/px 13))])
  (respond/laptop
    [:html
     (m/font (u/px 14))])
  (respond/desktop
    [:html
     (m/font (u/px 20))])
  (respond/hd
    [:html
     (m/font (u/px 26))]))

(defstyles site
  ;; Keyframes
  an/slide-right
  an/slide-left
  an/slide-up
  an/section-slide-down
  an/section-slide-up
  an/header-slide-up
  an/bounce-top
  an/bounce-bottom-left
  an/bounce-bottom-right
  an/rainbow
  an/reveal-from-right
  ;; Partials
  basic
  fonts
  media-queries
  [:body
   (m/font s/default-size)
   {:font-family :VeganSans
    :min-width (u/rem 60)
    :margin :auto
    :line-height 1.5}
   [:&.first-aid {:background-color c/mercury}]
   [:&.custom {:background-color c/emperor}]
   [:header#header
    {:position :relative
     :top 0
     :background-color c/clear
     :z-index 10
     :height s/double-size
     :display :flex
     :flex-direction :row
     :justify-content :space-between}
    [:div.menu
     m/full-width
     {:margin 0
      :padding [[(u/rem 1.25) s/default-size (u/rem 0.25) s/double-size]]}
     [:h1
      {:transition-property :font-size
       :transition-duration s/default-duration
       :display :inline-block
       :margin 0}
      (m/font s/bigger-size)
      [:a
       {:font-weight :bold}]]
     [:nav
      {:animation [[an/slide-left "2500ms" :ease :forwards]]
       :display :inline-block}
      [:a
       {:display :inline-block
        :padding-left s/double-size}
       [:&:first-child {:padding-left s/triple-size}]]
      [:&.visible {:animation [[an/slide-right s/short-duration :ease :forwards]]}]]]]
   [:section#cart
    {:margin-top s/triple-size
     :background-color c/clear
     :position :absolute}
    [:button.wish-list-button
     m/wish-button
     {:background-color c/cyan
      :transition-property :font-size
      :transition-duration s/default-duration
      :position :fixed
      :right s/double-size
      :top s/default-size
      :z-index 20}
     m/wish-button-hover
     [:&.small
      (m/font s/smaller-size)
      {:top (u/rem 1.2)}]]
    [:div.wish-list
     m/full-width
     {:background-color c/baby-blue
      :box-shadow "rgba(0,0,0,0.4) 0px 0.125rem 1rem -0.5rem"
      :position :fixed
      :top 0
      :z-index 20}
     [:&.checking-out
      {:background-color c/pale-canary
       :height (u/percent 100)
       :overflow :scroll}
      [:&.superior
       {:background-color c/electric-violet}
       [:h2
        {:font-weight 800
         :font-style :italic}]
       [:table
        {:color c/white}
        [:td :th {:border-color c/white}]]]
      [:div.content
       [:table
        {:width (u/percent 45)
         :transition-duration s/default-duration}
        [:th :td (m/font s/smaller-size)]
        [:td.name :th.total (m/font s/default-size)]
        [:tr.superior-list
         [:td
          [:&>div
           {:display :flex
            :justify-content :space-between}
           [:h2
            {:margin-bottom s/default-size}]]]]]
       [:form#checkout
        {:margin "3rem 0 0 3%"
         :width (u/percent 45)
         :float :left
         :transform-origin "center right"
         :transform "translateX(45%)"
         :opacity 0
         :animation-delay "500ms"
         :animation [[an/reveal-from-right s/default-duration :forwards :ease-out]]}
        [:.column
         {:width (u/percent 50)
          :float :left}
         [:label
          {:display :block
           :margin-bottom s/default-size}
          [:&.grey {:color c/nobel}]
          [:input
           (m/font s/default-size)
           {:display :block
            :border-radius "0.5rem"
            :padding "0.5rem"
            :width (u/percent 75)}
           [:&.zip {:width (u/percent 25)}]]
          [:select
           (m/font s/default-size)
           ^:prefix {:appearance :none}
           {:background "url('/img/down-arrow.svg') #fff no-repeat center right 0.5rem"
            :display :block
            :width (u/percent 80)
            :border-radius (u/rem (* s/modular 0.25))
            :background-color c/white
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
            :margin-top s/default-size
            :width s/double-size
            :height s/double-size}
           [:&:checked {:background-image "url('/img/check-checked.svg')"}]]
          [:button
           (m/font s/bigger-size)
           {:padding "0.5rem 1rem"
            :color c/black
            :border-radius s/big-size
            :border :none
            :background-color c/white
            :margin "3rem 0"
            :cursor :pointer}
           [:&:hover
            {:background-color c/salmon}]]]]]]]
     [:header
      {:display :flex
       :justify-content :space-between
       :align-items "flex-start"}
      [:h2
       (m/font s/larger-size)
       {:margin "3rem 3rem 1rem"
        :font-weight 600}]
      [:button.back-button
       m/back-button
       {:margin "3rem 2rem 0"}
       m/back-button-hover
       [:&.wish-list
        {:background-color c/white}
        [:&:hover
         {:background-color c/baby-blue}]]]]]
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
        :padding s/default-size
        :border-bottom "thin solid c/black"}
       [:&.price :&.remove
        {:width (u/rem 7)
         :text-align :right}]
       [:&.total
        {:text-align :right}]
       [:&.checkout
        {:text-align :right}
        [:button.checkout
         m/button-padding
         {:border-radius s/bigger-size
          :border :none
          :background c/white
          :float :right}
         [:&:hover {:background c/pale-canary}]]]]
      [:button
       (m/font s/default-size)
       {:border :none
        :background :none
        :cursor :pointer
        :font-family :VeganSans}]
      [:select
       ^:prefix {:appearance :none}
       (m/font s/default-size)
       {:border-radius s/bigger-size
        :padding "0.5rem 2rem 0.5rem 1rem"
        :background "url('/img/down-arrow.svg') #fff no-repeat center right 0.5rem"}]]]]
   [:main#app
    [:&>.fade
     {:opacity 0.4}
     [:button {:display (m/important "none")}]]
    [:svg
     {:width "100vw"
      :height "95vh"}
     [:g
      [:text
       (m/font s/huge-size)]]
     [:g.top
      {:animation [[an/bounce-top "3s" :infinite :alternate :ease]]}
      [:text
       {:font-family :VeganSans
        :font-weight 600
        :fill c/golden-fizz}]
      [:circle
       {:stroke c/golden-fizz
        :stroke-width 24
        :fill c/clear}]]
     [:g.bottom-right
      {:animation [[an/bounce-bottom-right "2s" :infinite :alternate :ease-out]]}
      [:text
       {:font-family :KundaBook
        :font-style :Italic}]
      [:circle
       {:stroke :none
        :fill c/red}]]
     [:g.bottom-left
      {:animation [[an/bounce-bottom-left "1.44s" :infinite]]}
      [:text
       {:font-family :Hrot
        :font-weight 200
        :fill c/electric-violet}]
      [:circle
       {:stroke c/electric-violet
        :fill c/white}]]
     [:circle.down
      {:animation [[an/rainbow "4s" :infinite]]
       :cursor :pointer}]]
    [:header#m/font
     m/full-width
     {:display :flex
      :padding "1rem 0 2rem"
      :justify-content :space-between
      :align-items :flex-end
      :box-shadow "rgba(0,0,0,0.2) 0px 0.125rem 1rem 0px"
      :position :fixed
      :top 0
      :height (u/rem 8)
      :z-index 3
      :background-color c/white}
     [:&.small
      (m/font s/smaller-size)
      {:padding-bottom "1.25rem"
       :animation [[an/header-slide-up "500ms" :ease :forwards]]}
      [:&:hover :&.hover
       [:&.fade
        {:display :none
         :opacity 0}]]
      [:nav.fonts
       [:h2
        (m/font s/bigger-size)]
       [:a
        {:margin-bottom "0.5rem"
         :width s/double-size
         :height s/default-size}]]
      [:button
       (m/font s/smaller-size)
       {:transition-property "margin-bottom, margin-right"
        :transition-duration s/short-duration
        :margin-bottom 0}
       [:&.right-spaced {:margin-right (u/rem 9)}]]
      [:nav.sections
       {:margin-bottom 0}]]
     [:&.fade
      {:display :none
       :opacity 0}]
     [:nav.fonts
      {:display :flex
       :margin-left s/double-size
       :align-items :center}
      [:div.links
       {:display :flex
        :width (u/rem 6)}]
      [:h2
       (m/font s/huge-size)
       {:transition-property "font-size, width"
        :transition-duration s/default-duration
        :font-weight :normal
        :display :inline-block
        :margin 0
        :width (u/rem 25)
        :font-family :inherit}]
      [:a
       {:transition-property "width, height, margin-bottom"
        :transition-duration s/default-duration
        :background-position-y 0
        :background-position-x (u/percent 50)
        :background-repeat "no-repeat"
        :background-color "rgba(0, 0, 0, 0)"
        :background-size :contain
        :display :inline-block
        :border :none
        :width s/double-size
        :height s/double-size
        :color "rgba(0, 0, 0, 0)"}
       [:&.previous
        {:background-image "url(/img/previous.svg)"}]
       [:&.next
        {:background-image "url(/img/next.svg)"}]]]
     [:div.buttons
      {:display :flex
       :width (u/percent 55)
       :justify-content :space-between
       :margin-right s/double-size}]
     [:nav.sections
      {:transition-property "height, margin-bottom"
       :transition-duration s/default-duration
       :display :flex
       :align-items "flex-start"
       :justify-content :space-between
       :margin-bottom s/default-size}
      [:a
       {:font-family :VeganSans
        :padding "0.33rem 1rem"
        :text-align :center
        :border-radius s/default-size
        :transition-property "background-color"
        :transition-duration s/short-duration
        :cursor :pointer}
       [:&:active
        {:transform "scale(0.8)"
         :transition-property :transform
         :transition-duration s/short-duration}]
       [:&:hover {:background-color c/alabaster}]
       [:&.active
        {:font-weight 700
         :background-color c/nobel
         :transition-duration s/short-duration}]]]
     [:button
      m/wish-button
      {:margin-bottom s/default-size}
      m/wish-button-hover]
     wish-box]
    [:&>div>div>.wish-box:last-child
     {:top (u/rem 9)
      :padding-top s/quadruple-size
      :position :fixed
      :z-index 2
      :background-color c/white
      :padding s/triple-size
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
      {:border-bottom (m/thin-border c/nobel)
       :padding-top s/default-size}
      [:&.fade {:opacity "0.1"}]
      [:&.false {:background-color "#fffffe"}]
      [:div.tools
       {:font-family (m/important "VeganSans")}
       [:&>div {:float :left}]
       [:button
        (m/font s/smaller-size)
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
         (m/font s/bigger-size)
         m/wish-button
         {:width :auto
          :height :auto
          :float :right
          :display :block
          :transition "transform 250ms"
          :transform "translateY(4rem)"
          :position :relative
          :z-index 2}
         m/wish-button-hover]
        [:&.smaller
         [:&:hover
          {:transform "scale(0.8)"}]
         [:&:active
          {:transform "scale(1.0)"}]]
        [:&.list:hover {:transform "translateY(0.3rem)"}]
        [:&.bigger
         [:&:hover {:transform "scale(1.2)"}]
         [:&:active {:transform "scale(1.0)"}]]]
       [:a (m/font s/smaller-size)]
       [:span
        {:display :inline-block
         :text-align :center
         :width s/quadruple-size}
        [:&.name
         (m/font s/smaller-size)
         {:width (u/rem 20)
          :text-align :left
          :padding-left s/default-size}]]]
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
     wish-box]
    [:section#styles
     [:ul.styles
      {:padding-top (u/rem 9)}]]
    [:section#glyphs
     {:margin "6rem 6rem 0"}
     [:select
      ^:prefix {:appearance :none}
      {:top (u/rem 8)
       :position :fixed
       :border-radius s/default-size
       :padding "0.75rem 1rem"
       :margin [[s/double-size 0]]
       :left "-30rem"
       :z-index 10
       :opacity 0}
      [:&.visible
       {:opacity 0.9
        :left s/default-size}
       (m/font s/default-size)]]
     [:img
      m/full-width
      {:padding [[s/triple-size 0]]}]]
    [:section#details
     {:margin "0 6rem"
      :padding "6rem 0"}
     [:.main {:display :flex}
      [:.description
       {:width (u/percent 73)
        :margin-right (u/percent 2)}
       (m/font s/bigger-size)]
      [:.features
       {:width (u/percent 20)
        :padding-left (u/percent 5)}]]
     [:div.opentype
      {:margin-top s/double-size
       :clear :both}
      [:img
       m/full-width]]]
    [:section#inuse
     m/full-width
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
        :height s/double-size
        :float :left}
       [:&:last-child {:text-align :right}]
       [:button
        m/button-padding
        {:display :inline-block
         :background-repeat "no-repeat"
         :background-color c/white
         :opacity 0.8
         :margin 0
         :padding-bottom "0.5rem"
         :border :none
         :border-radius s/default-size
         :cursor :pointer}]]]
     [:div.figure-wrapper
      {:transition "transform cubic-bezier(0.4, 0, 0.2, 1) 1s"
       :z-index 1}
      [:figure
       m/full-width
       {:margin 0
        :float :left}
       [:figcaption
        {:transform "translate(2rem, 2rem)"
         :background-color c/white
         :border-radius s/double-size
         :padding "0.25rem 1rem"
         :opacity "0.8"
         :position :absolute}
        [:span
         {:padding [[0 s/default-size 0 0]]
          :display :inline-block}
         [:&:last-child {:padding 0}]
         [:&:first-child
          {:font-weight :bold}]]]
       [:img
        m/full-width
        {:max-height "100vw"
         :float :left}]]]]
    [:section#foundry
     [:header
      [:h2
       (m/font s/larger-size)
       {:width (u/percent 48)
        :margin "0 10%"
        :padding-top (u/rem 6)
        :padding-bottom s/double-size
        :font-weight :normal}]]
     [:div.about-address
      {:background-color c/spring-green
       :display :flex}
      [:div.text
       (m/font s/bigger-size)
       {:flex 6
        :margin-left (u/percent 10)
        :padding-top s/quadruple-size}]]
     [:div.contact
      {:flex 8
       :overflow :visible}
      [:address
       (m/font s/larger-size)
       {:transition-property :transform
        :transition-duration "1750ms"
        :transition-timing-function "cubic-bezier(0.18, 0.89, 0.32, 1.28)"
        :margin-left (u/percent 10)
        :width (u/percent 70)
        :padding s/triple-size
        :font-style :normal
        :background-color c/nobel
        :margin-top s/triple-size
        :margin-bottom s/default-size
        :border-radius s/double-size}
       [:&.visible {:transform "translateX(0rem)"}]
       [:&.hidden {:transform "translateX(70rem)"}]
       [:a
        (m/font s/bigger-size)
        {:border-radius s/triple-size
         :padding "0.5rem 1rem"}
        [:&:hover
         {:background-color (m/important c/white)
          :color (m/important c/black)}]]
       [:div.email
        {:margin-top s/default-size}
        [:a
         (m/font s/bigger-size)
         {:color c/white
          :background-color c/emperor}]]
       [:nav.social
        {:margin-top s/triple-size
         :line-height 1
         :display :flex
         :justify-content :space-between}
        [:a
         {:color c/white}
         [:&.facebook
          {:background-color "#0060ff"}]
         [:&.twitter
          {:background-color "#00beff"}]
         [:&.instagram
          {:background-color "#a04aff"}]]]]]
     [:.lost
      m/full-width
      {:background-color c/spring-green}
      [:p
       (m/font s/bigger-size)
       {:margin-left (u/percent 10)
        :padding-top s/large-size
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
          :transition-duration s/short-duration
          :font-size "6vw"
          :color c/white
          :padding "0 1rem 0 0"
          :font-family :inherit}
         [:&:hover
          {:transform "scale(1.10)"
           :color c/black}]]]]]
     [:.people
      {:background-color c/spring-green
       :display :flex}
      [:.person
       {:width (u/percent 48)
        :margin-top s/triple-size
        :padding s/double-size}
       [:.photo
        [:img
         m/full-width
         {:border-radius (u/percent 50)}
         [:p
          (m/font s/bigger-size)]]]]]
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
       (m/font s/large-size)
       {:color c/purple
        :width (u/percent 80)
        :margin "0 10%"
        :padding-top (u/rem 6)
        :padding-bottom s/double-size
        :font-weight :normal}]]
     [:div.text
      {:width (u/percent 40)
       :margin "0 10% 0 10%"
       :float :left}
      [:p (m/font s/bigger-size)
       [:span.email
        {:margin-top s/default-size}
        [:a
         m/button-padding
         {:display :inline-block
          :color c/white
          :border-radius s/default-size
          :background-color c/black}
         [:&:hover
          {:background-color (m/important c/white)
           :color (m/important c/black)}]]]]]
     [:ul.advantages
      {:width (u/percent 30)
       :margin "0 10% 0 0"
       :float :left}
      [:li {:margin "0 0 2rem 0"}
       [:&:before
        (m/font s/bigger-size)
        {:content "\"• \""}]
       [:&.red:before {:color c/red}]
       [:&.yellow:before {:color c/golden-fizz}]]]
     [:div.cases
      {:clear :both
       :font-family :VeganSans}
      [:&>div
       {:margin-bottom s/quadruple-size}
       [:&.fade {:opacity 0.4}]
       [:h3
        (m/font s/monster-size)
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
       [:img {:width (u/percent 100)}]]]]
    [:section#first-aid
     {:display :flex
      :padding s/double-size}
     [:div.column
      {:flex 1
       :height (u/percent 100)
       :margin-right s/double-size}
      [:h2
       (m/font s/default-size)
       {:font-weight :normal
        :width (u/percent 75)
        :margin-bottom s/double-size
        :line-height s/bigger-size}]
      [:h3
       (m/font s/bigger-size)
       {:font-weight :normal
        :margin-bottom (u/rem 0.5)}
       [(sel/& (sel/not (sel/first-child))) {:margin-top s/triple-size}]]
      [:h4
       {:margin-bottom s/default-size}
       [:button
        m/button-padding
        (m/font s/default-size)
        {:background-color c/gallery
         :border-radius s/default-size
         :cursor :pointer
         :position :relative
         :z-index 2
         :border :none}
        [:&.opened {:background-color c/paris-daisy}]]]
      [:&:first-child
       [:p
        (m/font s/smaller-size)
        {:width (u/percent 75)
         :margin-bottom s/default-size}]]
      [(sel/& (sel/not (sel/first-child)))
       [:p
        (m/font s/smaller-size)
        {:margin [[0 s/double-size 0 s/default-size]]
         :width (u/percent 75)
         :max-height (u/rem 0)
         :position :relative
         :z-index 1
         :opacity 0}
        [:&.opened
         {:animation [[an/section-slide-down s/default-duration :alternate :forwards]]}]
        [:&.closed
         {:animation [[an/section-slide-up s/short-duration :alternate :forwards]]}]]]]]]])
