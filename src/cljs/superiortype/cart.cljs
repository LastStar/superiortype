(ns superiortype.cart
    (:require
      [clojure.string :refer [join split capitalize]]
      [re-frame.core :as re-frame :refer [subscribe dispatch]]))

(def package-options
  {:superior "Superior"
   :premium "Premium"
   :basic "Basic"
   :demo "Demo"})

(def license-options
  {:print "Print"
   :web "Web"
   :print-web "Print + Web"
   :apps "Applications"})

(def users-options
  {:ones "1-5"
   :fives "5-20"
   :tens "21-100"
   :hundreds "101-1000"
   :thousands "1001-∞"})

(def countries
 [[:CZ "Czech Republic"]
  [:US "United States"]
  [:GB "United Kingdom"]
  [:DE "Germany"]
  [:AF "Afghanistan"]
  [:AX "Åland Islands"]
  [:AL "Albania"]
  [:DZ "Algeria"]
  [:AS "American Samoa"]
  [:AD "Andorra"]
  [:AO "Angola"]
  [:AI "Anguilla"]
  [:AQ "Antarctica"]
  [:AG "Antigua and Barbuda"]
  [:AR "Argentina"]
  [:AM "Armenia"]
  [:AW "Aruba"]
  [:AU "Australia"]
  [:AT "Austria"]
  [:AZ "Azerbaijan"]
  [:BS "Bahamas"]
  [:BH "Bahrain"]
  [:BD "Bangladesh"]
  [:BB "Barbados"]
  [:BY "Belarus"]
  [:BE "Belgium"]
  [:BZ "Belize"]
  [:BJ "Benin"]
  [:BM "Bermuda"]
  [:BT "Bhutan"]
  [:BO "Bolivia, Plurinational State of"]
  [:BQ "Bonaire, Sint Eustatius and Saba"]
  [:BA "Bosnia and Herzegovina"]
  [:BW "Botswana"]
  [:BV "Bouvet Island"]
  [:BR "Brazil"]
  [:IO "British Indian Ocean Territory"]
  [:BN "Brunei Darussalam"]
  [:BG "Bulgaria"]
  [:BF "Burkina Faso"]
  [:BI "Burundi"]
  [:KH "Cambodia"]
  [:CM "Cameroon"]
  [:CA "Canada"]
  [:CV "Cape Verde"]
  [:KY "Cayman Islands"]
  [:CF "Central African Republic"]
  [:TD "Chad"]
  [:CL "Chile"]
  [:CN "China"]
  [:CX "Christmas Island"]
  [:CC "Cocos (Keeling) Islands"]
  [:CO "Colombia"]
  [:KM "Comoros"]
  [:CG "Congo"]
  [:CD "Congo, the Democratic Republic of the"]
  [:CK "Cook Islands"]
  [:CR "Costa Rica"]
  [:CI "Côte d'Ivoire"]
  [:HR "Croatia"]
  [:CU "Cuba"]
  [:CW "Curaçao"]
  [:CY "Cyprus"]
  [:DK "Denmark"]
  [:DJ "Djibouti"]
  [:DM "Dominica"]
  [:DO "Dominican Republic"]
  [:EC "Ecuador"]
  [:EG "Egypt"]
  [:SV "El Salvador"]
  [:GQ "Equatorial Guinea"]
  [:ER "Eritrea"]
  [:EE "Estonia"]
  [:ET "Ethiopia"]
  [:FK "Falkland Islands (Malvinas)"]
  [:FO "Faroe Islands"]
  [:FJ "Fiji"]
  [:FI "Finland"]
  [:FR "France"]
  [:GF "French Guiana"]
  [:PF "French Polynesia"]
  [:TF "French Southern Territories"]
  [:GA "Gabon"]
  [:GM "Gambia"]
  [:GE "Georgia"]
  [:GH "Ghana"]
  [:GI "Gibraltar"]
  [:GR "Greece"]
  [:GL "Greenland"]
  [:GD "Grenada"]
  [:GP "Guadeloupe"]
  [:GU "Guam"]
  [:GT "Guatemala"]
  [:GG "Guernsey"]
  [:GN "Guinea"]
  [:GW "Guinea-Bissau"]
  [:GY "Guyana"]
  [:HT "Haiti"]
  [:HM "Heard Island and McDonald Islands"]
  [:VA "Holy See (Vatican City State)"]
  [:HN "Honduras"]
  [:HK "Hong Kong"]
  [:HU "Hungary"]
  [:IS "Iceland"]
  [:IN "India"]
  [:ID "Indonesia"]
  [:IR "Iran, Islamic Republic of"]
  [:IQ "Iraq"]
  [:IE "Ireland"]
  [:IM "Isle of Man"]
  [:IL "Israel"]
  [:IT "Italy"]
  [:JM "Jamaica"]
  [:JP "Japan"]
  [:JE "Jersey"]
  [:JO "Jordan"]
  [:KZ "Kazakhstan"]
  [:KE "Kenya"]
  [:KI "Kiribati"]
  [:KP "Korea, Democratic People's Republic of"]
  [:KR "Korea, Republic of"]
  [:KW "Kuwait"]
  [:KG "Kyrgyzstan"]
  [:LA "Lao People's Democratic Republic"]
  [:LV "Latvia"]
  [:LB "Lebanon"]
  [:LS "Lesotho"]
  [:LR "Liberia"]
  [:LY "Libya"]
  [:LI "Liechtenstein"]
  [:LT "Lithuania"]
  [:LU "Luxembourg"]
  [:MO "Macao"]
  [:MK "Macedonia, the former Yugoslav Republic of"]
  [:MG "Madagascar"]
  [:MW "Malawi"]
  [:MY "Malaysia"]
  [:MV "Maldives"]
  [:ML "Mali"]
  [:MT "Malta"]
  [:MH "Marshall Islands"]
  [:MQ "Martinique"]
  [:MR "Mauritania"]
  [:MU "Mauritius"]
  [:YT "Mayotte"]
  [:MX "Mexico"]
  [:FM "Micronesia, Federated States of"]
  [:MD "Moldova, Republic of"]
  [:MC "Monaco"]
  [:MN "Mongolia"]
  [:ME "Montenegro"]
  [:MS "Montserrat"]
  [:MA "Morocco"]
  [:MZ "Mozambique"]
  [:MM "Myanmar"]
  [:NA "Namibia"]
  [:NR "Nauru"]
  [:NP "Nepal"]
  [:NL "Netherlands"]
  [:NC "New Caledonia"]
  [:NZ "New Zealand"]
  [:NI "Nicaragua"]
  [:NE "Niger"]
  [:NG "Nigeria"]
  [:NU "Niue"]
  [:NF "Norfolk Island"]
  [:MP "Northern Mariana Islands"]
  [:NO "Norway"]
  [:OM "Oman"]
  [:PK "Pakistan"]
  [:PW "Palau"]
  [:PS "Palestinian Territory, Occupied"]
  [:PA "Panama"]
  [:PG "Papua New Guinea"]
  [:PY "Paraguay"]
  [:PE "Peru"]
  [:PH "Philippines"]
  [:PN "Pitcairn"]
  [:PL "Poland"]
  [:PT "Portugal"]
  [:PR "Puerto Rico"]
  [:QA "Qatar"]
  [:RE "Réunion"]
  [:RO "Romania"]
  [:RU "Russian Federation"]
  [:RW "Rwanda"]
  [:BL "Saint Barthélemy"]
  [:SH "Saint Helena, Ascension and Tristan da Cunha"]
  [:KN "Saint Kitts and Nevis"]
  [:LC "Saint Lucia"]
  [:MF "Saint Martin (French part)"]
  [:PM "Saint Pierre and Miquelon"]
  [:VC "Saint Vincent and the Grenadines"]
  [:WS "Samoa"]
  [:SM "San Marino"]
  [:ST "Sao Tome and Principe"]
  [:SA "Saudi Arabia"]
  [:SN "Senegal"]
  [:RS "Serbia"]
  [:SC "Seychelles"]
  [:SL "Sierra Leone"]
  [:SG "Singapore"]
  [:SX "Sint Maarten (Dutch part)"]
  [:SK "Slovakia"]
  [:SI "Slovenia"]
  [:SB "Solomon Islands"]
  [:SO "Somalia"]
  [:ZA "South Africa"]
  [:GS "South Georgia and the South Sandwich Islands"]
  [:SS "South Sudan"]
  [:ES "Spain"]
  [:LK "Sri Lanka"]
  [:SD "Sudan"]
  [:SR "Suriname"]
  [:SJ "Svalbard and Jan Mayen"]
  [:SZ "Swaziland"]
  [:SE "Sweden"]
  [:CH "Switzerland"]
  [:SY "Syrian Arab Republic"]
  [:TW "Taiwan, Province of China"]
  [:TJ "Tajikistan"]
  [:TZ "Tanzania, United Republic of"]
  [:TH "Thailand"]
  [:TL "Timor-Leste"]
  [:TG "Togo"]
  [:TK "Tokelau"]
  [:TO "Tonga"]
  [:TT "Trinidad and Tobago"]
  [:TN "Tunisia"]
  [:TR "Turkey"]
  [:TM "Turkmenistan"]
  [:TC "Turks and Caicos Islands"]
  [:TV "Tuvalu"]
  [:UG "Uganda"]
  [:UA "Ukraine"]
  [:AE "United Arab Emirates"]
  [:UM "United States Minor Outlying Islands"]
  [:UY "Uruguay"]
  [:UZ "Uzbekistan"]
  [:VU "Vanuatu"]
  [:VE "Venezuela, Bolivarian Republic of"]
  [:VN "Viet Nam"]
  [:VG "Virgin Islands, British"]
  [:VI "Virgin Islands, U.S."]
  [:WF "Wallis and Futuna"]
  [:EH "Western Sahara"]
  [:YE "Yemen"]
  [:ZM "Zambia"]
  [:ZW "Zimbabwe"]])

