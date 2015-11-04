(ns superiortype.css
  (:refer-clojure :exclude [+ - * / rem])
  (:require [garden.def :refer [defstylesheet defstyles defkeyframes]]
            [garden.units :as u :refer [rem px]]
            [garden.selectors :as sel]
            [garden.arithmetic :refer [+ - * /]]
            [garden.stylesheet :refer [at-font-face at-media]]
            [gardener.respond :as respond]))


;; FIXME with more garden
(def grey "#b3b3b3")
(def darkerer-grey "#f0f0f0")
(def darker-grey "#e6e6e6")
(def lighter-grey "#F2F2F2")
(def lightest-grey "#FAFAFA")
(def dark-grey "#555555")
(def black "#000")
(def white "#fff")
(def light-blue "#00ffff")
(def lightest-blue "#dcffff")
(def green "#00ffa0")
(def light-green "#00ffa0")
(def blue "#0ff")
(def dark-blue "#5F00FF")
(def clear "rgba(0, 0, 0, 0)")
(def yellow "#fffd38")
(def dark-yellow "#ffff78")
(def light-yellow "#ffff96")
(def red "#f00")
(def violet "#4600a0")
(def orange "#ff7d64")

(def default-margin
  {:margin (rem 2)})
(def default-padding
  {:padding (rem 2)})
(def modular 1.333)
(def default-size (u/rem 1))
(def smaller-size (/ default-size modular))
(def bigger-size (* default-size modular))
(def big-size (* bigger-size modular))
(def larger-size (* big-size modular))
(def large-size (* larger-size modular))
(def huge-size (* large-size modular))
(def epic-size (* huge-size modular))
(def monster-size (* epic-size modular))
(def full-width {:width "100%"})

(defn font
  ([] font 1)
  ([size] {:font-size size}))

(defn thin-border
  ([] thin-border black)
  ([color] (str "thin solid " color)))

(def button-padding {:padding "0.35rem 1rem"})

(def wish-button
  (merge
   {:cursor "pointer"
    :color black
    :background-color "white"
    :border-radius "1rem"
    :border (thin-border light-blue)
    :font-family "VeganSans"
    :font-weight "normal"}
    button-padding
    (font default-size)))

(def wish-button-hover
  [:&:hover
   {:background-color light-blue}])

(def back-button
  (merge
   {:cursor "pointer"
    :color black
    :background-color grey
    :border-radius "1rem"
    :border "none"
    :font-family "VeganSans"
    :font-weight "normal"}
    button-padding
    (font default-size)))

(def back-button-hover
  [:&:hover
   {:background-color white}])

(defstyles basic
  [:h1 :h2 :h3 :h4 :h5 :a :a:hover :select :input
   {:font-family "VeganSans"
    :border "none"
    :padding 0
    :margin 0
    :line-height 1.2}]
  [:a
   {:color "black"
    :text-decoration "none"
    :font-weight "normal"}]
  [:ul
   {:list-style-type "none"
    :-webkit-padding-start 0}]
  [:button
   (font default-size)
   {:font-family "VeganSans"}]
  [:input:focus :button:focus :select:focus {:outline "none"}]
  ["::-webkit-input-placeholder" {:color black}]
  ["::-moz-placeholder" {:color black}]
  [":-ms-input-placeholder" {:color black}]
  [:input:-moz-placeholder {:color black}]
  [:p {:margin 0}])

