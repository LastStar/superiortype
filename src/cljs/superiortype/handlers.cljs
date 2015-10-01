(ns superiortype.handlers
    (:require [re-frame.core :as re-frame :refer [register-handler]]
              [superiortype.db :as db]))

(register-handler
 :initialize-db
 (fn  [_ _]
   db/default-db))

(register-handler
 :page-changed
  (fn [app-state [_ page]]
    (assoc app-state :current-page page)))

(register-handler
 :size-changed
  (fn [app-state [_ id size]]
    (assoc-in app-state [:size (keyword id)] size)))

(register-handler
 :menu-visibility-changed
  (fn [app-state [_ visibility]]
    (assoc app-state :menu-visible visibility)))

(register-handler
 :counter-changed
  (fn [app-state [_ new-value]]
    (assoc app-state :counter new-value)))

(register-handler
 :font-changed
  (fn [app-state [_ new-value]]
    (assoc app-state :font-id new-value)))

(register-handler
 :wishing-changed
  (fn [app-state [_ new-value]]
    (assoc app-state :wishing new-value)))

(register-handler
 :section-changed
  (fn [app-state [_ new-value]]
    (assoc app-state :current-section new-value)))

(register-handler
 :header-class-changed
  (fn [app-state [_ new-value]]
    (assoc app-state :header-class new-value)))

(register-handler
 :address-class-changed
  (fn [app-state [_ new-value]]
    (assoc app-state :address-class new-value)))

(register-handler
 :listening
  (fn [app-state [_ page]]
    (assoc-in app-state [:listening page] true)))

(register-handler
 :charset-selected
  (fn [app-state [_ new-value]]
    (assoc app-state :selected-charset new-value)))

(register-handler
 :step-changed
  (fn [app-state [_ new-value max]]
    (let [new-step (if (>= new-value max) 0 (if (< new-value 0) (- max 1) new-value))]
      (assoc app-state :step new-step))))

(register-handler
 :show-controlls-changed
  (fn [app-state [_ new-value]]
    (assoc app-state :show-controlls new-value)))

(register-handler
 :add-edited
  (fn [app-state [_ new-edited]]
    (assoc app-state :edited (conj (:edited app-state) new-edited))))

(register-handler
 :remove-edited
  (fn [app-state [_ edited]]
    (.log js/console edited)
    (assoc app-state :edited (remove #(= edited %) (:edited app-state)))))
