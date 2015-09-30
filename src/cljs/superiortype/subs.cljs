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
    (reaction (get-in @db [:size id] default))))

(register-sub
 :visible-styles-query
  (fn [db [_ id]]
    (reaction (get-in @db [:visible-styles id] false))))

