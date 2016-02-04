(ns superiortype.first-aid
  (:require
    [re-frame.core :as re-frame :refer [subscribe dispatch]]))

(defn subsection [id title & paragraphs]
  (fn []
    (let [opened (subscribe [:section-opened id])]
      [:div {:id id}
       [:h4
        [:button
         {:on-click (if @opened
                      #(dispatch [:section-closed id])
                      #(dispatch [:section-opened id]))
          :class (when @opened "opened")}
         title]]
       (for [paragraph paragraphs]
        [:p
         {:class (when-not (nil? @opened) (if @opened "opened" "closed"))}
         paragraph])])))

(defn page []
  (set! (-> js/document .-body .-className) "first-aid")
  [:section#first-aid
   [:div.column
    [:h2 "Welcome to our client support page. Here you will find all the information about our services and terms of use. We have created a user friendly layout for a quicker and better way to get you acquainted."]
    [:p "Type design is a work of great diligence and meticulous pondering over all the curve combinations leading to a perfect harmony of each and every character. All the ligatures, words, phrases and sentences form a balanced whole. For us it is not only work that we are enthusiastic about but a business that we take seriously. Therefore we pay special attention to customer care as well as license observance."]
    [:p "Please read carefully the license agreement and terms of use."]
    [:p "Feel free to contact us about any further questions."]]
   [:div.column
    [:h3 "Wish Font"]
    [subsection :who-buys-fonts "Who buys fonts"
     "Whoever needs fonts for personal or commercial use. You need to be a holder of our license to be able to download and use our fonts. The license should be made in your name. If your client wants to use a solution concieved by you using our font he must purchase a license in his name."]
    [subsection :how-to-buy "How to buy"
     "Every font is provided with a wish button. Upon hitting the button font packages appear."
     "After choosing the desired packaged your choice will appear on the wishlist."
     "You can furthermore choose from more font options in the wishlist before checking out."]
    [subsection :wish-list "Wish list"
     "This page presents a table of settings of your wishlist. From left to right there are columns of wished fonts, packages, number of licences, names of users, currency and on the far right a column allowing to remove items in the given row. You can also click on the wish more button to add more fonts. When you are satisfied with your settings you can proceed to checkout."]
    [subsection :payment "Payment"
     "First fill out your personal information. Then you need to agree with our terms and conditions. After confirming you can pay. Payment can be made by Paypal or a credit card. There is also the possibility of paying by bank transfer, however please note that the authorization of the payment may take several days."]
    [subsection :downloading "Downloading fonts"
     "Once the order has been proccessed and we have recieved your payment you will be redirected to a page where you can download the fonts immediately. If you have payed by a bank transfer you will be able to download the fonts after we recieve the payment. Then we will send a link with the purchased fonts to your e-mail address."]
    [:h3 "Packages"]
    [subsection :what-is-package "What is a package?"
     "To give you an ample variety of choices we have designed font packages. Every package includes specific characters and features. You have one typeface but you are free to choose from four different packages."]
    [subsection :demo "Demo"
     "Demo package is designed for future customers. If you wish to try out the typeface before deciding about purchasing it you can download it for free."]
    [subsection :basic "Basic"
     "This package can appeal to designers who can do without small caps, denominators or arrows. If you design posters, logotypes or subtitles on a daily basis, this package is the perfect choice."]
    [subsection :premium "Premium"
     "We intended this package for professionals who make use of the extended character set to the fullest. Namely in designing books, newspapers and comprehensive corporate identities."]
    [subsection :superior "Superior"
     "This extensive package is our Superior Type special. It includes our first three typefaces in all thirty six available styles. These typefaces are Hrot, Kunda Book and Vegan Sans in Premium packages."]]
   [:div.column
    [:h3 "Licensing"]
    [subsection :eula "EULA"
     "End User Agreement is set between Superior Type and the end user who must accept the conditions of the agreement before purchasing and using the fonts. What you are purchasing are not the fonts themselves but a license for their use. You will find the terms and conditions specified on this link:"]
    [subsection :extended-licenses "Extended licences"
     "In addition to the basic Print license you can also choose from Web license, discounted Print + Web license or Application license. You will find the terms and conditions specified on this link:"]
    [subsection :multi-licences "Multi-licenses"
     "Fonts are a software, therefore licenses are authorized for a certain number of computers. The basic installation offer spans from one to five computers in one geographical location. In case you need licenses for more than five computers or different kinds of licenses, you can select the number in the wishlist table."]
    [subsection :web-fonts-licensing "Web fonts licensing"
     "There are no time limitations using web fonts. There are two offers - you can either purchase Web fonts individually or opt for a convenient combination of a Print + Web bundle. The basic license allows the usage of web fonts on up to five domains with an unlimited number of pages and up to 10 000 displays per month. Should you be interested in buying web fonts for traffic-intensive sites you can specify the number of views in your wishlist."]
    [:h3 "Limitations"]
    [subsection :reselling "Re-selling"
     "It is forbidden for the user to sell the font to a third party."]
    [subsection :font-modification "Font modifications"
     "Should you need to modify any purchased fonts please contact us via e-mail."]
    [subsection :return-policy "Return policy"
     "Once downloaded a font cannot be returned, refunded or replaced. However if the font is defected you can contact us within two weeks and get the replacement in such a case right away."]
    [subsection :embedding-pdf "Embedding PDF files"
     "The embedding of fonts in PDF documents is allowed provided that you set embedding to prevent the extraction of the fonts."]]
   [:div.column
    [:h3 "Technical support"]
    [subsection :language-support "Language support"
     "All our fonts contain characters of the following languages: Afrikaans, Albanian, Basque, Belorussian (Latin), Bosnian, Breton, Catalan, Chamorro, Chichewa, Cornish, Crimean Tatar (Latin), Croatian, Czech, Danish, Dutch, Esperanto, Estonian, Faroese, Finnish, French, Frisian, Galician, German, Greenlandic, Hawaiian, Hungarian, Icelandic, Indonesian, Italian, Kashubian, Kurdish, Latvian, Lithuanian, Luxembourgian, Maltese, Maori, Moldavian (Latin), Norwegian, Occitan, Polish, Portuguese, Rhaeto-Romance, Romanian, Serbian (Latin), Scots Gaelic, Slovak, Slovenian, Spanish, Swedish, Tswana, Turkish, Turkmen, Welsh, Waloon, Wolof"]
    [subsection :mail "Mail"
     [:span "In case of any questions or technical issues with purchased fonts contact us on "
      [:a {:href "mailto:mail@superiortype.com"} "mail@superiortype.com"]]]]])
