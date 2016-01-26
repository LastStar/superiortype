(ns superiortype.css
  (:refer-clojure :exclude [+ - * / rem])
  (:require [garden.def :refer [defstylesheet defstyles defkeyframes]]
            [garden.units :as u :refer [rem px]]
            [garden.selectors :as sel]
            [garden.arithmetic :refer [+ - * /]]
            [garden.stylesheet :refer [at-font-face at-media]]
            [gardener.respond :as respond]
            [superiortype.colors :as c]))

(defn important
  "Adds !important css directive to the content"
  [content]
  [[content "!important"]])

(def modular 1.333)
(def default-size (u/rem 1))
(def double-size (u/rem 2))
(def triple-size (u/rem 3))
(def quadruple-size (u/rem 4))

(def smaller-size (/ default-size modular))
(def bigger-size (* default-size modular))
(def big-size (* bigger-size modular))
(def larger-size (* big-size modular))
(def large-size (* larger-size modular))
(def huge-size (* large-size modular))
(def epic-size (* huge-size modular))
(def monster-size (* epic-size modular))

(def full-width {:width (u/percent 100)})
(def short-duration (u/ms 250))
(def default-duration (u/ms 450))
(def long-duration (u/ms 750))
(def longest-duration (u/ms 1000))

(def default-margin
  {:margin double-size})
(def default-padding
  {:padding double-size})

(defn font
  ([] font 1)
  ([size] {:font-size size}))

(defn thin-border
  ([] thin-border c/black)
  ([color] [[:thin :solid color]]))

(def button-padding {:padding [[(u/rem 0.35) default-size]]})

(def wish-button
  (merge
   {:cursor :pointer
    :color c/black
    :background-color c/white
    :border-radius default-size
    :border (thin-border c/cyan)
    :font-family :VeganSans
    :font-weight :normal}
    button-padding
    (font default-size)))

(def wish-button-hover
  [:&:hover
   {:background-color c/cyan}])

(def back-button
  (merge
    {:cursor :pointer
     :color c/black
     :background-color c/nobel
     :border-radius default-size
     :border :none
     :font-family :VeganSans
     :font-weight :normal}
     button-padding
     (font default-size)))

(def back-button-hover
  [:&:hover
   {:background-color c/white}])

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
   (font default-size)
   {:font-family :VeganSans}]
  [:input:focus :button:focus :select:focus {:outline :none}]
  ["::-webkit-input-placeholder" {:color c/black}]
  ["::-moz-placeholder" {:color c/black}]
  [":-ms-input-placeholder" {:color c/black}]
  [:input:-moz-placeholder {:color c/black}]
  [:p {:margin 0}])

(defstyles fonts
  ;; FIXME dynamic font styles for all like this
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
   {:font-family (important "Dres")}]
  [:.kakao
   {:font-family (important "Kakao")}]
  [:.motel-sans
   {:font-family (important "MotelSans")}]
  [:.motel-slab
   {:font-family (important "MotelSlab")}]
  [:.pramen-sans
   {:font-family (important "PramenSans")}]
  [:.pramen-slab
   {:font-family (important "PramenSlab")}]
  [:.steiner
   {:font-family (important "Steiner")}])

;; Keyframes
;; FIXME move to its own file animation
;; FIXME tune indents
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
  [:0% {:opacity 0
        :max-height (u/rem 0)}]
  [:99.9% {:opacity 0
         :max-height (u/rem 20)
         :margin-bottom default-size}]
  [:100% {:opacity 1
          :max-height (u/rem 20)
          :margin-bottom default-size}])

