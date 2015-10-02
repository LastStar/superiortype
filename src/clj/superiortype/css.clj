(ns superiortype.css
  (:refer-clojure :exclude [+ - * / rem])
  (:require [garden.def :refer [defstylesheet defstyles defkeyframes]]
            [garden.units :as u :refer [rem]]
            [garden.arithmetic :refer [+ - * /]]
            [garden.stylesheet :refer [at-font-face]]))


;; FIXME with more garden
(def grey "#b3b3b3")
(def lighter-grey "#F2F2F2")
(def lightest-grey "#FAFAFA")
(def dark-grey "#555555")
(def black "#000")
(def white "#fff")
(def light-blue "#00ffff")
(def green "#00ffa0")
(def light-green "#00ffa0")
(def blue "#0ff")
(def dark-blue "#5F00FF")
(def clear "rgba(0, 0, 0, 0)")
(def yellow "#fffd38")
(def red "#f00")

(def default-margin
  {:margin (rem 2)})
(def bigger-margin
  {:margin "0 6rem"})
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
(defn font
  ([] font 1)
  ([size] {:font-size size}))
(defn thin-border
  ([] thin-border black)
  ([color] (str "thin solid " color)))
(def wish-button
  (merge {:padding "0.35rem 1rem"
   :color black
   :background-color "white"
   :border-radius "1rem"
   :border (thin-border light-blue)
   :font-family "VeganSans"
   :font-weight "normal"}
   (font default-size)))

(defstyles basic
  [:html
   (font (u/px 16))]
  [:h1 :h2 :h3 :h4 :h5 :a :a:hover :select :input
   {:font-family "VeganSans"
    :border "none"
    :line-height 1.2}]
  [:a
   {:color "black"
    :text-decoration "none"
    :font-weight "normal"}]
  [:ul {:list-style-type "none"}]
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
     :src "url(\"../fonts/Hrot_Hair.woff\") format(\"woff\")"})
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
   {:font-size "1.618rem"}]
  )

;; Keyframes
;; FIXME move to its own file animation
(defkeyframes header-slide-up
  [:0% {:height "8rem"}]
  [:80% {:height "7rem"}]
  [:100% {:height "6rem"}])

(defkeyframes slide-right
  [:0% {:transform "translateX(-4rem)" :opacity 0}]
  [:90% {:transform "translateX(0.5rem)" :opacity 0.8}]
  [:97% {:transform "translateX(-0.2rem)" :opacity 0.9}]
  [:100% {:transform "translateX(0rem)" :opacity 1}])

(defkeyframes slide-left
  [:0% {:transform "translateX(0rem)" :opacity 1}]
  [:30% {:transform "translateX(0rem)" :opacity 0.6}]
  [:80% {:transform "translateX(0rem)" :opacity 0.1}]
  [:100% {:transform "translateX(-40rem)" :opacity 0}])

(defkeyframes slide-up
  [:0% {:transform "translateY(10rem)"}]
  [:100% {:transform "translateY(0rem)"}])

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

(defkeyframes address-slide
  [:0% {:transform "translateX(55rem)"}]
  [:85% {:transform "translateX(-2rem)"}]
  [:90% {:transform "translateX(1.5rem)"}]
  [:100% {:transform "translateX(0rem)"}])

(defkeyframes rainbow
  [:0% {:fill dark-blue :transform "translate(3vw, 95vh) scale(1)"}]
  [:33% {:fill red :transform "translate(3vw, 93vh) scale(1.2)"}]
  [:67% {:fill yellow :transform "translate(3vw, 93vh) scale(1.4)"}]
  [:100% {:fill white :transform "translate(3vw, 95vh)"}])

