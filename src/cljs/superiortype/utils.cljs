(ns superiortype.utils
  (:require-macros [cljs.core.async.macros :refer [go-loop]])
  (:require
   [cljs.core.async :refer [timeout]]))

(def modular 1.3333)

(defn element [el]
  (.getElementById js/document el))

(defn header-bottom []
  (if-let [header (element "font")]
    (-> header .getBoundingClientRect .-bottom) 0))

(defn get-top [element]
  (if element
    (- (.-offsetTop element) (header-bottom))
    0))

(defn get-bottom [element]
  (if element
    (- (+ (.-offsetTop element) (.-offsetHeight element))
       (* (header-bottom) 1.1))
    0))

;; Scrolling
(defn scroll-to [top]
  (.scrollTo js/window 0 top))

(defn- section-range [sec]
  (let [element (element sec)]
    [sec (get-top element) (get-bottom element)]))

(defn no-scroll-body []
  (-> js/document (.querySelector "body") .-style  (aset  "overflow" "hidden")))

(defn scroll-body []
  (-> js/document (.querySelector "body") .-style (aset "overflow" "scroll")))

(defn scroll-top []
  (scroll-to 0))

(defn- easing
  "Modeled after the piecewise quadratic
   y = (1/2)((2x)^2)               [0, 0.5)
   y = -(1/2)((2x-1)*(2x-3) - 1)   [0.5, 1]"
  [start end point]
  (let [p (/ (- point start) (- end start))]
    (if (< p 0.5)
      (* 2 p p)
      (+ (* 4 p) (* -2 p p) -1))))

(defn smooth-scroll [element]
  (let [start-top (.-scrollY js/window)
        init-distance (- (get-top element) start-top)
        duration (/ (.abs js/Math init-distance) (if (pos? init-distance) 3 9))
        start-time (.now js/Date)
        end-time (+ start-time duration)
        step-function (partial easing start-time end-time)]
    (go-loop []
      (let [now (.now js/Date)
            point (step-function now)
            top (inc (.round js/Math (get-top element)))
            distance (- top start-top)
            frame-top (.round js/Math (+ start-top (* distance point)))]
        (<! (timeout 1))
        (when (pos? frame-top) (scroll-to frame-top))
        (when (> end-time now) (recur))))))
