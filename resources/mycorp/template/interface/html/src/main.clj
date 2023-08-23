(ns {{top/ns}}.{{main/ns}}
    (:require
     [org.httpkit.server :as http-kit]
     [reitit.ring :as reitit.ring]
     [ring.middleware.resource :as ring.resource]
     [{{top/ns}}.{{main/ns}}.reload :as reload]
     [{{top/ns}}.{{main/ns}}.views.home :as home]))

(defonce *server (atom nil))

(defn- html-response [html-string]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    html-string})

(def router
  (reitit.ring/router
    [["/" {:get (fn [_req]
                  (html-response (home/page)))}]
     ["/live-reload" {:get reload/ws-handler}]]))

(def handler
  (-> (reitit.ring/ring-handler router)
      (ring.resource/wrap-resource "public")))

(defn- start!
  []
  (swap! *server
    #(or %
         (do
           (println "Starting server at localhost:3000")
           (http-kit/run-server #'handler
             {:port 3000
              :join? true}))))

  (reload/start!))

(defn- stop!
  []
  (when-let [server @*server]
    (server :timeout 10))
  (reload/stop!))

(defn -main
  [& args]
  (start!))

(reload/notify!)

(comment
  (start!)
  (stop!)
  )
