(ns superiortype.events
  (:require [cljs.core.async :refer [<! put! chan]]
            [goog.events :as events]
            [goog.events.EventType :as EventType]
            [goog.dom :as dom]))

(defn- get-scroll []
  (-> (dom/getDocumentScroll) (.-y)))

(defn- events->chan [el event-type c]
  (events/listen el event-type #(put! c %))
  c)

(defn scroll-chan-events []
  (events->chan js/window EventType/SCROLL (chan 1 (map get-scroll))))