(defkeyframes section-slide-up
  [:0% {:opacity 1
        :margin-bottom default-size
        :max-height (u/rem 20)}])
  [:100% {:opacity 0
        :max-height (u/rem 0)}]

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
    :margin default-size
    :padding default-size
    :border-radius default-size
    :cursor :pointer
    :transform "translateY(30rem)"
    :animation [[:slide-up long-duration :forwards :ease-out]]}
   [:&:first-child {:margin-left 0}]
   [:header
    {:display :flex
     :justify-content :space-between
     :align-items :center}
    [:h5
     (font bigger-size)]
    [:.price
     (font smaller-size)
     {:font-family :VeganSans
      :display :inline-block
      :border-radius default-size
      :padding [[(u/rem 0.35) (u/rem 0.5) (u/rem 0.25)]]
      :background-color c/white
      :text-align :center}]]
   [:.description
    {:clear :both
     :transition-property :opacity
     :transition-duration short-duration
     :opacity 0}
    [:h6
     (font default-size)
     {:margin [[double-size 0 0]]}]
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
     :animation-delay short-duration}
    [:h5 {:font-weight :normal}]]
   [:&.premium
    {:background-color c/cyan
     :animation-delay long-duration}]
   [:&.superior
    {:background-color c/electric-violet
     :color c/white
     :animation-delay longest-duration
     :max-height (u/rem 20)}
    [:h5
     {:font-weight 900}]
    [:.price
     {:color c/black}]
    [:.description
     {:margin "10rem 0 0 0"}]]]
  [:button
   back-button
   {:margin-bottom default-size}]])

(defstyles media-queries
  (respond/tablet
    [:html
     (font (u/px 13))])
  (respond/laptop
    [:html
     (font (u/px 14))])
  (respond/desktop
    [:html
     (font (u/px 20))])
  (respond/hd
    [:html
     (font (u/px 26))]))

(defstyles site
  ;; Keyframes
  slide-right
  slide-left
  slide-up
  section-slide-down
  section-slide-up
  header-slide-up
  bounce-top
  bounce-bottom-left
  bounce-bottom-right
  rainbow
  reveal-from-right
  ;; Partials
  basic
  fonts
  media-queries

  [:body
   (font default-size)
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
     :height double-size
     :display :flex
     :flex-direction :row
     :justify-content :space-between}
    [:div.menu
     full-width
     {:margin 0
      :padding [[(u/rem 1.25) default-size (u/rem 0.25) double-size]]}
     [:h1
      {:transition-property :font-size
       :transition-duration default-duration
       :display :inline-block
       :margin 0}
      (font bigger-size)
      [:a
       {:font-weight :bold}]]
     [:nav
      {:animation [[slide-left "2500ms" :ease :forwards]]
       :display :inline-block}
      [:a
       {:display :inline-block
        :padding-left double-size}
       [:&:first-child {:padding-left triple-size}]]
      [:&.visible {:animation [[slide-right short-duration :ease :forwards]]}]]]]
   [:section#cart
    {:margin-top triple-size
     :background-color c/clear
     :position :absolute}
    [:button.wish-list-button
     wish-button
     {:background-color c/cyan
      :transition-property :font-size
      :transition-duration default-duration
      :position :fixed
      :right double-size
      :top default-size
      :z-index 20}
     wish-button-hover
     [:&.small
      (font smaller-size)
      {:top (u/rem 1.2)}]]
    [:div.wish-list
     full-width
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
         :transition-duration default-duration}
        [:th :td (font smaller-size)]
        [:td.name :th.total (font default-size)]
        [:tr.superior-list
         [:td
          [:&>div
           {:display :flex
            :justify-content :space-between}
           [:h2
            {:margin-bottom default-size}]]]]]
       [:form#checkout
        {:margin "3rem 0 0 3%"
         :width (u/percent 45)
         :float :left
         :transform-origin "center right"
         :transform "translateX(45%)"
         :opacity 0
         :animation-delay "500ms"
         :animation [[reveal-from-right default-duration :forwards :ease-out]]}
        [:.column
         {:width (u/percent 50)
          :float :left}
         [:label
          {:display :block
           :margin-bottom default-size}
          [:&.grey {:color c/nobel}]
          [:input
           (font default-size)
           {:display :block
            :border-radius "0.5rem"
            :padding "0.5rem"
            :width (u/percent 75)}
           [:&.zip {:width (u/percent 25)}]]
          [:select
           (font default-size)
           ^:prefix {:appearance :none}
           {:background "url('/img/down-arrow.svg') #fff no-repeat center right 0.5rem"
            :display :block
            :width (u/percent 80)
            :border-radius (u/rem (* modular 0.25))
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
            :margin-top default-size
            :width double-size
            :height double-size}
           [:&:checked {:background-image "url('/img/check-checked.svg')"}]]
          [:button
           (font bigger-size)
           {:padding "0.5rem 1rem"
            :color c/black
            :border-radius big-size
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
       (font larger-size)
       {:margin "3rem 3rem 1rem"
        :font-weight 600}]
      [:button.back-button
       back-button
       {:margin "3rem 2rem 0"}
       back-button-hover
       [:&.wish-list
        {:background-color c/white}
        [:&:hover
         {:background-color c/baby-blue}]]]]
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
        :padding default-size
        :border-bottom "thin solid c/black"}
       [:&.price :&.remove
        {:width (u/rem 7)
         :text-align :right}]
       [:&.total
        {:text-align :right}]
       [:&.checkout
        {:text-align :right}
        [:button.checkout
         button-padding
         {:border-radius bigger-size
          :border :none
          :background c/white
          :float :right}
         [:&:hover {:background c/pale-canary}]]]]
      [:button
       (font default-size)
       {:border :none
        :background :none
        :cursor :pointer
        :font-family :VeganSans}]
      [:select
       ^:prefix {:appearance :none}
       (font default-size)
       {:border-radius bigger-size
        :padding "0.5rem 2rem 0.5rem 1rem"
        :background "url('/img/down-arrow.svg') #fff no-repeat center right 0.5rem"}]]]]]
