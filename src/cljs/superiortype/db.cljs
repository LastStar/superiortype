(ns superiortype.db
  (:require [cljs.reader :refer [read-string]]))

(def fonts
  {
   :hrot
   {:id "hrot"
    :name "Hrot"
    :styles ["Hair" "Hair Italic" "Thin" "Thin Italic" "Light" "Light Italic" "Regular" "Italic" "Medium" "Medium Italic" "Semibold" "Semibold Italic" "Bold" "Bold Italic" "Black" "Black Italic" ]
    :styles-count 16
    :charsets ["uppercase" "lowercase" "ligatures" "numerals-fractions" "mathematical-punctuation" "arrows"]
    :next "kunda-book"
    :previous "vegan-sans"
    :glyphs "850"
    :details "The font type Hrot was inspired by the visual typographic aesthetic of the advertisements and signs in the seventies of the twentieth century namely in the German or Italian environment. The design of the font is character istically and pleasingly wide. Thanks to sharp cropped lines the characters appear almost as sharpened knives especially in bold faces. This design principle is more of an unconventional impression than a mere cold structure. The letter s are drawn from the ultra thin face Hair to the extremely thick bold face Black. The family encompasses a total of sixteen faces. Thanks to this Hrot is very well suited for setting striking captions and non-conformist web pages."
    :inuse [{:text " XYZ project\nDesign: Marek Šilpoch\n2015"
             :img "hrot-xyz.jpg"}]}
   :kunda-book
   {:id "kunda-book"
    :name "Kunda Book"
    :styles ["Regular" "Italic" "Medium" "Medium Italic" "Semibold" "Semibold Italic" "Bold" "Bold Italic"]
    :styles-count 8
    :charsets ["uppercase" "lowercase" "smallcaps" "ligatures" "superscript-subscript" "currency" "numerals-fractions" "mathematical-punctuation" "arrows"]
    :next "vegan-sans"
    :previous "hrot"
    :inuse [{:text "Spoušť\nDesign: Martin Pecina\n2015 "
             :img "kunda-book-spoust.jpg"}
            {:text "Živel #38\nDesign: Martin Groch Anežka Rozehnalová\n2013"
             :img "kunda-book-zivel.jpg"}
            {:text "Architektúra pohyblivého obrazu\nDesign: Komando\n2014"
             :img "kunda-book-architektura.jpg"}
            {:text "Bestsellers\nDesign: Richard Jaroš\n2014"
             :img "kunda-book-bestsellers.jpg"}]
    :glyphs 850
    :details "Kunda was originally designed for the text of the 38th edition of the Czech overground magazine Živel. The objective was to reinvent the aesthetic and structural principles of the humanistic antiqua into a fresh visual language. The type character is constituted by a lower medium height and quite notable shading of individual characters. The actual design drawing is symbiotic with calligraphic ornamentation and sharp contours. Itali cs, as a distinctive type face, are made much thinner and far more drawn out. Kunda Book was awarded at the European Design Awards in 2015."}
   :vegan-sans
   {:id "vegan-sans"
    :name "Vegan Sans"
    :styles ["Light" "Light Italic" "Regular" "Italic" "Medium" "Medium Italic" "Semibold" "Semibold Italic" "Bold" "Bold Italic" "Black" "Black Italic"]
    :styles-count 12
    :charsets ["uppercase" "lowercase" "smallcaps" "ligatures" "superscript-subscript" "currency" "numerals-fractions" "mathematical-punctuation" "arrows"]
    :next "hrot"
    :previous "kunda-book"
    :inuse [{:text "World without letters\nDesign: Renata Hovorková\n 2015"
             :img "vegan-sans-world.jpg"}
            {:text "Pálenka\nDesign: Martin Pecina\n2014"
             :img "vegan-sans-palenka.jpg"}
            {:text "Stříbrné okouzlení\nDesign: Martin Pecina\n2014"
             :img "vegan-sans-okouzleni.jpg"}
            {:text "Studio of Ceramics and Porcelain\nDesign: Komando\n2014"
             :img "vegan-sans-keramika.jpg"}]
    :glyphs 848
    :details "The inspiration source for the Vegan was the Vega type drawn by the Czech typographer and font designer Stanislav Maršo. The original design used highl y shaded character design with a lightly extended duct.\nAs the type needs to be used both for displays and texts, some radical changes in the drawing design and structure were necessary; I achieved this by optical thinning and darker sha ding of individual characters.\nThe result is a type that saves space when longer texts are being set and, thanks to the shading, is perfectly legible even in smaller sizes and, in caption sizes, unmistakable in appearance."}})

(def customs
  {:negramot
    {:id "negramot"
     :name "Negramot"
     :release "2014"
     :description ""}
   :signer
    {:id "signer"
     :name "Signer"
     :release "2012-2013"
     :description "Type family with six weights for Czech Design.cz"}
   :kendo
    {:id "kendo"
     :name "Kendo"
     :release "2014"
     :description ""}
   :tungsten
    {:id "tungsten"
     :name "TUNGSTEN"
     :release "2014"
     :description ""}})

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

(def default-db
  {:font-id nil
   :current-page :home
   :header-class "normal"
   :menu-visible false
   :current-section "styles"
   :down-visible true
   :listening {}
   :wishing false
   :edited []
   :counter 0
   :size {}
   :visible-styles {}
   :selected-charset nil
   :step 0
   :show-controlls false
   :address-class "hidden"
   :charset-visibility "hidden"
   :wish-list {}
   :showing-wish-list false
   :checkout-started false
   :eula-checked false
   :selected-country "---"
   :order {}
   :opened-sections {}
   :customs customs
   :selected-custom nil})
