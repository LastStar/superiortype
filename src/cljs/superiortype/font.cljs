(ns superiortype.font
  (:require-macros [cljs.core.async.macros :refer [go-loop]])
  (:require
    [superiortype.events :refer [scroll-chan-events]]
    [superiortype.utils :refer [element get-bottom get-top header-bottom
                           section-range scroll-to no-scroll-body scroll-body modular]]
    [re-frame.core :refer [dispatch subscribe]]
    [cljs.core.async :refer [<!]]
    [clojure.string :refer [replace split lower-case capitalize join]]))

;; -------------------------
;; Scrolling
;; FIXME name
(defn update-sections-ranges []
  (def sections-ranges (mapv section-range (:all-sections @app-state))))

(defn section-at [y]
  (when (not sections-ranges)
    (update-sections-ranges))
  (ffirst (filterv
    (fn [vec]
      (let [top (get vec 1)
            bottom (get vec 2)]
      (and (<= top y) (>= bottom y))))
    sections-ranges)))

(defn listen! []
  (let [chan (scroll-chan-events)]
    (dispatch [:listening true])
    (go-loop []
       (let [new-y (<! chan)
             new-section (section-at new-y)
             header-class (:header-class @app-state)]
         (if (< new-y 64)
           (when-not (= header-class "normal")
             (dispatch [:header-class-changed "normal"]))
           (when-not (= header-class "small")
             (dispatch [:header-class-changed "small"])))
         (when-not (= (:section @app-state) new-section)
           (dispatch [:section-changed new-section])))
         (recur))))