[:main#app
 [:&>.fade
  {:opacity 0.4}
  [:button {:display (important "none")}]]
 [:svg
  {:width "100vw"
   :height "95vh"}
  [:g
   [:text
    (font huge-size)]]
  [:g.top
   {:animation [[bounce-top "3s" :infinite :alternate :ease]]}
   [:text
    {:font-family :VeganSans
     :font-weight 600
     :fill c/golden-fizz}]
   [:circle
    {:stroke c/golden-fizz
     :stroke-width 24
     :fill c/clear}]]
  [:g.bottom-right
   {:animation [[bounce-bottom-right "2s" :infinite :alternate :ease-out]]}
   [:text
    {:font-family :KundaBook
     :font-style :Italic}]
   [:circle
    {:stroke :none
     :fill c/red}]]
  [:g.bottom-left
   {:animation [[bounce-bottom-left "1.44s" :infinite]]}
   [:text
    {:font-family :Hrot
     :font-weight 200
     :fill c/electric-violet}]
   [:circle
    {:stroke c/electric-violet
     :fill c/white}]]
  [:circle.down
   {:animation [[:rainbow "4s" :infinite]]
    :cursor :pointer}]]
 [:header#font
  full-width
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
   (font smaller-size)
   {:padding-bottom "1.25rem"
    :animation [[header-slide-up "500ms" :ease :forwards]]}
   [:&:hover :&.hover
    [:&.fade
     {:display :none
      :opacity 0}]]
   [:nav.fonts
    [:h2
     (font bigger-size)]
    [:a
     {:margin-bottom "0.5rem"
      :width double-size
      :height default-size}]]
   [:button
    (font smaller-size)
    {:transition-property "margin-bottom, margin-right"
     :transition-duration short-duration
     :margin-bottom 0}
    [:&.right-spaced {:margin-right (u/rem 9)}]]
   [:nav.sections
    {:margin-bottom 0}]]
  [:&.fade
   {:display :none
    :opacity 0}]
  [:nav.fonts
   {:display :flex
    :margin-left double-size
    :align-items :center}
   [:div.links
    {:display :flex
     :width (u/rem 6)}]
   [:h2
    (font huge-size)
    {:transition-property "font-size, width"
     :transition-duration default-duration
     :font-weight :normal
     :display :inline-block
     :margin 0
     :width (u/rem 25)
     :font-family :inherit}]
   [:a
    {:transition-property "width, height, margin-bottom"
     :transition-duration default-duration
     :background-position-y 0
     :background-position-x (u/percent 50)
     :background-repeat "no-repeat"
     :background-color "rgba(0, 0, 0, 0)"
     :background-size :contain
     :display :inline-block
     :border :none
     :width double-size
     :height double-size
     :color "rgba(0, 0, 0, 0)"}
    [:&.previous
     {:background-image "url(/img/previous.svg)"}]
    [:&.next
     {:background-image "url(/img/next.svg)"}]]]
  [:div.buttons
   {:display :flex
    :width (u/percent 55)
    :justify-content :space-between
    :margin-right double-size}]
  [:nav.sections
   {:transition-property "height, margin-bottom"
    :transition-duration default-duration
    :display :flex
    :align-items "flex-start"
    :justify-content :space-between
    :margin-bottom default-size}
   [:a
    {:font-family :VeganSans
     :padding "0.33rem 1rem"
     :text-align :center
     :border-radius default-size
     :transition-property "background-color"
     :transition-duration short-duration
     :cursor :pointer}
    [:&:active
     {:transform "scale(0.8)"
      :transition-property :transform
      :transition-duration short-duration}]
    [:&:hover {:background-color c/alabaster}]
    [:&.active
     {:font-weight 700
      :background-color c/nobel
      :transition-duration short-duration}]]]
  [:button
   wish-button
   {:margin-bottom default-size}
   wish-button-hover]]
