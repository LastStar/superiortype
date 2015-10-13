(ns superiortype.handlers
    (:require [re-frame.core :as re-frame :refer [register-handler dispatch
                                                  subscribe path trim-v after]]
              [superiortype.utils :refer [no-scroll-body scroll-body element
                                          get-top scroll-top scroll-to smooth-scroll]]
              [superiortype.font :as font]
              [superiortype.db :as db :refer [ls->wish-list wish-list->ls!]]))

(register-handler
 :initialize-db
 (fn  [_ _]
   (merge db/default-db (ls->wish-list))))

(register-handler
 :page-changed
  (path :current-page)
  (fn [current-page [_ page]]
    (when-not (= current-page page) (scroll-top))
    page))

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
 :section-changed
  (fn [app-state [_ new-section]]
    (if (= new-section "glyphs")
      (dispatch [:charset-visible])
      (dispatch [:charset-hidden]))
    (smooth-scroll (element new-section))
    (assoc app-state :current-section new-section)))

(register-handler
 :section-scrolled
  (fn [app-state [_ new-section]]
    (if (= new-section "glyphs")
      (dispatch [:charset-visible])
      (dispatch [:charset-hidden]))
    (assoc app-state :current-section new-section)))

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
 :add-edited
  (fn [app-state [_ new-edited]]
    (assoc app-state :edited (conj (:edited app-state) new-edited))))

(register-handler
 :remove-edited
  (fn [app-state [_ edited]]
    (assoc app-state :edited (remove #(= edited %) (:edited app-state)))))


(register-handler
 :wishing-started
  (path :wishing)
  (fn [wishing [_ new-wishing element-top]]
    (scroll-to element-top)
    (no-scroll-body)
    new-wishing))

(register-handler
 :wishing-canceled
  (fn [app-state [_]]
    (scroll-body)
    (assoc app-state :wishing nil)))

(register-handler
 :charset-visible
  (fn [app-state [_]]
    (assoc app-state :charset-position "visible")))

(register-handler
 :charset-hidden
  (fn [app-state [_]]
    (assoc app-state :charset-position "hidden")))

(register-handler
 :charset-selected
  (fn [app-state [_ new-value]]
    (scroll-to (get-top (element "glyphs")))
    (font/invalidate-sections-ranges)
    (assoc app-state :selected-charset new-value)))

(register-handler
 :step-changed
  (fn [app-state [_ new-value max]]
    (let [new-step (if (>= new-value max) 0 (if (not (pos? new-value)) (- max 1) new-value))]
      (assoc app-state :step new-step))))

(register-handler
 :show-controlls-changed
  (fn [app-state [_ new-value]]
    (assoc app-state :show-controlls new-value)))

(register-handler
 :address-class-changed
  (fn [app-state [_ new-value]]
    (assoc app-state :address-class new-value)))

(register-handler
 :custom-selected
  (fn [app-state [_ custom]]
    (assoc app-state :selected-custom custom)))

(register-handler
 :custom-deselected
  (fn [app-state [_]]
    (assoc app-state :selected-custom nil)))

(register-handler
 :show-wish-list
  (path :showing-wish-list)
  (fn [_]
    (no-scroll-body)
    true))

(register-handler
 :hide-wish-list
  (path :showing-wish-list)
  (fn [_]
    (scroll-body)
    (dispatch [:checkout-canceled])
    false))

(def ->ls (after wish-list->ls!))    ;; middleware to store wish-list into local storage

;; middleware for any handler that manipulates wish-list
(def wish-list-middleware [;check-schema-mw ;; after ever event handler make sure the schema is still valid
                      (path :wish-list)   ;; 1st param to handler will be value from this path
                      ->ls            ;; write to localstore each time
                      trim-v])        ;; remove event id from event vec

(register-handler
 :add-to-wish-list
  wish-list-middleware
  (fn [wish-list [id package]]
    (dispatch [:wishing-canceled])
    (assoc wish-list id {:package package
                         :license :print
                         :users :ones})))

(register-handler
 :change-package-in-wish-list
  wish-list-middleware
  (fn [wish-list [id package]]
    (assoc wish-list id {:package package})))

(register-handler
 :change-license-in-wish-list
  wish-list-middleware
  (fn [wish-list [id license]]
    (assoc wish-list id {:license license})))

(register-handler
 :change-users-in-wish-list
  wish-list-middleware
  (fn [wish-list [id users]]
    (assoc wish-list id {:users users})))

(register-handler
 :remove-all-from-wishlist
  wish-list-middleware
  (fn [_]
    (dispatch [:hide-wish-list])
    {}))

(register-handler
 :remove-from-wishlist
  wish-list-middleware
  (fn [wish-list [item]]
    (if (= (count wish-list) 1)
      (dispatch [:remove-all-from-wishlist])
      (dissoc wish-list item))))

(register-handler
 :checkout-started
  (path :checkout-started)
  (fn [_] true))

(register-handler
 :checkout-canceled
  (path :checkout-started)
  (fn [_] false))

(register-handler
 :toggle-eula
 (path :eula-checked)
  (fn [checked] (not checked)))