(def states
  {:AU
   {:ACT "Australian Capital Territory"
    :NSW "New South Wales"
    :NT "Northern Territory"
    :QLD "Queensland"
    :SA "South Australia"
    :TAS "Tasmania"
    :VIC "Victoria"
    :WA "Western Australia"}
   :BR
   {:AC "Acre"
    :AL "Alagoas"
    :AP "Amapá"
    :AM "Amazonas"
    :BA "Bahía"
    :CE "Ceará"
    :DF "Distrito Federal"
    :ES "Espirito Santo"
    :GO "Goiás"
    :MA "Maranhão"
    :MT "Mato Grosso"
    :MS "Mato Grosso do Sul"
    :MG "Minas Gerais"
    :PR "Paraná"
    :PB "Paraíba"
    :PA "Pará"
    :PE "Pernambuco"
    :PI "Piauí"
    :RN "Rio Grande do Norte"
    :RS "Rio Grande do Sul"
    :RJ "Rio de Janeiro"
    :RO "Rondônia"
    :RR "Roraima"
    :SC "Santa Catarina"
    :SE "Sergipe"
    :SP "São Paulo"
    :TO "Tocantins"}
  :CA
   {:AB "Alberta"
    :BC "British Columbia"
    :MB "Manitoba"
    :NB "New Brunswick"
    :NL "Newfoundland and Labrador"
    :NT "Northwest Territories"
    :NS "Nova Scotia"
    :NU "Nunavut"
    :ON "Ontario"
    :PE "Prince Edward Island"
    :QC "Quebec"
    :SK "Saskatchewan"
    :YT "Yukon"}
  :CU
   {:CA "Ciego de Ávila"
    :CF "Cienfuegos"
    :CG "Camagüey"
    :CH "Ciudad de La Habana"
    :GR "Granma (Bayamo)"
    :GT "Guantánamo"
    :HA "La Habana"
    :HO "Holguín"
    :IJ "Municipio Especial Isla de la Juventud"
    :LT "Las Tunas (Victoria de)"
    :MT "Matanzas"
    :PR "Pinar del Río"
    :SC "Santiago de Cuba"
    :SS "Sancti Spíritus"
    :VC "Villa Clara"}
  :DE
   {:BW "Baden-Württemberg"
    :BY "Bayern"
    :BE "Berlin"
    :BR "Brandenburg"
    :HB "Bremen"
    :HH "Hamburg"
    :HE "Hessen"
    :MV "Mecklenburg-Vorpommern"
    :NI "Niedersachsen"
    :NW "Nordrhein-Westfalen"
    :RP "Rheinland-Pfalz"
    :SL "Saarland"
    :SN "Sachsen"
    :ST "Sachsen-Anhalt"
    :SH "Schleswig-Holstein"
    :TH "Thüringen"}
  :DK
   {:RH "Region Hovedstaden"
    :RM "Region Midtjylland"
    :RN "Region Nordjylland"
    :RSj "Region Sjælland"
    :RSy "Region Syddanmark"}
  :ES
   {:AND "Andalucía"
    :ARA "Aragón"
    :CAN "Cantabria"
    :CLM "Castilla-La Mancha"
    :CyL "Castilla y León"
    :CAT "Cataluña"
    :CEU "Ceuta"
    :MAD "Comunidad de Madrid"
    :CVA "Comunidad Valenciana"
    :EXT "Extremadura"
    :GAL "Galicia"
    :IB "Islas Baleares"
    :LRJ "La Rioja"
    :MEL "Melilla"
    :NAV "Navarra"
    :PV "País Vasco"
    :AST "Principado de Asturias"
    :RMU "Región de Murcia"}
  :HR
   {:BJ "Bjelovarsko-bilogorska"
    :SB "Brodsko-posavska"
    :DU "Dubrovačko-neretvanska"
    :ZG "Grad Zagreb"
    :IS "Istarska"
    :KA "Karlovačka"
    :KC "Koprivničko-križevačka"
    :KR "Krapinsko-zagorska"
    :GS "Ličko-senjska"
    :CA "Međimurska"
    :OS "Osječko-baranjska"
    :PZ "Požeško-slavonska"
    :PG "Primorsko-goranska"
    :SK "Sisačko-moslavačka"
    :ST "Splitsko-dalmatinska"
    :SI "Šibensko-kninska"
    :VZ "Varaždinska"
    :VT "Virovitičko-podravska"
    :VU "Vukovarsko-srijemska"
    :ZD "Zadarska"
    :ZGZ "Zagrebačka"}
  :IN
   {:AP "Andhra Pradesh"
    :AR "Arunachal Pradesh"
    :AS "Assam"
    :BR "Bihar"
    :CT "Chhattisgarh"
    :GA "Goa"
    :GJ "Gujarat"
    :HR "Haryana"
    :HP "Himachal Pradesh"
    :JK "Jammu and Kashmir"
    :JH "Jharkhand"
    :KA "Karnataka"
    :KL "Kerala"
    :MP "Madhya Pradesh"
    :MH "Maharashtra"
    :MN "Manipur"
    :ML "Meghalaya"
    :MZ "Mizoram"
    :NL "Nagaland"
    :OR "Orissa"
    :PB "Punjab"
    :RJ "Rajasthan"
    :SK "Sikkim"
    :TN "Tamil Nadu"
    :TR "Tripura"
    :UL "Uttarakhand"
    :UP "Uttar Pradesh"
    :WB "West Bengal"
    :AN "Andaman and Nicobar Islands"
    :CH "Chandigarh"
    :DN "Dadra and Nagar Haveli"
    :DD "Daman and Diu"
    :DL "Delhi"
    :LD "Lakshadweep"
    :PY "Puducherry"}
  :IT
   {:AG "Agrigento"
    :AL "Alessandria"
    :AN "Ancona"
    :AO "Aosta / Aoste"
    :AR "Arezzo"
    :AP "Ascoli Piceno"
    :AT "Asti"
    :AV "Avellino"
    :BA "Bari"
    :BL "Belluno"
    :BN "Benevento"
    :BG "Bergamo"
    :BI "Biella"
    :BO "Bologna"
    :BZ "Bolzano / Bolzen"
    :BS "Brescia"
    :BR "Brindisi"
    :CA "Cagliari"
    :CL "Caltanisetta"
    :CB "Campobasso"
    :CE "Caserta"
    :CT "Catania"
    :CZ "Catanzaro"
    :CH "Chieti"
    :CO "Como"
    :CS "Cosenza"
    :CR "Cremona"
    :KR "Crotone"
    :CN "Cuneo"
    :EN "Enna"
    :FE "Ferrara"
    :FI "Firenze"
    :FG "Foggia"
    :FO "Forl"
    :FR "Frosinone"
    :GE "Genova"
    :GO "Gorizia"
    :GR "Grosseto"
    :IM "Imperia"
    :IS "Isernia"
    :AQ "L'Aquila"
    :SP "La Spezia"
    :LT "Latina"
    :LE "Lecce"
    :LC "Lecco"
    :LI "Livorno"
    :LO "Lodi"
    :LU "Lucca"
    :MC "Macerata"
    :MN "Mantova"
    :MS "Massa-Carrara"
    :MT "Matera"
    :ME "Messina"
    :MI "Milano"
    :MO "Modena"
    :NA "Napoli"
    :NO "Novara"
    :NU "Nuoro"
    :OR "Oristano"
    :PD "Padova"
    :PA "Palermo"
    :PR "Parma"
    :PV "Pavia"
    :PG "Perugia"
    :PS "Pesaro-Urbino"
    :PE "Pescara"
    :PC "Piacenza"
    :PI "Pisa"
    :PT "Pistoia"
    :PN "Pordenone"
    :PZ "Potenza"
    :PO "Prato"
    :RG "Ragusa"
    :RA "Ravenna"
    :RC "Reggio di Calabria"
    :RE "Reggio nell'Emilia"
    :RI "Rieti"
    :RN "Rimini"
    :RM "Roma"
    :RO "Rovigo"
    :SA "Salerno"
    :SS "Sassari"
    :SV "Savona"
    :SI "Siena"
    :SR "Siracusa"
    :SO "Sondrio"
    :TA "Taranto"
    :TE "Teramo"
    :TR "Terni"
    :TO "Torino"
    :TP "Trapani"
    :TN "Trento"
    :TV "Treviso"
    :TS "Trieste"
    :UD "Udine"
    :VA "Varese"
    :VE "Venezia"
    :VB "Verbania"
    :VC "Vercelli"
    :VR "Verona"
    :VV "Vibo Valentia"
    :VI "Vicenza"
    :VT "Viterbo"}
  :MX
   {:AGS "Aguascalientes"
    :BCN "Baja California Norte"
    :BCS "Baja California Sur"
    :CAM "Campeche"
    :CHIS "Chiapas"
    :CHIH "Chihuahua"
    :COAH "Coahuila"
    :COL "Colima"
    :DF "Distrito Federal"
    :DGO "Durango"
    :GTO "Guanajuato"
    :GRO "Guerrero"
    :HGO "Hidalgo"
    :JAL "Jalisco"
    :MEX "México (Estado de)"
    :MICH "Michoacán"
    :MOR "Morelos"
    :NAY "Nayarit"
    :NL "Nuevo León"
    :OAX "Oaxaca"
    :PUE "Puebla"
    :QRO "Querétaro"
    :QROO "Quintana Roo"
    :SLP "San Luis Potosí"
    :SIN "Sinaloa"
    :SON "Sonora"
    :TAB "Tabasco"
    :TAMPS "Tamaulipas"
    :TLAX "Tlaxcala"
    :VER "Veracruz"
    :YUC "Yucatán"
    :ZAC "Zacatecas"}
  :NO
   {:AK "Akershus"
    :AA "Aust-Agder"
    :BU "Buskerud"
    :FI "Finnmark"
    :HE "Hedmark"
    :HO "Hordaland"
    :MR "Møre og Romsdal"
    :NT "Nord-Trøndelag"
    :NL "Nordland"
    :OP "Oppland"
    :OS "Oslo"
    :RO "Rogaland"
    :SF "Sogn og Fjordane"
    :ST "Sør-Trøndelag"
    :TE "Telemark"
    :TR "Troms"
    :VA "Vest-Agder"
    :VF "Vestfold"
    :ØF "Østfold"}
  :NZ
   {:AUK "Auckland"
    :BOP "Bay of Plenty"
    :CAN "Canterbury"
    :HKB "Hawke's Bay"
    :MWT "Manawatu-Wanganui"
    :NTL "Northland"
    :OTA "Otago"
    :STL "Southland"
    :TKI "Taranaki"
    :WKO "Waikato"
    :WGN "Wellington"
    :WTC "West Coast"
    :GIS "Gisborne District"
    :MBH "Marlborough District"
    :NSN "Nelson City"
    :TAS "Tasman District"
    :CIT "Chatham Islands Territory"}
  :UA
   {:CR "Autonomous Republic of Crimea"
    :CK "Cherkashchyna"
    :CN "Chernihivshchyna"
    :CV "Chernivechchyna"
    :DP "Dnipropetrovshchyna"
    :DN "Donechchyna"
    :IF "Ivano-Frankivshchyna"
    :KH "Kharkivshchyna"
    :KS "Khersonshchyna"
    :KM "Khmel'nychchyna"
    :KV "Kyivshchyna"
    :KR "Kirovohradshchyna"
    :LG "Luhanshchyna"
    :LV "L'vivshchyna"
    :MK "Mykolaivshchyna"
    :OD "Odeshchyna"
    :PL "Poltavshchyna"
    :RV "Rivnenshchyna"
    :SM "Sumshchyna"
    :TAS "Ternopil'shchyna"
    :VN "Vinnychchyna"
    :VOLYN "Volyn'"
    :ZK "Zakarpattia"
    :ZP "Zaporizhzhia"
    :ZT "Zhytomyrshchyna"}
  :US
   {:AL "Alabama"
    :AK "Alaska"
    :AZ "Arizona"
    :AR "Arkansas"
    :CA "California"
    :CO "Colorado"
    :CT "Connecticut"
    :DE "Delaware"
    :DC "District Of Columbia"
    :FL "Florida"
    :GA "Georgia"
    :HI "Hawaii"
    :ID "Idaho"
    :IL "Illinois"
    :IN "Indiana"
    :IA "Iowa"
    :KS "Kansas"
    :KY "Kentucky"
    :LA "Louisiana"
    :ME "Maine"
    :MD "Maryland"
    :MA "Massachusetts"
    :MI "Michigan"
    :MN "Minnesota"
    :MS "Mississippi"
    :MO "Missouri"
    :MT "Montana"
    :NE "Nebraska"
    :NV "Nevada"
    :NH "New Hampshire"
    :NJ "New Jersey"
    :NM "New Mexico"
    :NY "New York"
    :NC "North Carolina"
    :ND "North Dakota"
    :OH "Ohio"
    :OK "Oklahoma"
    :OR "Oregon"
    :PA "Pennsylvania"
    :RI "Rhode Island"
    :SC "South Carolina"
    :SD "South Dakota"
    :TN "Tennessee"
    :TX "Texas"
    :UT "Utah"
    :VT "Vermont"
    :VA "Virginia"
    :WA "Washington"
    :WV "West Virginia"
    :WI "Wisconsin"
    :WY "Wyoming"
    :AS "American Samoa"
    :FM "Federated States of Micronesia"
    :GU "Guam"
    :MH "Marshall Islands"
    :MP "Northern Mariana Islands"
    :PW "Palau"
    :PR "Puerto Rico"
    :VI "Virgin Islands"
    :AA "Armed Forces Americas (except Canada)"
    :AE "Armed Forces Europe, Canada, Africa, or Middle East"
    :AP "Armed Forces Pacific"}})