wish-box
[:&>div>div>.wish-box:last-child
 {:top (u/rem 9)
  :padding-top quadruple-size
  :position :fixed
  :z-index 2
  :background-color c/white
  :padding triple-size
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
  {:border-bottom (thin-border c/nobel)
   :padding-top default-size}
  [:&.fade {:opacity "0.1"}]
  [:&.false {:background-color "#fffffe"}]
  [:div.tools
   {:font-family (important "VeganSans")}
   [:&>div {:float :left}]
   [:button
    (font smaller-size)
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
     (font bigger-size)
     wish-button
     {:width :auto
      :height :auto
      :float :right
      :display :block
      :transition "transform 250ms"
      :transform "translateY(4rem)"
      :position :relative
      :z-index 2}
     wish-button-hover]
    [:&.smaller
     [:&:hover
      {:transform "scale(0.8)"}]
     [:&:active
      {:transform "scale(1.0)"}]]
    [:&.list:hover {:transform "translateY(0.3rem)"}]
    [:&.bigger
     [:&:hover {:transform "scale(1.2)"}]
     [:&:active {:transform "scale(1.0)"}]]]
   [:a (font smaller-size)]
   [:span
    {:display :inline-block
     :text-align :center
     :width quadruple-size}
    [:&.name
     (font smaller-size)
     {:width (u/rem 20)
      :text-align :left
      :padding-left default-size}]]]
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
   :border-radius default-size
   :padding "0.75rem 1rem"
   :margin [[double-size 0]]
   :left "-30rem"
   :z-index 10
   :opacity 0}
  [:&.visible
   {:opacity 0.9
    :left default-size}
   (font default-size)]]
 [:img
  full-width
  {:padding [[triple-size 0]]}]]
[:section#details
 {:margin "0 6rem"
  :padding "6rem 0"}
 [:.main {:display :flex}
  [:.description
   {:width (u/percent 73)
    :margin-right (u/percent 2)}
   (font bigger-size)]
  [:.features
   {:width (u/percent 20)
    :padding-left (u/percent 5)}]]
 [:div.opentype
  {:margin-top double-size
   :clear :both}
  [:img
   full-width]]]
[:section#inuse
 full-width
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
    :height double-size
    :float :left}
   [:&:last-child {:text-align :right}]
   [:button
    button-padding
    {:display :inline-block
     :background-repeat "no-repeat"
     :background-color c/white
     :opacity 0.8
     :margin 0
     :padding-bottom "0.5rem"
     :border :none
     :border-radius default-size
     :cursor :pointer}]]]
 [:div.figure-wrapper
  {:transition "transform cubic-bezier(0.4, 0, 0.2, 1) 1s"
   :z-index 1}
  [:figure
   full-width
   {:margin 0
    :float :left}
   [:figcaption
    {:transform "translate(2rem, 2rem)"
     :background-color c/white
     :border-radius double-size
     :padding "0.25rem 1rem"
     :opacity "0.8"
     :position :absolute}
    [:span
     {:padding [[0 default-size 0 0]]
      :display :inline-block}
     [:&:last-child {:padding 0}]
     [:&:first-child
      {:font-weight :bold}]]]
   [:img
    {:width (u/percent 100)
     :max-height "100vw"
     :float :left}]]]]