(defstyles wish-box
 [:div.wish-box
  default-margin
  {:display "flex"
   :flex-direction "row"
   :align-items "flex-end"
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
   [:h5
    (font bigger-size)
    {:float "left"
     :width "61%"
     :margin "0 0 1rem"
     }]
   [:.price
    (font smaller-size)
    {
    :font-family "VeganSans"
    :float "right"
    :width "32%"
    :margin "0.25rem 0"
    :background-color "white"
    :text-align "center"
    :border-radius "1rem"
    :padding "0.25rem 0.5rem"
     }]
   [:.description
    {
     :clear "both"
     :transition-property "opacity"
     :transition-duration "250ms"
     :opacity 0}
   [:h6
    (font default-size)
    {:margin "6rem 0 0"}]
   [:p {:margin "0"}]]
   [:&:hover
    [:.description
     {:opacity 1}]]
   [:&.demo
    {:background-color lighter-grey}
    [:h5
      {:margin "0"
      :font-style "italic"
      :font-weight "300"}]
    [:.price
      { :width "30%"
      :margin "0.3rem 0 0.25rem 0"}]]
   [:&.basic
    {:background-color blue
     :animation-delay "0.5s"}
     [:h5
      {:font-weight "normal"}]]
   [:&.premium
    {:background-color blue
     :animation-delay "1.25s"}
     [:.description
      {:margin-bottom "1rem"}]]
   [:&.superior
    {:background-color dark-blue
     :color white
     :animation-delay "2s"
     :max-height "20rem"}
     [:h5
      {:font-weight "900"}]
     [:.price
      {:width "28%"
       :color black}]
     [:.description
      {:margin "9rem 0 7rem 0"}]]]])

