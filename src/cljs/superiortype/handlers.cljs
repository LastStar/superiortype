(ns superiortype.handlers
    (:require [re-frame.core :as re-frame :refer [register-handler dispatch subscribe]]
              [superiortype.utils :refer [no-scroll-body scroll-body scroll-top
                                          scroll-to element get-top]]
              [superiortype.font :as font]
              [superiortype.db :as db]))

(register-handler
 :initialize-db
 (fn  [_ _]
   db/default-db))

(register-handler
 :page-changed
  (fn [app-state [_ page]]
    (when-not (= (deref (subscribe [:current-page])) page)
      (scroll-top))
    (assoc app-state :current-page page)))

(register-handler
 :menu-visibility-changed
  (fn [app-state [_ visibility]]
    (assoc app-state :menu-visible visibility)))

(register-handler
 :font-changed
  (fn [app-state [_ new-value]]
    (font/invalidate-sections-ranges)
    (dispatch [:page-changed :font])
    (assoc app-state :font-id new-value)))

(register-handler
 :listening
  (fn [app-state [_ page]]
    (assoc-in app-state [:listening page] true)))

(register-handler
 :counter-changed
  (fn [app-state [_ new-value]]
    (assoc app-state :counter new-value)))

(register-handler
 :size-changed
  (fn [app-state [_ id size]]
    (font/invalidate-sections-ranges)
    (assoc-in app-state [:size (keyword id)] size)))

(register-handler
 :header-class-changed
  (fn [app-state [_ new-value]]
    (font/invalidate-sections-ranges)
    (assoc app-state :header-class new-value)))

(register-handler
 :styles-visibility-changed
  (fn [app-state [_ id visibility]]
    (assoc-in app-state [:visible-styles id] visibility)))

(register-handler
 :section-changed
  (fn [app-state [_ new-section]]
    (if (= new-section "glyphs")
      (dispatch [:charset-fixed])
      (dispatch [:charset-relative]))
    (assoc app-state :current-section new-section)))

(register-handler
 :add-edited
  (fn [app-state [_ new-edited]]
    (assoc app-state :edited (conj (:edited app-state) new-edited))))

(register-handler
 :remove-edited
  (fn [app-state [_ edited]]
    (assoc app-state :edited (remove #(= edited %) (:edited app-state)))))


(register-handler
 :wishing-started
  (fn [app-state [_ wishing]]
    (no-scroll-body)
    (assoc app-state :wishing wishing)))

(register-handler
 :wishing-canceled
  (fn [app-state [_]]
    (scroll-body)
    (assoc app-state :wishing nil)))

(register-handler
 :charset-fixed
  (fn [app-state [_]]
    (assoc app-state :charset-position "fixed")))

(register-handler
 :charset-relative
  (fn [app-state [_]]
    (assoc app-state :charset-position "relative")))

(register-handler
 :charset-selected
  (fn [app-state [_ new-value]]
    (scroll-to (get-top (element "glyphs")))
    (font/invalidate-sections-ranges)
    (assoc app-state :selected-charset new-value)))

(register-handler
 :step-changed
  (fn [app-state [_ new-value max]]
    (let [new-step (if (>= new-value max) 0 (if (pos? new-value) (- max 1) new-value))]
      (assoc app-state :step new-step))))

(register-handler
 :show-controlls-changed
  (fn [app-state [_ new-value]]
    (assoc app-state :show-controlls new-value)))

(register-handler
 :add-to-wishlist
  (fn [app-state [_ wishing-one package]]
    (scroll-body)
    (dispatch [:wishing-canceled])
    (assoc app-state :wishlist (conj (:wishlist app-state) [wishing-one package]))))

(register-handler
 :address-class-changed
  (fn [app-state [_ new-value]]
    (assoc app-state :address-class new-value)))
