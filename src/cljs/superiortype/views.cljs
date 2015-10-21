(ns superiortype.views
  (:require [re-frame.core :as re-frame :refer [subscribe dispatch]]))

;; -------------------------
;; Pages
(defn header []
  (fn []
    (let [menu-visible (subscribe [:menu-visible])]
      [:div
       {:on-mouse-enter #(dispatch [:menu-visibility-changed true])
        :on-mouse-leave #(dispatch [:menu-visibility-changed false])
        :class "menu"}
       [:h1 [:a
             {:href "#/"}
              "Superior Type"]]
       [:nav
        {:class (when @menu-visible "visible")}
        [:a {:href "#/custom"} "Custom"]
        [:a {:href "#/foundry"} "Foundry"]
        [:a {:href "#/first-aid"} "First Aid"]]])))

(defn error []
  [:div.error
   [:h2 "Something went horibly wrong"]])


