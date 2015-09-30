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
    (assoc-in app-state [:size id] size)))

(register-handler
 :menu-visibility-changed
  (fn [app-state [_ visibility]]
    (assoc app-state :menu-visible visibility)))

(register-handler
 :counter-changed
  (fn [app-state [_ new-value]]
    (assoc app-state :counter new-value)))



