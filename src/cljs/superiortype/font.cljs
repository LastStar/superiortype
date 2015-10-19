(ns superiortype.font
  (:require-macros [cljs.core.async.macros :refer [go-loop]])
  (:require
    [superiortype.cart :as cart]
    [superiortype.events :refer [scroll-chan-events]]
    [superiortype.utils :refer [element get-top section-range modular
                                smooth-scroll]]
    [re-frame.core :refer [dispatch subscribe]]
    [cljs.core.async :refer [<!]]
    [clojure.string :refer [replace split lower-case capitalize join]]))

;; -------------------------
;; Scrolling
;; FIXME name
(def all-sections ["styles", "glyphs", "details", "inuse"])

(defn update-sections-ranges []
  (def sections-ranges (mapv section-range all-sections)))

(defn invalidate-sections-ranges []
  (set! sections-ranges false))

(defn section-at [y]
  (when (not sections-ranges)
    (update-sections-ranges))
  (ffirst (filterv
    (fn [vec]
      (let [[_ top bottom] vec]
        (and (<= top y) (>= bottom y))))
      sections-ranges)))

(defn listen! []
  (let [chan (scroll-chan-events)]
    (dispatch [:listening :font])
    (go-loop []
       (let [new-y (<! chan)
             new-section (section-at new-y)
             header-class (subscribe [:header-class])]
         (if (< new-y 38)
           (when-not (= @header-class "normal")
             (dispatch [:header-class-changed "normal"]))
           (when-not (= @header-class "small")
             (dispatch [:header-class-changed "small"])))
         (when-not (= (deref (subscribe [:current-section])) new-section)
           (dispatch [:section-scrolled new-section])))
         (recur))))

