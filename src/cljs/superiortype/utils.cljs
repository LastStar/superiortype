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
    (do
    (- (.-offsetTop element) (header-bottom)))
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

(defn- smooth-step [start end point]
  (let [x (/ (- point start) (- end start))]
    (* (* x x) (- 3 (* 2 x)))))

(defn smooth-scroll [target duration]
  (let [start-time (.now js/Date)
        end-time (+ start-time duration)
        start-top (.-scrollY js/window)
        distance (- target start-top)
        step-function (partial smooth-step start-time end-time)]
    (.log js/console distance duration)
    (go-loop []
      (let [now (.now js/Date)
            point (step-function now)
            frame-top (.round js/Math (+ start-top (* distance point)))]
        (<! (timeout 1))
        (when (pos? frame-top) (scroll-to frame-top))
        (when (> end-time now)
          (recur))))))
