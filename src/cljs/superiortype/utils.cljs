(ns superiortype.utils)

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
