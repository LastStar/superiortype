(ns superiortype.core
  (:require-macros [secretary.core :refer [defroute]])
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame :refer [subscribe dispatch]]
            [secretary.core :as secretary]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [superiortype.utils :refer [scroll-body]]
            [superiortype.views :refer [header error]]
            [superiortype.foundry :as foundry]
            [superiortype.font :as font]
            [superiortype.home :as home]
            [superiortype.handlers]
            [superiortype.subs]
            [superiortype.views :as views])
  (:import goog.History))

;; current page implementation
(defmulti current-page #(deref (subscribe [:current-page])))

(defmethod current-page :home []
  (scroll-body)
  [home/page])

(defmethod current-page :foundry []
  (scroll-body)
  [foundry/page])

(defmethod current-page :font []
  [font/page])

(defmethod current-page :error []
  [error])

;; -------------------------
;; History
;; must be called after routes have been defined
(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
     EventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

;; -------------------------
;; Routes
(defn app-routes []
  (secretary/set-config! :prefix "#")

  (defroute "/" []
    (dispatch [:page-changed :home]))

  (defroute "/foundry" []
    (dispatch [:page-changed :foundry]))

  (defroute "/font/:id" [id]
    (do
      (dispatch [:font-changed id])
      (dispatch [:page-changed :font])))

  (defroute "/font/:id/:section" [id section]
    (do
      (dispatch [:section-changed section])
      (dispatch [:font-id id])
      (dispatch [:page-changed :font])))

  (hook-browser-navigation!))

;; -------------------------
;; Initialize app

(defn mount-header []
  (reagent/render [header] (.getElementById js/document "header")))

(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn ^:export main []
  (re-frame/dispatch-sync [:initialize-db])
  (app-routes)
  (mount-header)
  (mount-root)
  )