(defn wish-box [wishing-one]
  [:div.wish-box
   [:div.demo
    {:on-click #(dispatch [:add-to-wish-list wishing-one :demo])}
    [:header
     [:h5 "Demo"]
     [:div.price "Free"]]]
   [:div.basic
    {:on-click #(dispatch [:add-to-wish-list wishing-one :basic])}
    [:header
     [:h5 "Basic"]
     [:div.price "From $30"]]
    [:div.description
     [:h6 "Standart font encoding"]
     [:p "Uppercase, Lowercase, Ligatures, Currency, Numerals, Fractions, Mathematical, Punctuations"]]]
   [:div.premium
    {:on-click #(dispatch [:add-to-wish-list wishing-one :premium])}
    [:header
     [:h5 "Premium"]
     [:div.price "From $50"]]
    [:div.description
     [:h6 "Extended font encoding"]
     [:p "Uppercase, Lowercase, Smallcaps, Extended Ligatures, Superscript, Subscript, Extend Currency, Extended Numerals, Extended Fractions, Mathematical, Punctuations, Arrows"]]]
   [:div.superior
    {:on-click #(dispatch [:add-to-wish-list :superior])}
    [:header
     [:h5 "Superior"]
     [:div.price "$1920"]]
    [:div.description
     [:p "All three typefaces in the Premium package: Hrot, Kunda Book, Vegan Sans"]
     [:p "+ Our next two released typefaces for free."]]]
   [:button.help "What?"]])

(defn page []
  (fn []
    (let [wish-list (deref (subscribe [:wish-list]))
          wish-list-count (count wish-list)
          showing-wish-list (subscribe [:showing-wish-list])
          checkout-started (deref (subscribe [:checkout-started]))
          eula-checked (deref (subscribe [:eula-checked]))
          header-class (subscribe [:header-class])
          order (subscribe [:order])
          selected-country (@order :country)]
       (when (pos? wish-list-count)
        (if (not @showing-wish-list)
          [:button
           {:class (str "wish-list-button " @header-class)
            :on-click #(dispatch [:show-wish-list])}
           (str "You have " wish-list-count " wish" (when (> wish-list-count 1) "es"))]
          [:div {:class (str "wish-list " (and checkout-started "checking-out"))}
            (if checkout-started
              [:header
               [:h2 "Superior Wish List"]
               [:button.back-button.wish-list
                {:on-click #(dispatch [:checkout-canceled])} "Back to wish list"]]
              [:header
               [:h2 "Superior Cart"]
               [:button.back-button.app
                {:on-click #(dispatch [:hide-wish-list])} "I wish more"]])
           [:div.content
            [:table
             [:thead
              [:tr
               [:th ""]
               [:th "Package"]
               [:th "License"]
               [:th "Users"]
               [:th.price "Price"]
               (when-not checkout-started
                 [:th.remove
                  [:button
                   {:on-click #(dispatch [:remove-all-from-wishlist])}
                   "Remove all"]])]]
             [:tbody
              (for [item (keys wish-list)]
                (let [name (join  " " (map capitalize (split item #"-")))
                      package (get-in wish-list [item :package])
                      license (get-in wish-list [item :license])
                      users (get-in wish-list [item :users])]
                  ^{:key item}
                  [:tr
                   [:td.name name]
                   [:td.package
                    (if checkout-started
                      [:span (package-options package)]
                      [:select
                       {:defaultValue package
                        :on-change #(dispatch [:change-package-in-wish-list item (keyword (-> % .-target .-value))])}
                        (for [package (keys package-options)]
                          ^{:key package}
                           [:option {:value package} (package-options package)])])]
                   [:td.license
                    (if checkout-started
                      [:span (license-options license)]
                      [:select
                       {:defaultValue license
                        :on-change #(dispatch [:change-license-in-wish-list item (keyword (-> % .-target .-value))])}
                        (for [license (keys license-options)]
                          ^{:key license}
                           [:option {:value license} (license-options license)])])]
                   [:td.users
                    (if checkout-started
                      [:span (users-options users)]
                      [:select
                       {:defaultValue users
                        :on-change #(dispatch [:change-users-in-wish-list item (keyword (-> % .-target .-value))])}
                        (for [users (keys users-options)]
                          ^{:key users}
                           [:option {:value users} (users-options users)])])]
                   [:td.price "30"]
                   (when-not checkout-started
                     [:td.remove
                      [:button
                       {:on-click #(dispatch [:remove-from-wishlist item])}
                       "Remove"]])]))
              [:tr
               [:td]
               [:td]
               [:td]
               [:th.total "Total"]
               [:th.total (* wish-list-count 30)]
               [:th.checkout]]]]
            (when checkout-started
              [:form#checkout
               [:div.column
                [:label
                 "First name"
                 [:input {:required "required"
                          :name "first-name"
                          :value (:first-name @order)
                          :on-change #(dispatch [:order-changed :first-name (-> % .-target .-value)])
                          :type "text"}]]
                [:label
                 "Last name"
                 [:input {:required "required"
                          :name "last-name"
                          :value (:last-name @order)
                          :on-change #(dispatch [:order-changed :last-name (-> % .-target .-value)])
                          :type "text"}]]
                [:label.grey
                 "Company"
                 [:input {:name "company"
                          :value (:company @order)
                          :on-change #(dispatch [:order-changed :company (-> % .-target .-value)])
                          :type "text"}]]
                [:label
                 "Street"
                 [:input {:required "required"
                          :name "street"
                          :value (:street @order)
                          :on-change #(dispatch [:order-changed :street (-> % .-target .-value)])
                          :type "text"}]]
                [:label
                 "City"
                 [:input {:required "required"
                          :name "city"
                          :value (:city @order)
                          :on-change #(dispatch [:order-changed :city (-> % .-target .-value)])
                          :type "text"}]]
                [:label
                 "Country"
                 [:select {:name "country"
                           :defaultValue selected-country
                           :on-change #(dispatch [:country-selected (-> % .-target .-value)]) }
                  [:option "–––"]
                  (for [country countries]
                    ^{:key (first country)}
                    [:option {:value (first country)} (second country)])]]

                (if-let [states (states selected-country)]
                  [:label
                    "State"
                    [:select {:name "state"}
                     {:name "state"
                      :defaultValue (:state order)
                      :on-change #(dispatch [:state-selected] (-> % .-target .-value))}
                     (for [state (sort states)]
                       ^{:key (first state)}
                       [:option {:value (first state)} (second state)])]])
                [:label
                 "Zip"
                 [:input.zip {:required "required"
                          :name "Zip"
                           :value (:zip @order)
                           :on-change #(dispatch [:order-changed :zip (-> % .-target .-value)])
                          :type "text"}]]]
                [:div.column
                 [:label
                  "Email"
                  [:input {:required "required"
                           :name "email"
                           :value (:email @order)
                           :on-change #(dispatch [:order-changed :email (-> % .-target .-value)])
                           :type "email"}]]
                [:label.grey
                 "Phone"
                 [:input {:name "phone"
                          :value (:phone @order)
                          :on-change #(dispatch [:order-changed :phone (-> % .-target .-value)])
                          :type "text"}]]
                [:div.pay-box
                 [:label.eula
                  "I agree with "
                  [:a
                   {:href "#/eula"}
                   "terms and conditions"]
                  [:input
                   {:on-change #(dispatch [:toggle-eula (-> % .-target .-checked)])
                    :type "checkbox"
                    :name "eula"}]]
                 (when eula-checked [:button "Confirm"])]]])]
            (when-not checkout-started
             [:button.checkout
              {:on-click #(dispatch [:checkout-started])}
              "Make wish come true"])])))))

