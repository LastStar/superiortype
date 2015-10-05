(ns superiortype.views
    (:require [re-frame.core :as re-frame :refer [subscribe dispatch]]))

;; -------------------------
;; Pages
(defn header []
  (fn []
    (let [menu-visible (subscribe [:menu-visible])
          wishlist-count (subscribe [:wishlist-count])]
      [:div.wrap
       [:div
        {:on-mouse-enter #(dispatch [:menu-visibility-changed true])
         :on-mouse-leave #(dispatch [:menu-visibility-changed false])
         :class (str "menu" (when @menu-visible " opaque"))}
        [:h1 [:a
              {:href "#/"}
               "Superior Type"]]
        [:nav
         {:class (when @menu-visible "visible")}
         [:a {:href "#/custom"} "Custom"]
         [:a {:href "#/foundry"} "Foundry"]]]
       (when-not (empty? @wishlist-count)
        [:div#wishlist
         [:button (str "You have " (count @wishlist-count) " wish" (when (> (count @wishlist-count) 1) "es"))]])])))

(defn error []
  [:div.error
   [:h2 "Something went horibly wrong"]])


