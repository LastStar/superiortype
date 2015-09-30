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
      (let [size (subscribe [:size-query id 128])
            visible-styles (subscribe [:visible-styles-query id])]
      [:li
       {:style
        {:font-family (replace name #" " "")}}
       [:div.tools
        [:button.minus {:on-click #(dispatch [:size-changed id (/ @size modular)])} "smaller"]
        [:button.list {:on-click #(dispatch [:styles-visibility-changed id (not @visible-styles)])}
         "Styles"]
        [:button.plus {:on-click #(dispatch [:size-changed id (* @size modular)])} "BIGGER"]]
       [:a {:style {:font-size @size} :href (str "#/font/" id)} name]
       (if @visible-styles
         [:div {:style {:font-family (replace name #" " "")}}
           [:ul.styles
            (for [style styles]
              ^{:key style}
              [:li
                [:div
                 {:style {:font-size (str @size "px")} :class (lower-case style)}
                 style]])]])]))))

(defn show-case-svg []
  (let [content ["a" "b" "C" "D" "E" "ff" "gg" "HhH" "IiiiIiiiii" "jJjJjJ"
                  "kKKKk" "llllL lll" "Mmmm" "nnNnNN" "OooOoo" "Pp" "QQqQQ"
                  "rrrRrrRrrrR" "SssssssS" "TTtTtTTTT" "UuUUUuUUU" "VVvVVvVV"
                  "WwwWwwW" "XXX" "yyyyYYYY" "Zzzzzzzzzzzzzz"]]
    (fn []
      (let [counter (subscribe [:counter])
            font-size (str (/ 144 (* @counter)) "px")
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
  (let [fonts (subscribe [:fonts])]
    [:div
     [show-case-svg]
     [:ul#fonts
       (for [current-font (vals @fonts)]
         ^{:key (:id current-font)}
         [font-line current-font])]]))
