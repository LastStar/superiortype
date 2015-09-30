(ns superiortype.utils)

(def modular 1.3333)

(defn element [el]
  (.getElementById js/document el))

(defn header-bottom []
  (if-let [header (element "font")]
     (.-bottom (.getBoundingClientRect header)) 0))

(defn get-top [element]
  (if element
    (- (.-offsetTop element) (header-bottom))
    0))

(defn get-bottom [element]
  (if element
    (- (+ (.-offsetTop element) (.-offsetHeight element))
       (+ (header-bottom) 10))
    0))

(defn scroll-to [top]
   (.scrollTo js/window 0 top))

(defn section-range [sec]
  (let [element (element sec)]
    [sec (get-top element) (get-bottom element)]))

(defn no-scroll-body []
  (-> js/document (.querySelector "body") .-style  (aset  "overflow" "hidden")))

(defn scroll-body []
  (-> js/document (.querySelector "body") .-style (aset "overflow" "scroll")))
