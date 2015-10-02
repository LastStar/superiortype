(ns superiortype.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame :refer [register-sub]]))

(register-sub
 :current-page
 (fn [db]
   (reaction (:current-page @db))))

(register-sub
 :menu-visible
 (fn [db]
   (reaction (:menu-visible @db))))

(register-sub
 :fonts
  (fn [db]
    (reaction (:fonts @db))))

(register-sub
 :counter
  (fn [db]
    (reaction (:counter @db))))

(register-sub
 :size-query
  (fn [db [_ id default]]
    (reaction (get-in @db [:size (keyword id)] default))))

(register-sub
 :visible-styles-query
  (fn [db [_ id]]
    (reaction (get-in @db [:visible-styles id]))))

(register-sub
 :current-font
  (fn [db [_]]
    (reaction (get-in @db [:fonts (keyword (:font-id @db))]))))

(register-sub
 :listening
  (fn [db [_ page]]
    (reaction (get-in @db [:listening page]))))

(register-sub
 :wishing
  (fn [db [_]]
    (reaction (@db :wishing))))

(register-sub
 :current-section
  (fn [db [_]]
    (reaction (@db :current-section))))

(register-sub
 :header-class
  (fn [db [_]]
    (reaction (@db :header-class))))

(register-sub
 :edited
  (fn [db [_]]
    (reaction (@db :edited))))

(register-sub
 :selected-charset
  (fn [db [_]]
    (reaction (@db :selected-charset))))

(register-sub
 :step
  (fn [db [_]]
    (reaction (@db :step))))

(register-sub
 :show-controlls
  (fn [db [_]]
    (reaction (@db :show-controlls))))

(register-sub
 :all-edited
  (fn [db [_]]
    (reaction (@db :edited))))

(register-sub
 :address-class
  (fn [db [_]]
    (reaction (@db :address-class))))
