(ns superiortype.custom
  (:require [re-frame.core :refer [dispatch subscribe]]))

(defn page []
  (set! (-> js/document .-body .-className) "custom")
  (fn []
    (let [customs (vals (deref (subscribe [:customs])))
          selected-custom (deref (subscribe [:selected-custom]))]
     [:section#custom
      [:header
       [:h2 "Custom fonts are best way to create unique and highly recognizable visual identity of a brand"]]
      [:div.text
        [:p "Creating typefaces on custom order is a discipline on its own. Typefaces are the most significant visual and information means. The combination of a quality font and visual identity can distinguish you from competitors in a smart, however distinctive manner, giving you a clear advantage."]
        [:p
         [:span.email
          [:a {:href "mailto:mail@superiortype.com"} "mail@superiortype.com"]]]]
      [:ul.advantages
        [:li.red "Specifically adapting one of our existing types. This could mean changing individual characters, font width, encoding scope and many other elements that will become characteristic for your brand. We will be happy to design elements that will suit your intentions the best."]
        [:li.yellow "Developing a whole new type according to your individual needs."]]
      [:div.cases
       (for [custom customs]
         (let [id (custom :id)]
           (if (= selected-custom id)
            ^{:key id}
            [:div {:id id
                   :on-click #(dispatch [:custom-deselected])}
             [:div.details
               [:h3 {:class id} (custom :name)]
               [:div.release
                [:h4 "Year"]
                [:div (custom :release)]]
               [:div.description (custom :description)]]
             [:img {:src "http://placehold.it/1000x400"}]]
            ^{:key id}
            [:div {:id id
                   :class (when selected-custom " fade")
                   :on-click #(dispatch [:custom-selected id])}
             [:div.details
              [:h3 {:class id} (custom :name)]]])))]])))


