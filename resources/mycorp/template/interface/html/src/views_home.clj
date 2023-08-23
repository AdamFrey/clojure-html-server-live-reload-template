(ns {{top/ns}}.{{main/ns}}.views.home
    (:require
     [hiccup2.core :as h]
     [{{top/ns}}.{{main/ns}}.reload :as reload]))

(defn wrap-html
  [body]
  (str
    (h/html
      [:html
       [:head
        [:link {:rel "stylesheet" :href "css/styles.css"}]
        [:script {:src "js/live-reload.js"}]]
       body])))

(defn page
  []
  (wrap-html
    [:body
     [:span "Modify me in views/home.clj and in styles.css"]]))

(reload/notify!)