(defstyles site
  ;; Keyframes
  slide-right
  slide-left
  slide-up
  header-slide-up
  bounce-top
  bounce-bottom-left
  bounce-bottom-right
  rainbow
  address-slide
  ;; Partials
  basic
  fonts
  [:body
   (font default-size)
   {:font-family "VeganSans"
    :min-width (rem 62)
    :margin "auto"
    :line-height 1.5}
    [:header#header
     {:display "flex"
      :flex-direction "row"
      :position "fixed"
      :left "-1rem"
      :background-color clear
      :z-index 10
      :padding "1rem 2rem 0"
      :height (rem 2)}
     [:div.menu
      {:padding "0.25rem 1rem 1rem 1rem"
       :border-radius "1rem"}
      [:&.opaque {:background-color "rgba(255, 255, 255, 0.9)"}]
      [:h1
       {:display "inline-block"
        :margin "0 0 1rem 0"}
       (font bigger-size)
       [:a
        {:font-weight "bold"}]]
      [:nav
       {:animation [[slide-left "2500ms" :ease :forwards]] :display "inline-block"}
       [:a
          {:display "inline-block"
           :padding-left "1rem"}
          [:&:first-child {:padding-left "3rem"}]]
       [:&.visible {:animation [[slide-right "250ms" :ease :forwards]]}]]]]
    [:main#app
     [:svg
      {:width "100vw"
       :height "100vh"}
      [:g
       [:text
        (font huge-size)]]
      [:g.top
       {:animation [[bounce-top "3s" :infinite :alternate :ease]] }
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
       {:animation [[bounce-bottom-left "1.44s" :infinite ]]}
       [:text
        {:font-family "Hrot"
         :font-weight 200
         :fill dark-blue
         }]
       [:circle
        {:stroke dark-blue
        :fill "white"}]]
      [:circle.down
       {
        :animation [["rainbow" "4s" :infinite]]}]
      ]
     [:header#font
      {:transition-property "opacity, background-color"
       :transition-duration "500ms"
       :transition-delay "100ms"
       :opacity "1"
       :display "flex"
       :justify-content "space-between"
       :padding "1rem 0 2rem"
       :align-items "flex-end"
       :box-shadow "rgba(0,0,0,0.2) 0px 0.125rem 1rem 0px"
       :position "fixed"
       :top 0
       :width "100%"
       :height (rem 8)
       :z-index 1
       :background-color "white"
       }
      [:&.small
       {:box-shadow "none"
        :background-color clear
        :opacity "0.2"
        :animation [[header-slide-up "500ms" :ease :forwards]]
        :transition-delay "750ms"
        :transition-duration "500ms"
        }
       [:&:hover :&.hover
        {:box-shadow "rgba(0,0,0,0.08) 0px 0.125rem 1rem 0px"
         :opacity "0.99"
         :transition-delay "100ms"
        :background-color white}
        [:&.fade
         {:display "none"
          :opacity 0}]]
       [:nav.fonts
         [:h2 (font larger-size)]
         [:a {:height "1.5rem"
              :margin-bottom "0.75rem"}]]
       [:button
        {:background-color clear}]]
      [:&.fade
       {:display "none"
        :opacity 0}]
      [:nav.fonts
        {:display "flex"
         :margin-left "2rem"
         :align-items "flex-end"}
        [:h2
         (font huge-size)
         {:transition-property "font-size"
          :transition-duration "450ms"
          :font-weight "normal"
          :display "inline-block"
          :margin 0
          :font-family "inherit"}]
         [:a
          {:transition-property "height, margin-bottom"
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
            :margin-right "2rem"
            :margin-left  "-1rem"}]
          [:&.next
           {:background-image "url(/img/next.svg)"
            :margin-left "2rem"}]]]
      [:nav.sections
        {:display "flex"
         :align-items "flex-start"
         :justify-content "space-between"
         :margin-bottom "1rem"}
       [:a
        {:font-family "VeganSans"
         :padding "0.5rem 1rem"
         :text-align "center"
         :border-radius "1rem"
         :transition-property "background-color"
         :transition-duration "250ms"}
        [:&:hover {:background-color lightest-grey}]
        [:&.active
         {:font-weight 700
          :background-color grey
          :transition-duration "250ms"}]]]
      [:button
       wish-button
       {:margin-bottom "1rem"
        :margin-right "2rem"}]]
     wish-box
     [:&>div>div>.wish-box:last-child
      {:top "9rem"
       :padding-top "4rem"
       :position "fixed"
       :z-index 2
       :background-color white
       :padding "3rem"
       :overflow "hidden"
       :margin 0
       } ]
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
           :transform "translateY(4rem)"}]
          [:&.smaller:hover
           {:transform "scale(0.8)"
            :transition "transform 250ms"}]
          [:&.list:hover
           {:transform "translateY(0.3rem)"
            :transition "transform 250ms"}]
         [:&.bigger:hover
           {:transform "scale(1.2)"
            :transition "transform 250ms"}] ]
         [:a (font smaller-size) ]
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
        {:border "none"
         :width "100%"
         :padding "2rem 0"
         :font-family "inherit"
         :font-weight "inherit"
         :font-style "inherit"}]]
       wish-box]
     [:section#styles
      [:ul.styles
       {:padding-top (rem 11)}]]
     [:section#glyphs
      bigger-margin
      [:select
       ^:prefix {:appearance "none"}
       {:border-radius (rem 1)
        :padding "0.35rem 1rem"
        :margin [[(rem 2) 0]]}
       (font default-size)]
      [:img
       {:padding [[(rem 3) 0]]
        :width "100%"}]]
     [:section#details
      bigger-margin
      {:float "left"
       :padding "4rem 0"}
      [:.description
       {:float "left"
        :width "73%"
        :margin-right "2%"}
       (font bigger-size)]
      [:.features
       {:float "left"
        :width "17%"
        :padding-left "8%"}]
      [:div.opentype
       [:img
        {:width "100%"
         :float "left"}]]]
     [:section#inuse
      {:overflow "hidden"
       :float "left"
       :width "100%"}
      [:nav
       {:transform "translate(2vw, 32vw)"
        :position "absolute"
        :width "96vw"
        :z-index 2}
       [:a
          {:background-repeat "no-repeat"
           :background-color "rgba(0, 0, 0, 0)"
           :margin 0
           :padding-bottom "0.5rem"
           :display "inline-block"
           :width "50%"
           :height "2rem"
           :border "none"
           :color "rgba(0, 0, 0, 0)"
           :font-size 0}
          [:&.previous
           {:background-image "url(/img/circle-previous.svg)"
            :background-position "0%"}]
          [:&.next
           {:background-image "url(/img/circle-next.svg)"
            :background-position "100%"}]]]
      [:div.figure-wrapper
       {:transition "transform cubic-bezier(0.4, 0, 0.2, 1) 1s"
        :z-index 1}
       [:figure
        {:margin 0
         :width "100%"
         :float "left"}
        [:figcaption
         {:transform "translate(3vw, -120%)"
          :width "30vw"
          :background-color grey
          :border-radius (rem 2)
          :padding (rem 2)
          :opacity "0.75"
          :position "absolute"}
         [:ul
          {:margin "0"
           :padding "0"}
          [:li:first-child
           {:font-weight "bold"}]]]
        [:img {:width "100%" :max-height "100vw"}]]]]
     [:section#foundry
      [:header
       [:h2
        (font larger-size)
        {:width "48%"
         :margin "0 10%"
         :padding-top (rem 6)
         :padding-bottom (rem 2)
         :font-weight "normal"}
      ]]
      [:div.about-address
       {:background-color green
        :display "flex"}
       [:div.text
        (font bigger-size)
        {:flex 6
         :margin-left "10%"
         :padding-top (rem 4)}
        ]]
      [:div.contact
       {:flex 8
        :overflow "hidden"}
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
        [:&.hidden {:transform "translateX(50rem)"}]
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
            {:transform "scale(1.10) rotateZ(2deg)"
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
          :width "100%"}]]]]])

