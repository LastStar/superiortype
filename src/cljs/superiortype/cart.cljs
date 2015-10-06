(ns superiortype.cart
    (:require
      [clojure.string :refer [join split capitalize]]
      [re-frame.core :as re-frame :refer [subscribe dispatch]]))

(def package-options
  {:superior "Superior"
   :premium "Premium"
   :basic "Basic"
   :demo "Demo"})

(def license-options
  {:print "Print"
   :web "Web"
   :print-web "Print + Web"
   :apps "Applications"})

(def users-options
  {:ones "1-5"
   :fives "5-20"
   :tens "21-100"
   :hundreds "101-1000"
   :thousands "1001-∞"})

(defn page []
  (fn []
    (let [wish-list (deref (subscribe [:wish-list]))
          wish-list-count (count wish-list)
          showing-wish-list (subscribe [:showing-wish-list])]
       (when (pos? wish-list-count)
        (if (not @showing-wish-list)
          [:button.wish-list-button
           {:on-click #(dispatch [:show-wish-list])}
           (str "You have " wish-list-count " wish" (when (> wish-list-count 1) "es"))]
          [:div.wish-list
           [:button.back-button
            {:on-click #(dispatch [:hide-wish-list])} "Back to wishing"]
           [:h2 "Your Wish List"]
           [:table.items
            [:thead
             [:tr
              [:th "Package"]
              [:th "License"]
              [:th "Users"]
              [:th ""]
              [:th.price "Price"]
              [:th.remove
               [:button
                {:on-click #(dispatch [:remove-all-from-wishlist])}
                "Remove all"]]]]
            [:tbody
             (for [item (keys wish-list)]
               (let [name (join  " " (map capitalize (split item #"-")))
                     package (get-in wish-list [item :package])]
                 ^{:key item}
                 [:tr
                  [:td.package
                   [:select
                    {:defaultValue package
                     :on-change #(dispatch [:change-package-in-wish-list item (keyword (-> % .-target .-value))])}
                     (for [package (keys package-options)]
                       ^{:key package}
                        [:option {:value package} (package-options package)])]]
                  [:td.license
                   [:select
                    {:defaultValue (or (get-in wish-list [item :license]) :print)
                     :on-change #(dispatch [:change-license-in-wish-list item (keyword (-> % .-target .-value))])}
                     (for [license (keys license-options)]
                       ^{:key license}
                        [:option {:value license} (license-options license)])]]
                  [:td.users
                   [:select
                    {:defaultValue (or (get-in wish-list [item :users]) :ones)
                     :on-change #(dispatch [:change-users-in-wish-list item (keyword (-> % .-target .-value))])}
                     (for [users (keys users-options)]
                       ^{:key users}
                        [:option {:value users} (users-options users)])]]
                  [:td.name name]
                  [:td.price "30"]
                  [:td.remove
                   [:button
                    {:on-click #(dispatch [:remove-from-wishlist [item package]])}
                    "Remove"]]]))
             [:tr
              [:td]
              [:td]
              [:td]
              [:th.total "Total"]
              [:th.total (* wish-list-count 30)]
              [:th.checkout
               [:button "Make wish come true"]]]]]])))))