;; -------------------------
;; Components
(defn style-line [style]
  (let [weight (lower-case style)]
    (fn []
      (let [current-font (subscribe [:current-font])
            font-name (:name @current-font)
            style-name (str font-name " " style)
            style-id (replace (lower-case (style-name) #" " "-"))
            wishing-one (subscribe [:wishing])
            i-am-wishing (= @wishing-one style-id)
            someother-is-wishing (and @wishing-one (not i-am-wishing) "fade")
            all-edited (subscribe [:edited])
            i-was-edited (some #{style-id} all-edited)
            size (subscribe [:size-query style-name])]
        [:li {:class someother-is-wishing}
          [:div.tools
           [:button.smaller {:on-click  #(dispatch [:size-changed style-id (/ size modular)])} "smaller"]
           [:span (str (Math/floor size) "px")]
           [:button.bigger {:on-click #(dispatch [:size-changed style-id (* size modular)])} "BIGGER"]
           (when i-was-edited
             [:span (str font-name " " style)])
           (if i-am-wishing
             [:button.wish
              {:on-click #(do
                            (scroll-body)
                            (dispatch [:wishing-changed nil]))}
              "No thanks"]
             (when-not someother-is-wishing
               [:button.wish
                {:on-click #(do
                              (no-scroll-body)
                              (scroll-to (get-top (-> % .-target)))
                              (dispatch [:wishing-changed style-name]))}
                "Wish"]))]
          [:div {:style {:font-size (str size "px")} :class weight}
              [:input
               {:on-change
                (fn [e]
                  (let [value (-> e .-target .-value)]
                   (if (= value "")
                     (dispatch [:add-edited  style-id])
                     (dispatch [:remove-edited  style-id]))))
                :placeholder style-name
                :style {:font-size size}}]]
           (when i-am-wishing
            [:div.wish-box
             [:div.demo
              [:h5 "Demo"]
              [:div.price "Free"]]
             [:div.basic
              [:h5 "Basic"]
              [:div.price "From $30"]
              [:div.description
               [:h6 "Standart font encoding"]
               [:p "Uppercase, Lowercase, Ligatures, Currency, Numerals, Fractions, Mathematical, Punctuations"]]]
             [:div.premium
              [:h5 "Premium"]
              [:div.price "From $50"]
              [:div.description
               [:h6 "Extended font encoding"]
               [:p "Uppercase, Lowercase, Smallcaps, Extended Ligatures, Superscript, Subscript, Extend Currency, Extended Numerals, Extended Fractions, Mathematical, Punctuations, Arrows"]]]
             [:div.superior
              [:h5 "Superior"]
              [:div.price "$1920"]
              [:div.description
               [:p "All three typefaces in the Premium package: Hrot, Kunda Book, Vegan Sans"]
               [:p "+ Our next two released typefaces for free."]]]
             [:a.help "What?"]])]))))

(defn font-header []
  (fn []
    (let [current-section (subscribe [:section])
          current-font (subscribe [:current-font])
          id (:id @current-font)
          name (:name @current-font)
          wishing-one (subscribe [:wishing])
          i-am-wishing (= @wishing-one id)
          someother-is-wishing (and @wishing-one (not i-am-wishing) "fade")
          header-class (str (:header-class @app-state)
                            " " (or (and i-am-wishing "hover")
                                    (and someother-is-wishing "fade")))
          previous (get current-font "previous")
          next (get current-font "next")]
      [:div
       [:header#font
        {:on-click #(scroll-to (get-top (element "font")))
         :class header-class}
        [:nav.fonts {:class id}
         [:a.previous {:href (str "#/font/" previous "/" current-section)} previous]
         [:h2 name]
         [:a.next {:href (str "#/font/" next "/" current-section)} next]]
        [:nav.sections
         (for [sec (:all-sections @app-state)]
           ^{:key sec}
           [:a
            {:href (str "#/font/" id "/" sec)
             :class (str sec (if (= sec current-section) " active" ""))
             :on-click (fn [e]
                         (scroll-to (get-top (element sec)))
                         (.stopPropagation e))}
            (capitalize sec)])
         [:a.specimen {:href (str "/pdf/superior-type-" id ".pdf")} "Specimen"]]
        (if i-am-wishing
         [:button.wish
          {:on-click #(do
                        (scroll-body)
                        (swap! app-state assoc  :wishing nil)
                        (.preventDefault %))}
          "No thank you"]
         (when-not someother-is-wishing
           [:button.wish
            {:on-click #(do
                          (no-scroll-body)
                          (scroll-to (get-top (-> % .-target)))
                          (swap! app-state assoc :wishing name))}
            "Wish whole family"]))]
        (when i-am-wishing
           [:div.wish-box
            [:div.demo
             [:h5 "Demo"]
             [:div.price "Free"]]
            [:div.basic
             [:h5 "Basic"]
             [:div.price "From $130"]
             [:div.description
              [:h6 "Standart font encoding"]
              [:p "Uppercase, Lowercase, Ligatures, Currency, Numerals, Fractions, Mathematical, Punctuations"]]]
            [:div.premium
             [:h5 "Premium"]
             [:div.price "From $250"]
             [:div.description
              [:h6 "Extended font encoding"]
              [:p "Uppercase, Lowercase, Smallcaps, Extended Ligatures, Superscript, Subscript, Extend Currency, Extended Numerals, Extended Fractions, Mathematical, Punctuations, Arrows"]]]
            [:div.superior
             [:h5 "Superior"]
             [:div.price "$1920"]
             [:div.description
              [:p "All three typefaces in the Premium package: Hrot, Kunda Book, Vegan Sans"]
              [:p "+ Our next two released typefaces for free."]]]
            [:a.help "What?"]])])))

(defn styles-section []
  (fn []
    (let [current-font (get (:fonts @app-state) (:font-id @app-state))
          styles (get current-font "styles")
          name (get current-font "name")]
      [:section#styles {:style {:font-family (replace name #" " "")}}
         [:ul.styles
          (for [style styles]
            ^{:key style}
            [style-line style])]])))

(defn glyphs-section []
  (let [selected (atom nil)]
    (fn []
      (let [id (:font-id @app-state)
            current-font (get (:fonts @app-state) id)
            charsets (get current-font "charsets")]
        [:section#glyphs
         [:select
          {:defaultValue @selected
           :on-change #(let [value (-> % .-target .-value)]
                         (reset! selected value))}
         (for [image charsets]
           ^{:key image}
           [:option
            {:value image}
            (join ", " (mapv capitalize (split image #"-")))])]
           [:img {:size "1600x1087" :src (str "/img/glyphs/" id "/" (or @selected (first charsets)) ".svg")}]]))))

(defn details-section [current-font]
  (let [id (get current-font "id")
        details (split (get current-font "details") #"\n")
        styles (count (get current-font "styles"))
        glyphs (get current-font "glyphs")]
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
  (let [step (atom 0)
        show-controls (atom false)]
    (fn []
      (let [current-font (get (:fonts @app-state) (:font-id @app-state))
            inuses (get current-font "inuse")
            inuses-count (count inuses)]
        [:section#inuse
         (when @show-controls
          [:nav
           {:on-mouse-enter #(reset! show-controls true)}
           [:a.previous
            {:on-click #(reset! step (dec @step))}
            "Previous"]
           [:a.next
            {:on-click #(reset! step (if (< @step (- inuses-count 2)) (inc @step) 0))}
            "Next"]])
        [:div.figure-wrapper
         {:on-mouse-enter #(reset! show-controls true)
          :on-mouse-leave #(reset! show-controls false)
          :style
          {:width (str inuses-count "00%")
           :transform (str "translateX(-" @step "00vw)")}}
         (doall (for [inuse inuses]
           (let [img (get inuse "img")]
           ^{:key img}
           [:figure
            {:style
             {:width (str (/ 100 inuses-count) "%")}}
            [:img {:src (str "/img/" img)}]
            (when @show-controls
             [:figcaption
              [:ul
               (for [p (split (get inuse "text") #"\n")]
                 ^{:key (hash p)}
                 [:li p])]])])))]]))))

(defn page []
  (when-not (:listening @app-state) (listen!))
  (fn []
    (when (:fonts @app-state)
      (let [id (:font-id @app-state)
            current-font (get (:fonts @app-state) (:font-id @app-state))]
        [:div
         [font-header]
         [styles-section]
         [glyphs-section]
         [details-section current-font]
         [inuse-section]]))))
