(ns superiortype.font
  (:require-macros [cljs.core.async.macros :refer [go-loop]])
  (:require
    [superiortype.events :refer [scroll-chan-events]]
    [superiortype.utils :refer [element get-bottom get-top header-bottom
                                section-range scroll-to modular]]
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
         (if (< new-y 64)
           (when-not (= @header-class "normal")
             (dispatch [:header-class-changed "normal"]))
           (when-not (= @header-class "small")
             (dispatch [:header-class-changed "small"])))
         (when-not (= (deref (subscribe [:current-section])) new-section)
           (dispatch [:section-changed new-section])))
         (recur))))

;; -------------------------
;; Components
(defn wish-box [wishing-one]
  [:div.wish-box
   [:div.demo
    {:on-click #(dispatch [:add-to-wish-list wishing-one :demo])}
    [:h5 "Demo"]
    [:div.price "Free"]]
   [:div.basic
    {:on-click #(dispatch [:add-to-wish-list wishing-one :basic])}
    [:h5 "Basic"]
    [:div.price "From $30"]
    [:div.description
     [:h6 "Standart font encoding"]
     [:p "Uppercase, Lowercase, Ligatures, Currency, Numerals, Fractions, Mathematical, Punctuations"]]]
   [:div.premium
    {:on-click #(dispatch [:add-to-wish-list wishing-one :premium])}
    [:h5 "Premium"]
    [:div.price "From $50"]
    [:div.description
     [:h6 "Extended font encoding"]
     [:p "Uppercase, Lowercase, Smallcaps, Extended Ligatures, Superscript, Subscript, Extend Currency, Extended Numerals, Extended Fractions, Mathematical, Punctuations, Arrows"]]]
   [:div.superior
    {:on-click #(dispatch [:add-to-wish-list :superior])}
    [:h5 "Superior"]
    [:div.price "$1920"]
    [:div.description
     [:p "All three typefaces in the Premium package: Hrot, Kunda Book, Vegan Sans"]
     [:p "+ Our next two released typefaces for free."]]]
   [:a.help "What?"]])

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
              [:button.smaller {:on-click  #(dispatch [:size-changed style-id smaller-size])} "smaller"]
              [:span (str (Math/floor @size) "px")]
              [:button.bigger {:on-click #(dispatch [:size-changed style-id bigger-size])} "BIGGER"]])
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
                              (scroll-to style-top)
                              (dispatch [:wishing-started style-id]))}
                "Wish"]))]
          [:div {:style {:font-size (str @size "px")} :class weight}
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
             [wish-box style-id]
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
        {:on-click #(when-not someother-is-wishing (scroll-to (get-top (element "font"))))
         :class header-class}
        [:nav.fonts {:class id}
         [:a.previous {:href (str "#/font/" previous "/" current-section)} previous]
         [:h2 name]
         [:a.next {:href (str "#/font/" next "/" current-section)} next]]
      (when-not i-am-wishing
        [:nav.sections
         (for [sec all-sections]
           ^{:key sec}
           [:a
            {:href (str "#/font/" id "/" sec)
             :class (str sec (when (= sec current-section) " active"))
             :on-click (fn [e]
                         (scroll-to (get-top (element sec)))
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
            {:on-click #(do
                          (scroll-to (get-top (-> % .-target)))
                          (dispatch [:wishing-started id]))}
            "Wish whole family"]))]
        (when i-am-wishing
           [wish-box id])])))

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
            charset-position (subscribe [:charset-position])]
        [:section#glyphs
         [:select
          {:class @charset-position
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
       [:span "2015"]]]
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
         [:a.previous
          {:on-click #(dispatch [:step-changed (dec @step) inuses-count])}
          "Previous"]
         [:a.next
          {:on-click #(dispatch [:step-changed (inc @step) inuses-count])}
          "Next"]])
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
          [:img {:src (str "/img/" img)}]
          (when show-controlls
           [:figcaption
            [:ul
             (for [p (split (inuse :text) #"\n")]
               ^{:key (hash p)}
               [:li p])]])])))]])))

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