(defstyles fonts
  ;; FIXME dynamic font styles for all like this
  (at-font-face
    {:font-family "Hrot"
     :font-style "normal"
     :font-weight 100
     :src "url(\"/fonts/Hrot_Hair.woff\") format(\"woff\")"})
  [:.hair
    {:font-weight 100}]
  [:.thin
    {:font-weight 200}]
  [:.light
    {:font-weight 300}]
  [:.regular
    {:font-weight "normal"}]
  [:.medium
    {:font-weight 500}]
  [:.bold
    {:font-weight "bold"}]
  [:.semibold
    {:font-weight 600}]
  [:.italic
    {:font-style "italic"}]
  [:.black
    {:font-weight 800}]
  [:.vegan-sans
   {:font-family "VeganSans"}]
  [:.kunda-book
   {:font-family "KundaBook"}]
  [:.hrot
   {:font-family "Hrot"}]
  [:.kendo
   {:font-family "Kendo"}]
  [:.signer
   {:font-family "Signer"}]
  [:.tungsten
   {:font-family "Tungsten"}]
  [:.negramot
   {:font-family "Negramot"}]
  [:.dres
   {:font-family "Dres !important"}]
  [:.kakao
   {:font-family "Kakao !important"}]
  [:.motel-sans
   {:font-family "MotelSans !important"}]
  [:.motel-slab
   {:font-family "MotelSlab !important"}]
  [:.pramen-sans
   {:font-family "PramenSans !important"}]
  [:.pramen-slab
   {:font-family "PramenSlab !important"}]
  [:.steiner
   {:font-family "Steiner !important"}]
  [:.bigger-font
   {:font-size "1.618rem"}])

;; Keyframes
;; FIXME move to its own file animation
(defkeyframes header-slide-up
  [:0% {:height "8rem"}]
  [:100% {:height "2.5em"}])

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
        :max-height (rem 0)}]
  [:99.9% {:opacity 0
         :max-height (rem 20)
         :margin-bottom (rem 1)}]
  [:100% {:opacity 1
          :max-height (rem 20)
          :margin-bottom (rem 1)}])

(defkeyframes section-slide-up
  [:0% {:opacity 1
        :margin-bottom (rem 1)
        :max-height (rem 20)}])
  [:100% {:opacity 0
        :max-height (rem 0)}]

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
         :fill white}]
  [:97% {:transform "translate(50vw, 40vh) scale(3)"}]
  [:100% {:transform "translate(50vw, 40vh) scale(3)"
          :fill white}])

(defkeyframes rainbow
  [:0% {:fill dark-blue :transform "translate(2rem, 92vh) scale(1)"}]
  [:40% {:fill red :transform "translate(2rem, 88vh) scale(1.2)"}]
  [:50% {:transform "translate(2rem, 88vh) scale(1)"}]
  [:60% {:transform "translate(2rem, 88vh) scale(1.2)"}]
  [:70% {:transform "translate(2rem, 88vh) scale(1)"}]
  [:80% {:fill yellow :transform "translate(2rem, 88vh) scale(1.4)"}]
  [:100% {:fill white :transform "translate(2rem, 93vh)"}])

(defkeyframes reveal-from-right
  [:0% {:transform "translateX(45%)" :opacity 0.7}]
  [:100% {:transform "translateX(0)" :opacity 1}])

(defstyles wish-box
 [:div.wish-box
  {:display "flex"
   :flex-direction "row"
   :align-items "flex-end"
   :justify-items "space-between"
   :clear "both"
   :overflow "hidden"}
  [:&>div
   {:font-family "VeganSans"
    :width "23%"
    :margin "1rem"
    :padding "1rem"
    :border-radius "1rem"
    :cursor "pointer"
    :transform "translateY(30rem)"
    :animation [["slide-up" "750ms" :forwards :ease-out]]}
   [:&:first-child {:margin-left 0}]
   [:header
    {:display "flex"
     :justify-content "space-between"
     :align-items "center"}
    [:h5
      (font bigger-size)]
     [:.price
      (font smaller-size)
      {:font-family "VeganSans"
       :display "inline-block"
       :border-radius "1rem"
       :padding "0.35rem 0.5rem 0.25rem"
       :background-color "white"
       :text-align "center"}]]
   [:.description
    {:clear "both"
     :transition-property "opacity"
     :transition-duration "250ms"
     :opacity 0}
   [:h6
    (font default-size)
    {:margin "2rem 0 0"}]
   [:p {:margin "0"}]]
   [:&:hover
    [:.description {:opacity 1}]]
   [:&.demo
    {:background-color lighter-grey}
    [:header
     [:h5
       {:margin "0"
       :font-style "italic"
       :font-weight "300"}]
     [:.price {:margin "0.3rem 0 0.25rem 0"}]]]
   [:&.basic
    {:background-color lightest-blue
     :animation-delay "250ms"}
     [:h5 {:font-weight "normal"}]]
   [:&.premium
    {:background-color blue
     :animation-delay "750ms"}]
   [:&.superior
    {:background-color dark-blue
     :color white
     :animation-delay "1s"
     :max-height "20rem"}
     [:h5
      {:font-weight "900"}]
     [:.price
      {:color black}]
     [:.description
      {:margin "10rem 0 0 0"}]]]
  [:button
   back-button
   {:margin-bottom (rem 1)}]])

