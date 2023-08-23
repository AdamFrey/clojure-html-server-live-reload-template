(ns {{top/ns}}.{{main/ns}}
    (:require
     [hiccup2.core :as h]
     [org.httpkit.server :as http-kit]
     [reitit.ring :as reitit.ring]
     [ring.middleware.resource :as ring.resource]))

(defonce *server (atom nil))
(defonce *channels (atom #{}))

;; (defn- ws-connect! [channel]
;;  (println "channel open")
;;  (swap! *channels conj channel))

;; (defn- ws-disconnect! [channel status]
;;  (println "channel closed:" status)
;;  (swap! *channels disj channel))

;; (defn- ws-notify! [msg]
;;   (doseq [channel @*channels]
;;     (http-kit/send! channel msg)))

(defn- ws-handler
  [request]
  (http-kit/as-channel request
    {:on-open  (fn [channel]
                 (println "channel open")
                 (swap! *channels conj channel))
     :on-close (fn [channel status]
                 (println "channel closed:" status)
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

(defn wrap [handler id]
  (fn [request]
    (update (handler request) :wrap (fnil conj '()) id)))

(def router
  (reitit.ring/router
    [["/" {:get html-index}]
     ["/live-reload" {:get ws-handler}]]))

(def handler
  (-> (reitit.ring/ring-handler router)
      (ring.resource/wrap-resource "public")))

(defn -main
  [& args]
  (println "Starting server at localhost:3000")
  (when-not @*server
    (reset! *server
      (http-kit/run-server #'handler
        {:port 3000
         :join? true}))))

;; reload all clients on re-eval
(doseq [ch @*channels]
  (http-kit/send! (first @*channels) "reload"))

(comment
  (-main))
