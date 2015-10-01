(ns superiortype.foundry
  (:require-macros [cljs.core.async.macros :refer [go-loop]])
  (:require
    [superiortype.events :refer [scroll-chan-events]]
    [re-frame.core :refer [dispatch subscribe]]
    [superiortype.utils :refer [element get-bottom get-top]]))

(defn listen! []
  (let [chan (scroll-chan-events)]
    (dispatch [:listening :foundry])
    (go-loop []
       (let [new-y (<! chan)
             address-class (subscribe [:address-class])
             address-bottom (get-bottom (element "address"))
             address-top (get-top (element "address"))]
         (when (< new-y address-top)
           (when-not (= @address-class "visible")
             (dispatch [:address-class-changed "visible"])))
         (when (> new-y address-bottom)
           (when not (= @address-class "hidden")
             (dispatch [:address-class-changed "hidden"]))))
         (recur))))

(defn page []
  (when-not (deref (subscribe [:listening :foundry])) (listen!))
  (let [address-class (subscribe [:address-class])]
  [:section#foundry
   [:header
    [:h2 "Superior Type is an independent Type Foundry based in Prague, Czech Republic"]]
   [:div.about-address
    [:div.text
     [:p
      "The Superior Type foundry is a product of sheer love for designing new
       lettering. The main objective is to create original authorial,
       innovative types, designated for professional use thanks to their
       precise elaboration and pure functionality.  Another part of our letter
       line creation process is custom-made types, which can be used to create
       a unique visual identity."]
     [:p
      "Type foundry was founded by Vojtěch Říha in 2014 in Prague. The
       Superior Type production is high-end; we cooperate with Josef
       Pospíšil on the design of web pages."
      ]
     ]
    [:div.contact
     [:address#address {:class @address-class}
      [:div.street "Myslíkova 9"]
      [:div.city "Prague, Czech Republic"]
      [:div.phone "+420 724 158 383"]
      [:div.email
       [:a {:href "mailto:mail@superiortype.com"} "mail@superiortype.com"]]
      [:nav.social
       [:a.facebook {:href "https://www.facebook.com/superiortype", :target "_blank"} "FaceBook"]
       [:a.twitter {:href "https://twitter.com/SuperiorType", :target "_blank"} "Twitter"]
       [:a.instagram {:href "https://instagram.com/superior_type/", :target "_blank"} "Instagram"]]]]]
   [:div {:class "lost"}
    [:p "A great part of our Superior types became a part of the Briefcase Type Foundry library: "]
    [:div {:class "fonts"}
     [:span {:class "dres"}
      [:a {:href "//www.briefcasetype.com/bc-dres"} "Dres"]]
     [:span {:class "kakao"}
      [:a {:href "//www.briefcasetype.com/bc-kakao"} "Kakao"]]
     [:span {:class "motel-sans"}
      [:a {:href "//www.briefcasetype.com/bc-motel-sans"} "Motel Sans"]]
     [:span {:class "motel-slab"}
      [:a {:href "//www.briefcasetype.com/bc-motel-slab"} "Motel Slab"]]
     [:span {:class "pramen-sans"}
      [:a {:href "//www.briefcasetype.com/bc-pramen-sans"} "Pramen Sans"]]
     [:span {:class "pramen-slab"}
      [:a {:href "//www.briefcasetype.com/bc-pramen-slab"} "Pramen Slab"]]
     [:span {:class "steiner"}
      [:a {:href "//www.briefcasetype.com/bc-steiner"} "Steiner"]]]]
    [:div.people
     [:div.person
      [:div.photo
       [:img {:src "/img/vojta.jpg"}]]
      [:p
       [:strong "Vojtěch Říha (1989)"] " graduated from Type studio at Academy of Arts Architecture and Design in Prague (2008-2015). In the year 2011 he undertook a student exchange at the Royal Academy of Art, The Hague in Holland. In the same year he was awarded the European Design Award in original typeface category for his Type family Pramen. Since the year 2013 his typefaces are a part of the Briefcase Type Foundry typeface library. In the year 2014 he established his own type foundry by the name Superior Type. In the year 2015 he was awarded the European Design Award for the Kunda Book original typeface."]]
     [:div.person
      [:div.photo
       [:img {:src "/img/pepa.jpg"}]
       [:p
        [:strong "Josef Pospíšil (1976)"] " got his first computer, when he was 10 years old from Tuzex. After graduating from Czech University of Life Science he started his first IT company. And then after 5 years another one. He is specializing in the typography related project for more than 4 years and already built web for two and half foundries. He is the first Rubyist in the Czech Republic, recently turned Clojurian, father of three and the party animal."]]]]
    [:div.studio
     [:img {:src "/img/studio.jpg"}]]]))