[:section#foundry
 [:header
  [:h2
   (font larger-size)
   {:width (u/percent 48)
    :margin "0 10%"
    :padding-top (u/rem 6)
    :padding-bottom double-size
    :font-weight :normal}]]
 [:div.about-address
  {:background-color c/spring-green
   :display :flex}
  [:div.text
   (font bigger-size)
   {:flex 6
    :margin-left (u/percent 10)
    :padding-top quadruple-size}]]
 [:div.contact
  {:flex 8
   :overflow :visible}
  [:address
   (font larger-size)
   {:transition-property :transform
    :transition-duration "1750ms"
    :transition-timing-function "cubic-bezier(0.18, 0.89, 0.32, 1.28)"
    :margin-left (u/percent 10)
    :width (u/percent 70)
    :padding triple-size
    :font-style :normal
    :background-color c/nobel
    :margin-top triple-size
    :margin-bottom default-size
    :border-radius double-size}
   [:&.visible {:transform "translateX(0rem)"}]
   [:&.hidden {:transform "translateX(70rem)"}]
   [:a
    (font bigger-size)
    {:border-radius triple-size
     :padding "0.5rem 1rem"}
    [:&:hover
     {:background-color (important c/white)
      :color (important c/black)}]]
   [:div.email
    {:margin-top default-size}
    [:a
     (font bigger-size)
     {:color c/white
      :background-color c/emperor}]]
   [:nav.social
    {:margin-top triple-size
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
  {:background-color c/spring-green
   :width (u/percent 100)}
  [:p
   (font bigger-size)
   {:margin-left (u/percent 10)
    :padding-top large-size
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
      :transition-duration short-duration
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
    :margin-top triple-size
    :padding double-size}
   [:.photo
    [:img
     {:border-radius (u/percent 50)
      :width (u/percent 100)}
     [:p
      (font bigger-size)]]]]]
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
   (font large-size)
   {:color c/purple
    :width (u/percent 80)
    :margin "0 10%"
    :padding-top (u/rem 6)
    :padding-bottom double-size
    :font-weight :normal}]]
 [:div.text
  {:width (u/percent 40)
   :margin "0 10% 0 10%"
   :float :left}
  [:p (font bigger-size)
   [:span.email
    {:margin-top default-size}
    [:a
     button-padding
     {:display :inline-block
      :color c/white
      :border-radius default-size
      :background-color c/black}
     [:&:hover
      {:background-color (important c/white)
       :color (important c/black)}]]]]]
 [:ul.advantages
  {:width (u/percent 30)
   :margin "0 10% 0 0"
   :float :left}
  [:li {:margin "0 0 2rem 0"}
   [:&:before
    (font bigger-size)
    {:content "\"• \""}]
   [:&.red:before {:color c/red}]
   [:&.yellow:before {:color c/golden-fizz}]]]
 [:div.cases
  {:clear :both
   :font-family :VeganSans}
  [:&>div
   {:margin-bottom quadruple-size}
   [:&.fade {:opacity 0.4}]
   [:h3
    (font monster-size)
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
 default-padding
 {:display :flex}
 [:div.column
  {:flex 1
   :height (u/percent 100)
   :margin-right double-size}
  [:h2
   (font default-size)
   {:font-weight :normal
    :width (u/percent 75)
    :margin-bottom double-size
    :line-height bigger-size}]
  [:h3
   (font bigger-size)
   {:font-weight :normal
    :margin-bottom (u/rem 0.5)}
   [(sel/& (sel/not (sel/first-child))) {:margin-top triple-size}]]
  [:h4
   {:margin-bottom default-size}
   [:button
    button-padding
    (font default-size)
    {:background-color c/gallery
     :border-radius default-size
     :cursor :pointer
     :position :relative
     :z-index 2
     :border :none}
    [:&.opened {:background-color c/paris-daisy}]]]
  [:&:first-child
   [:p
    (font smaller-size)
    {:width (u/percent 75)
     :margin-bottom default-size}]]
  [(sel/& (sel/not (sel/first-child)))
   [:p
    (font smaller-size)
    {:margin [[0 double-size 0 default-size]]
     :width (u/percent 75)
     :max-height (u/rem 0)
     :position :relative
     :z-index 1
     :opacity 0}
    [:&.opened
     {:animation [[section-slide-down default-duration :alternate :forwards]]}]
    [:&.closed
     {:animation [[section-slide-up short-duration :alternate :forwards]]}]]]]]]])
