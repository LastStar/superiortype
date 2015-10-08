(ns superiortype.home
  (:require
    [superiortype.utils :refer [element get-top scroll-to modular]]
    [re-frame.core :refer [dispatch subscribe]]
    [clojure.string :refer [replace lower-case]]))

;; Components
(defn font-line [current-font]
  (let [name (:name current-font)
        id (:id current-font)
        styles (:styles current-font)]
    (fn []
      (let [size (subscribe [:size-query id 123])
            visible-styles (deref (subscribe [:visible-styles-query id]))]
      [:li
       {:style
        {:font-family (replace name #" " "")}}
       [:div.tools
        [:button.smaller {:on-click #(dispatch [:size-changed id (/ @size modular)])} "smaller"]
        [:button.list {:on-click #(dispatch [:styles-visibility-changed id (not visible-styles)])}
         "Styles"]
        [:button.bigger {:on-click #(dispatch [:size-changed id (* @size modular)])} "BIGGER"]]
       [:a {:style {:font-size @size} :href (str "#/font/" id)} name]
       (if visible-styles
         [:div {:style {:font-family (replace name #" " "")}}
           [:a {:href (str "#/font/" id)}
            [:ul.styles
             (for [style styles]
               ^{:key style}
               [:li
                 [:div
                  {:style {:font-size (str @size "px")} :class (lower-case style)}
                  style]])]]])]))))

(defn show-case-svg []
  (let [content [10 3 8 7 6 5 4 9 2 1 "a" "b" "C" "D" "E" "ff" "gg" "HhH"
                 "IiiiIiiiii" "jJjJjJ" "kKKKk" "llLlll" "Mmmm" "NnNnN"
                 "ooOoo" "Pp" "QqQQ" "rRrrRrR" "SssSsS" "TtTtT"
                 "UUUu" "VvVvV" "WWW" "XxXxX" "yyyyYYYY"
                 "ZZZzzzzzz"]]
    (fn []
      (let [counter (subscribe [:counter])
            font-size (str (/ 96 (/ @counter 3)) "px")
            current-content (get content @counter "END")]
        (when (> @counter 42) (dispatch [:counter-changed 0]))
        [:svg
         [:g.top
          {:on-mouse-enter #(dispatch [:counter-changed (inc @counter)])}
          [:circle
           {:cx 0 :cy 0 :r (+ 60 (* @counter 3))}]
          [:text {:x -30 :y 30
                  :style {:font-size font-size}}
           current-content]]
         [:g.bottom-left
          {:on-click #(dispatch [:counter-changed (inc @counter)])}
          [:circle
           {:cx 0 :cy 0 :r 37}]
          [:text {:x -24 :y 24
                  :style {:font-size font-size}}
           current-content]]
         [:g.bottom-right
          {:on-mouse-move #(dispatch [:counter-changed (inc @counter)])}
          [:circle
           {:cx 0 :cy 0 :r 60}]
          [:text {:x -20 :y 20
                  :style {:font-size font-size}}
           current-content]]
         [:circle.down
          {:cx 10 :cy 10 :r 10
           :on-click #(scroll-to (get-top (element "fonts")))}] ]))))

(defn page []
  (let [showing-wist-list (subscribe [:showing-wish-list])
        fonts (subscribe [:fonts])]
    [:div {:class (and @showing-wist-list "fade")}
     [show-case-svg]
     [:ul#fonts
       (for [current-font (vals @fonts)]
         ^{:key (:id current-font)}
         [font-line current-font])]]))
