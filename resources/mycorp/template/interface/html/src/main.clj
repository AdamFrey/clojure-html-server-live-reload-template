(ns {{top/ns}}.{{main/ns}}
    (:require
     [hiccup2.core :as h]
     [nextjournal.beholder :as beholder]
     [org.httpkit.server :as http-kit]
     [reitit.ring :as reitit.ring]
     [ring.middleware.resource :as ring.resource]))

(defonce *server (atom nil))
(defonce *channels (atom #{}))
(defonce *file-watcher (atom nil))

(defn- ws-handler
  [request]
  (http-kit/as-channel request
    {:on-open  (fn [channel]
                 (swap! *channels conj channel))
     :on-close (fn [channel _status]
                 (swap! *channels disj channel))}))

(defn html-index [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (str
           (h/html
             [:html
              [:head
               [:link {:rel "stylesheet" :href "css/screen.css"}]
               [:script {:src "js/live-reload.js"}]]
              [:body
               [:span "Hello World"]]]))})

(def router
  (reitit.ring/router
    [["/" {:get html-index}]
     ["/live-reload" {:get ws-handler}]]))

(def handler
  (-> (reitit.ring/ring-handler router)
      (ring.resource/wrap-resource "public")))

(defn- notify-reload!
  [& _args]
  (doseq [ch @*channels]
    (http-kit/send! ch "reload")))

(defn -main
  [& args]

  (swap! *server
    #(or %
         (do
           (println "Starting server at localhost:3000")
           (http-kit/run-server #'handler
             {:port 3000
              :join? true}))))
  (swap! *file-watcher
    #(or %
         (beholder/watch notify-reload! "resources"))))

(notify-reload!)

(comment
  (-main)
  )
