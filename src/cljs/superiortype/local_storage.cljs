(ns superiortype.local-storage)

;; Keys in local storage
(def lsk-wl "superior-wish-list")
(def lsk-or "superior-order")

(defn ls->wish-list
  "Read in wish list from LS, and process into a map we can merge into app-db."
  []
  (some->> (.getItem js/localStorage lsk-wl)
           (cljs.reader/read-string)
           (hash-map :wish-list)))

(defn wish-list->ls!
  "Puts wish list into localStorage"
  [wish-list]
  (.setItem js/localStorage lsk-wl (str wish-list)))

(defn ls->order
  "Read in order from LS, and process into a map we can merge into app-db."
  []
  (some->> (.getItem js/localStorage lsk-or)
           (cljs.reader/read-string)
           (hash-map :order)))

(defn order->ls!
  "Puts order into localStorage"
  [order]
  (.setItem js/localStorage lsk-or (str order)))