(defstyles media-queries
  (respond/tablet
      [:html
       (font (u/px 14))])
  (respond/laptop
    [:html
     (font (u/px 18))])
  (respond/desktop
    [:html
     (font (u/px 22))])
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
   {:font-family "VeganSans"
    :min-width (rem 62)
    :margin "auto"
    :line-height 1.5}
    [:header#header
     {:position "relative"
      :top 0
      :background-color clear
      :z-index 10
      :height (rem 2)
      :display :flex
      :flex-direction "row"
      :justify-content "space-between"}
     [:div.menu
      {:margin "0"
       :width "100%"
       :padding "1.25rem 1rem 0.25rem 2rem"}
      [:&.first-aid {:background-color darker-grey}]
      [:h1
       {:transition-property "font-size"
        :transition-duration "450ms"
        :display "inline-block"
        :margin 0}
       (font bigger-size)
       [:a
        {:font-weight "bold"}]]
      [:nav
       {:animation [[slide-left "2500ms" :ease :forwards]]
        :display "inline-block"}
       [:a
          {:display "inline-block"
           :padding-left "2rem"}
          [:&:first-child {:padding-left "3rem"}]]
       [:&.visible {:animation [[slide-right "250ms" :ease :forwards]]}]]]]
    [:section#cart
     {:margin-top "3rem"
      :background-color clear
      :position "absolute"}
     [:button.wish-list-button
       wish-button
       {:background-color light-blue
        :transition-property "font-size"
        :transition-duration "450ms"
        :position "fixed"
        :right "2rem"
        :top "1rem"
        :z-index 20}
      wish-button-hover
      [:&.small
       (font smaller-size)
       {:top (rem 1.2)}]]
     [:div.wish-list
      full-width
      {:background-color lightest-blue
       :box-shadow "rgba(0,0,0,0.4) 0px 0.125rem 1rem -0.5rem"
       :position "fixed"
       :top 0
       :z-index 20}
      [:&.checking-out
       {:background-color light-yellow
        :height "100%"
        :overflow "scroll"}
       [:div.content
        [:table
         {:width "45%"
          :transition-duration "450ms"}
         [:th :td (font smaller-size)]
         [:td.name :th.total (font default-size)]]
        [:form#checkout
         {:margin "3rem 0 0 3%"
          :width "45%"
          :float "left"
          :transform-origin "center right"
          :transform "translateX(45%)"
          :opacity 0
          :animation-delay "500ms"
          :animation [[reveal-from-right "450ms" :forwards :ease-out]]}
         [:.column
          {:width "50%"
           :float "left"}
          [:label
           {:display "block"
            :margin-bottom "1rem"}
           [:&.grey {:color grey}]
           [:input
            (font default-size)
            {:display "block"
             :border-radius "0.5rem"
             :padding "0.5rem"
             :width "75%"}
            [:&.zip {:width "25%"}]]
           [:select
            (font default-size)
            ^:prefix {:appearance "none"}
            {:background "url('/img/down-arrow.svg') #fff no-repeat center right 0.5rem"
             :display "block"
             :width "80%"
             :border-radius (rem (* modular 0.25))
             :background-color white
             :padding "0.5rem"}]]
          [:div.pay-box
           {:margin-top "8rem"}
           [:a {:text-decoration "underline"}]
           [:input
            ^:prefix {:appearance "none"}
            {:background "url('/img/check-unchecked.svg') no-repeat 0"}
            [:&:checked {:background "url('/img/check-checked.svg') no-repeat 0"}]]
           [:button
            (font bigger-size)
            {:padding "0.5rem 1rem"
             :color black
             :border-radius big-size
             :border "none"
             :background-color white
             :margin "3rem 0"
             :cursor "pointer"}
            [:&:hover
             {:background-color orange}]]]]]]]
      [:header
       {:display "flex"
        :justify-content "space-between"
        :align-items "flex-start"}
       [:h2
         (font larger-size)
         {:margin "3rem 3rem 1rem"
          :font-weight "600"}]
       [:button.back-button
        back-button
        {:margin "3rem 2rem 0"}
        back-button-hover
        [:&.wish-list
         {:background-color white}
         [:&:hover
          {:background-color lightest-blue}]]] ]
      [:div.content
       {:display "flex"}
       [:table
        {:transition-property "width"
         :transition-duration "0ms"
         :border-collapse "collapse"
         :margin "2rem 2rem 2rem 3rem"
         :width "calc(100vw - 5rem)"}
        [:tbody>tr:last-child
         [:th :td {:border-bottom "none"}]]
        [:th :td
         {:font-weight "normal"
          :text-align "left"
          :padding "1rem"
          :border-bottom "thin solid black"}
         [:&.price :&.remove
          {:width "7rem"
           :text-align "right"}]
         [:&.total
          {:text-align "right"}]
         [:&.checkout
          {:text-align "right"}
          [:button.checkout
           button-padding
           {:border-radius bigger-size
            :border "none"
            :background white
            :float "right"}
           [:&:hover {:background light-yellow}]]]]
         [:button
          (font default-size)
          {:border "none"
           :background "none"
           :cursor "pointer"
           :font-family "VeganSans"}]
         [:select
          ^:prefix {:appearance "none"}
          (font default-size)
          {:border-radius bigger-size
           :padding "0.5rem 2rem 0.5rem 1rem"
           :background "url('/img/down-arrow.svg') #fff no-repeat center right 0.5rem"}]]]]
         ]
    [:main#app
     [:&>.fade
      {:opacity 0.4}
      [:button {:display "none !important"}]]
     [:svg
      {:width "100vw"
       :height "95vh"}
      [:g
       [:text
        (font huge-size)]]
      [:g.top
       {:animation [[bounce-top "3s" :infinite :alternate :ease]]}
       [:text
        {:font-family "VeganSans"
         :font-weight 600
         :fill yellow}]
       [:circle
        {:stroke yellow
         :stroke-width "24"
        :fill "none"}]]
      [:g.bottom-right
       {:animation [[bounce-bottom-right "2s" :infinite :alternate :ease-out]]}
       [:text
        {:font-family "KundaBook"
         :font-style "Italic"}]
       [:circle
        {:stroke "none"
        :fill red}]]
      [:g.bottom-left
       {:animation [[bounce-bottom-left "1.44s" :infinite]]}
       [:text
        {:font-family "Hrot"
         :font-weight 200
         :fill dark-blue}]
       [:circle
        {:stroke dark-blue
        :fill "white"}]]
      [:circle.down
       {:animation [["rainbow" "4s" :infinite]]
        :cursor "pointer"}]]
     [:header#font
      full-width
      {:display "flex"
       :justify-content "space-between"
       :padding "1rem 0 2rem"
       :align-items "flex-end"
       :box-shadow "rgba(0,0,0,0.2) 0px 0.125rem 1rem 0px"
       :position "fixed"
       :top 0
       :height (rem 8)
       :z-index 1
       :background-color "white"}
      [:&.small
        (font smaller-size)
       {:padding-bottom "1.25rem"
        :animation [[header-slide-up "500ms" :ease :forwards]]}
       [:&:hover :&.hover
        [:&.fade
         {:display "none"
          :opacity 0}]]
       [:nav.fonts
         [:h2
          (font bigger-size)]
         [:a
          (font smaller-size)
          {:height "1rem"
           :width "0.75rem"
           :margin-bottom "0.5rem"}
          [:&.next {:margin-right (rem 2)}]]]
       [:button
        (font smaller-size)
        {:transition "margin-right 250ms"
         :margin-bottom 0
         :margin-right (rem 12)}]
       [:nav.sections
        {:margin-bottom 0}]]
      [:&.fade
       {:display "none"
        :opacity 0}]
      [:nav.fonts
        {:display "flex"
         :margin-left "2rem"
         :align-items "flex-end"}
        [:h2
         (font huge-size)
         {:transition-property "font-size width"
          :transition-duration "450ms"
          :font-weight "normal"
          :display "inline-block"
          :margin 0
          :font-family "inherit"}]
         [:a
          {:transition-property "width, height, margin-bottom"
           :transition-duration "450ms"
           :background-position-y "0"
           :background-position-x "50%"
           :background-repeat "no-repeat"
           :background-color "rgba(0, 0, 0, 0)"
           :background-size "contain"
           :margin 0
           :margin-bottom "1.75rem"
           :display "inline-block"
           :width "1.25rem"
           :height "2rem"
           :border "none"
           :color "rgba(0, 0, 0, 0)"
           :font-size 0}
          [:&.previous
           {:background-image "url(/img/previous.svg)"
            :margin-left  "0rem"}]
          [:&.next
           {:background-image "url(/img/next.svg)"
            :margin-right (rem 3)
            :margin-left  (rem 1.5)}]]]
      [:nav.sections
        {:transition-property "height, margin-bottom"
         :transition-duration "450ms"
         :display "flex"
         :align-items "flex-start"
         :justify-content "space-between"
         :margin-bottom "1rem"}
       [:a
        {:font-family "VeganSans"
         :padding "0.33rem 1rem"
         :text-align "center"
         :border-radius "1rem"
         :transition-property "background-color"
         :transition-duration "250ms"
         :cursor "pointer"}
        [:&:active
         {:transform "scale(0.8)"
          :transition-property "transform"
          :transition-duration "250ms"}]
        [:&:hover {:background-color lightest-grey}]
        [:&.active
         {:font-weight 700
          :background-color grey
          :transition-duration "250ms"}]]]
      [:button
       wish-button
       {:margin-bottom "1rem"
        :margin-right "2rem"}
       wish-button-hover]]
     wish-box
     [:&>div>div>.wish-box:last-child
      {:top "9rem"
       :padding-top "4rem"
       :position "fixed"
       :z-index 2
       :background-color white
       :padding "3rem"
       :overflow "hidden"
       :margin 0}]
     [:ul#fonts
      [:li {:cursor "pointer"}
       [:div [:a {:padding 0}]]
       [:ul.styles {:margin 0}
        [:li:first-child {:padding-top 0}]]]]
     [:ul#fonts :ul.styles
      {:padding 0
       :margin "0 2rem"}
      [:li
       {:border-bottom (thin-border grey)
        :padding-top "1rem"}
       [:&.fade {:opacity "0.1"}]
       [:&.false {:background-color "#fffffe"}]
       [:div.tools
        {:font-family "VeganSans !important"}
        [:&>div {:float "left"}]
        [:button
         (font smaller-size)
         {:background-position-x "0"
          :background-position-y "50%"
          :background-repeat "no-repeat"
          :background-color "rgba(0, 0, 0, 0)"
          :display "inline-block"
          :margin 0
          :border "none"
          :cursor "pointer"}
         [:&:first-child {:padding-right "0.5rem"}]
         [:&:last-child {:padding-left "0.5rem"}]
         [:&.wish
          (font bigger-size)
          wish-button
          {:width "auto"
           :height "auto"
           :float "right"
           :display "block"
           :transition "transform 250ms"
           :transform "translateY(4rem)"
           :position "relative"
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
         {:display "inline-block"
          :text-align "center"
          :width "4rem"}
         [:&.name
          (font smaller-size)
          {:width "20rem"
           :text-align "left"
           :padding-left "1rem"}]]]
       [:a
        {:display "block"
         :padding "2rem 0"
         :font-family "inherit"
         :font-weight "inherit"
         :font-style "inherit"}]
       [:input
        {:width "calc(100vw - 2rem)"
         :position "relative"
         :border "none"
         :padding "2rem 0"
         :font-family "inherit"
         :font-weight "inherit"
         :font-style "inherit"}]]
       wish-box]
     [:section#styles
      [:ul.styles
       {:padding-top (rem 9)}]]
     [:section#glyphs
      {:margin "6rem 6rem 0"}
      [:select
       ^:prefix {:appearance "none"}
       {:top "8rem"
        :position "fixed"
        :border-radius (rem 1)
        :padding "0.75rem 1rem"
        :margin [[(rem 2) 0]]
        :left "-30rem"
        :z-index 10
        :opacity 0}
       [:&.visible
        {:opacity 0.9
         :left "1rem"}
       (font default-size)]]
      [:img
       full-width
       {:padding [[(rem 3) 0]]}]]
     [:section#details
      {:margin "0 6rem"
       :padding "6rem 0"}
      [:.main {:display "flex"}
       [:.description
        {:width "73%"
         :margin-right "2%"}
        (font bigger-size)]
       [:.features
        {:width "20%"
         :padding-left "5%"}]]
      [:div.opentype
       {:margin-top "2rem"
        :clear "both"}
       [:img
        full-width]]]
     [:section#inuse
      full-width
      {:margin-top (rem 6)
       :overflow "hidden"
       :float "left"}
      [:nav
       {:transform "translate(2rem, 32vw)"
        :position "absolute"
        :width "96vw"
        :z-index 2}
       [:div
        {:width "50%"
         :height "2rem"
         :float "left"}
        [:&:last-child {:text-align "right"}]
       [:button
          {:display "inline-block"
           :background-repeat "no-repeat"
           :background-color white
           :opacity 0.8
           :padding "0.35rem 1rem"
           :margin 0
           :padding-bottom "0.5rem"
           :border "none"
           :border-radius (rem 1)
           :cursor "pointer"}]]]
      [:div.figure-wrapper
       {:transition "transform cubic-bezier(0.4, 0, 0.2, 1) 1s"
        :z-index 1}
       [:figure
        full-width
        {:margin 0
         :float "left"}
        [:figcaption
         {:transform "translate(2rem, 2rem)"
          :background-color white
          :border-radius (rem 2)
          :padding "0.25rem 1rem"
          :opacity "0.8"
          :position "absolute"}
          [:span
           [:&:last-child {:padding 0}]
           {:padding "0 1rem 0 0"
            :display "inline-block"}
           [:&:first-child
           {:font-weight "bold"}]]]
        [:img
         {:width "100%"
          :max-height "100vw"
          :float "left"}]]]]
     [:section#foundry
      [:header
       [:h2
        (font larger-size)
        {:width "48%"
         :margin "0 10%"
         :padding-top (rem 6)
         :padding-bottom (rem 2)
         :font-weight "normal"}]]
      [:div.about-address
       {:background-color green
        :display "flex"}
       [:div.text
        (font bigger-size)
        {:flex 6
         :margin-left "10%"
         :padding-top (rem 4)}]]
      [:div.contact
       {:flex 8
        :overflow "visible"}
       [:address
        (font larger-size)
        {:transition-property "transform"
         :transition-duration "1750ms"
         :transition-timing-function "cubic-bezier(0.18, 0.89, 0.32, 1.28)"
         :margin-left "10%"
         :width "70%"
         :padding (rem 3)
         :font-style "normal"
         :background-color grey
         :margin-top (rem 3)
         :margin-bottom (rem 1)
         :border-radius (rem 2)}
        [:&.visible {:transform "translateX(0rem)"}]
        [:&.hidden {:transform "translateX(70rem)"}]
        [:a
         (font bigger-size)
         {:border-radius (rem 3)
          :padding "0.5rem 1rem"}
         [:&:hover
            {:background-color "white !important"
             :color "black !important"}]]
        [:div.email
          {:margin-top (rem 1)}
         [:a
          (font bigger-size)
          {:color "white"
           :background-color dark-grey}]]
        [:nav.social
         {:margin-top (rem 3)
          :line-height 1
          :display "flex"
          :justify-content "space-between"}
         [:a
          {:color "white"}
          [:&.facebook
           {:background-color "#0060ff"}]
          [:&.twitter
           {:background-color "#00beff"}]
          [:&.instagram
           {:background-color "#a04aff"}]]]]]
      [:.lost
       {:background-color green
        :width "100%"}
       [:p
        (font bigger-size)
        {:margin-left "10%"
         :padding-top large-size
         :width "40%"}]
       [:.fonts
        {:width "90%"
         :margin-left "10%"}
        [:span
         {:padding "0 2rem 0 0"
          :display "inline-block"
          :line-height 1.5}
          [:a
           {:display "inline-block"
            :transition-property "transform"
            :transition-duration "250ms"
            :font-size "6vw"
            :color white
            :padding "0 1rem 0 0"
            :font-family "inherit"}
           [:&:hover
            {:transform "scale(1.10)"
             :color black}]]]]]
      [:.people
       {:background-color light-green
        :display "flex"}
       [:.person
          {:width "48%"
           :margin-top "3rem"
           :padding "2rem"}
        [:.photo
         [:img
          {:border-radius "50%"
           :width "100%"}
         [:p
           (font bigger-size)]]]]]
       [:.studio
        [:img
         {:border "none"
          :padding 0
          :margin 0
          :width "100%"}]]]
     [:section#custom
      {:background-color dark-grey
       :position "absolute"
       :top 0}
      [:header
       [:h2
        (font large-size)
        {:color violet
         :width "80%"
         :margin "0 10%"
         :padding-top (rem 6)
         :padding-bottom (rem 2)
         :font-weight "normal"}]]
      [:div.text
       {:width "40%"
        :margin "0 10% 0 10%"
        :float "left"}
       [:p (font bigger-size)
        [:span.email
         {:margin-top "1rem"}
         [:a
          {:padding "0.35rem 1rem"
           :display "inline-block"
           :color white
           :border-radius "1rem"
           :background-color "black"}
          [:&:hover
           {:background-color "white !important"
            :color "black !important"}]]]]]
      [:ul.advantages
       {:width "30%"
        :margin "0 10% 0 0"
        :float "left"}
       [:li {:margin "0 0 2rem 0"}
        [:&:before
         (font bigger-size)
         {:content "\"• \""}]
        [:&.red:before {:color red}]
        [:&.yellow:before {:color yellow}]]]
      [:div.cases
       {:clear "both"
        :font-family "VeganSans"}
       [:&>div
        {:margin-bottom (rem 4)}
        [:&.fade {:opacity 0.4}]
        [:h3
         (font monster-size)
         {:margin 0
          :padding 0}]
        [:div.details
         {:width "80%"
          :margin "0 10% 0 10%"
          :display "flex"
          :justify-content "space-between"}
         [:&>div
          {:margin "2rem 1rem"
           :font-family "VeganSans"}]]
        [:img {:width "100%"}]]]]
      [:section#first-aid
       default-padding
       {:display "flex"
        :background-color darker-grey
        :height "100%"}
       [:div.column
        {:flex 1
         :height "100%"
         :margin-right (rem 2)}
        [:h2
         (font default-size)
         {:font-weight "normal"
          :width "75%"
          :margin-bottom (rem 2)
          :line-height bigger-size}]
        [:h3
         (font bigger-size)
         {:font-weight "normal"
          :margin-bottom (rem 0.5)}
         [(sel/& (sel/not (sel/first-child))) {:margin-top (rem 3)}]]
        [:h4
         {:margin-bottom (rem 1)}
         [:button
          button-padding
          (font default-size)
          {:background-color darkerer-grey
           :border-radius default-size
           :cursor "pointer"
           :position "relative"
           :z-index "2"
           :border "none"}
          [:&.opened {:background-color dark-yellow}]]]
        [:&:first-child
         [:p
          (font smaller-size)
          {:width "75%"
           :margin-bottom (rem 1)}]]
        [(sel/& (sel/not (sel/first-child)))
         [:p
          (font smaller-size)
          {:margin [[0 (rem 2) 0 (rem 1)]]
           :width "75%"
           :max-height "0rem"
           :position "relative"
           :z-index "1"
           :opacity 0}
          [:&.opened
           {:animation [[section-slide-down "550ms" :alternate :forwards]]}]
          [:&.closed
           {:animation [[section-slide-up "250ms" :alternate :forwards]]}]]]]]]])

