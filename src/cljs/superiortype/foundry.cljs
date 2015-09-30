(ns superiortype.foundry)

(defn page []
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
     [:address
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
         [:a {:href "//www.briefcasetype.com/bc-steiner"} "Steiner"]]]]])