;; -------------------------
;; Components
(defn style-line [style]
  (let [weight (lower-case style)]
    (fn []
      (let [current-font (subscribe [:current-font])
            font-name (:name @current-font)
            style-name (str font-name " " style)
            style-id (replace (lower-case style-name) #" " "-")
            wishing-one (subscribe [:wishing])
            i-am-wishing (= @wishing-one style-id)
            someother-is-wishing (and @wishing-one (not i-am-wishing) "fade")
            all-edited (subscribe [:edited])
            i-was-edited (some #{style-id} @all-edited)
            size (subscribe [:size-query style-id 123])
            smaller-size (/ @size modular)
            bigger-size (* @size modular)]
        [:li {:class someother-is-wishing}
          [:div.tools
            (if @wishing-one
             [:span " "]
             [:div
              [:button.smaller {:on-click  #(dispatch [:size-changed style-id smaller-size])} "Smaller"]
              [:span (str (Math/floor @size) "pt")]
              [:button.bigger {:on-click #(dispatch [:size-changed style-id bigger-size])} "Bigger"]])
           (when i-was-edited
             [:span.name (str font-name " " style)])
           (if i-am-wishing
             [:button.wish
              {:on-click #(dispatch [:wishing-canceled])}
              "No thanks"]
             (when-not someother-is-wishing
               [:button.wish
                ; FIXME move to handler
                {:on-click #(let [style-top (get-top (-> % .-target .-parentElement .-parentElement))]
                              (dispatch [:wishing-started style-id style-top]))}
                "Wish"]))]
          [:div {:style {:font-size (str @size "pt")} :class weight}
              [:input
               {:on-change
                (fn [e]
                  (let [value (-> e .-target .-value)]
                   (if (= value "")
                     (dispatch [:remove-edited style-id])
                     (dispatch [:add-edited style-id]))))
                :placeholder style-name
                :style {:font-size @size}}]]
           (when i-am-wishing
             [cart/wish-box style-id]
            )]))))

(defn font-header []
  (fn []
    (let [current-section (deref (subscribe [:current-section]))
          current-font (subscribe [:current-font])
          id (:id @current-font)
          name (:name @current-font)
          wishing-one (subscribe [:wishing])
          i-am-wishing (= @wishing-one id)
          someother-is-wishing (and @wishing-one (not i-am-wishing) "fade")
          header-class (str (deref (subscribe [:header-class]))
                            " " (or (and i-am-wishing "hover")
                                    (and someother-is-wishing "fade")))
          previous (:previous @current-font)
          next (:next @current-font)]
      [:div
       [:header#font
        {:on-click #(when-not someother-is-wishing (smooth-scroll (element "font")))
         :class header-class}
        [:nav.fonts {:class id}
         [:a.previous {:href (str "#/font/" previous)} previous]
         [:a.next {:href (str "#/font/" next)} next]
         [:h2 name]]
      (when-not i-am-wishing
        [:nav.sections
         (for [sec all-sections]
           ^{:key sec}
           [:a
            {:class (str sec (when (= sec current-section) " active"))
             :on-click (fn [e]
                         (dispatch [:section-changed sec])
                         (.stopPropagation e))}
            (capitalize sec)])
         [:a.specimen {:href (str "/pdf/superior-type-" id ".pdf")} "Specimen"]])
        (if i-am-wishing
         [:button.wish
          {:on-click #(do
                        (dispatch [:wishing-canceled])
                        (.preventDefault %))}
          "No thank you"]
         (when-not someother-is-wishing
           [:button.wish
            {:on-click #(let [header-top (get-top (-> % .-target))]
                          (dispatch [:wishing-started id header-top]))}
            "Wish whole family"]))]
        (when i-am-wishing
           [cart/wish-box id])])))

(defn styles-section []
  (fn []
    (let [current-font (subscribe [:current-font])
          styles (:styles @current-font)
          name (:name @current-font)]
      [:section#styles {:style {:font-family (replace name #" " "")}}
         [:ul.styles
          (for [style styles]
            ^{:key style}
            [style-line style])]])))

(defn glyphs-section []
    (fn []
      (let [current-font (subscribe [:current-font])
            id (:id @current-font)
            charsets (:charsets @current-font)
            selected-charset (deref (subscribe [:selected-charset]))
            charset-visibility (subscribe [:charset-visibility])]
        [:section#glyphs
         [:select
          {:class @charset-visibility
           :defaultValue (and selected-charset (first charsets))
           :on-change #(let [value (-> % .-target .-value)]
                         (dispatch [:charset-selected value]))}
          (for [charset charsets]
            ^{:key charset}
            [:option
             {:value charset}
             (join ", " (mapv capitalize (split charset #"-")))])]
         [:img {:size "1600x1087" :src (str "/img/glyphs/" id "/" (or selected-charset (first charsets)) ".svg")}]])))

(defn details-section []
  (let [current-font (deref (subscribe [:current-font]))
        id (:id current-font)
        details (split (current-font :details) #"\n")
        styles (count (current-font :styles))
        glyphs (current-font :glyphs)]
    [:section#details
     [:div.main
      [:div.description
       (for [p details]
         ^{:key (hash p)}
         [:p.text p])]
      [:div.features
       [:p
        [:strong "Styles in font family"]
        [:br]
        [:span styles]]
       [:p
        [:strong "Number of glyphs per font"]
        [:br]
        [:span glyphs]]
       [:p
        [:strong "Language Support"]
        [:br]
        [:span "Latin Std"]]
       [:p
        [:strong "Designer"]
        [:br]
        [:span "Vojtěch Říha"]]
       [:p
        [:strong "Release year"]
        [:br]
        [:span "2015"]]]]
     [:div.opentype
      [:h4 "OpenType features"]
      [:img
        {:src (str "/img/" id "-opentype.svg")}]]]))

(defn inuse-section []
  (fn []
    (let [current-font (subscribe [:current-font])
          inuses (@current-font :inuse)
          inuses-count (count inuses)
          step (subscribe [:step])
          show-controlls (deref (subscribe [:show-controlls]))]
      [:section#inuse
       (when show-controlls
        [:nav
         {:on-mouse-enter #(dispatch [:show-controlls-changed true])}
         [:div
          [:button.previous
            {:on-click #(dispatch [:step-changed (dec @step) inuses-count])}
            "Previous"]]
         [:div
           [:button.next
            {:on-click #(dispatch [:step-changed (inc @step) inuses-count])}
            "Next"]]])
      [:div.figure-wrapper
       {:on-mouse-enter #(dispatch [:show-controlls-changed true])
        :on-mouse-leave #(dispatch [:show-controlls-changed false])
        :style
        {:width (str inuses-count "00vw")
         :transform (str "translateX(-" @step "00vw)")}}
       (doall (for [inuse inuses]
         (let [img (inuse :img)]
         ^{:key img}
         [:figure
          {:style
           {:width (str (/ 100 inuses-count) "%")}}
          (when show-controlls
           [:figcaption
             (for [p (split (inuse :text) #"\n")]
               ^{:key (hash p)}
               [:span p])])
          [:img {:src (str "/img/" img)}]
          ])))]])))

(defn page []
  (when-not (deref (subscribe [:listening :font])) (listen!))
  (let [showing-wist-list (subscribe [:showing-wish-list])
        wishing-one (subscribe [:wishing])]
    [:div {:class (and @showing-wist-list "fade")}
     [font-header]
     [styles-section]
     (when-not @wishing-one
       [:div
         [glyphs-section]
         [details-section]
         [inuse-section]])]))
