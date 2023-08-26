(ns {{top/ns}}.{{main/ns}}.reload
    (:require
     [org.httpkit.server :as http-kit]
     [nextjournal.beholder :as beholder]))

(defonce *channels (atom #{}))

(defn ws-handler
  [request]
  (http-kit/as-channel request
    {:on-open  (fn [channel]
                 (swap! *channels conj channel))
     :on-close (fn [channel _status]
                 (swap! *channels disj channel))}))

(defn notify!
  [& _args]
  (doseq [ch @*channels]
    (http-kit/send! ch "reload")))


(defonce *file-watcher (atom nil))

(defn start!
  []
  (swap! *file-watcher
    #(or %
         (beholder/watch notify! "resources"))))

(defn stop!
  []
  (when-let [file-watcher @*file-watcher]
    (beholder/stop file-watcher))
  (swap! *channels empty))
