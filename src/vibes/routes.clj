(ns vibes.routes
  (:require [reitit.ring :as reitit-ring]
            [clojure.string :as str]))

(defn dynamic-handler
  [system request]
  (let [server-name (:server-name request)
        subdomain   (first (str/split server-name #"\."))
        path        (str/replace (:uri request) #"^/" "")
        response    (str subdomain " world on " path)]
    {:status 200
     :headers {"Content-Type" "text/html"}
     :body response}))

(defn routes
  [system]
  [["/*path" {:get {:handler (partial #'dynamic-handler system)}}]])

(defn not-found-handler
  [_request]
  {:status 404
   :headers {"Content-Type" "text/html"}
   :body "Not Found"})

(defn root-handler
  [system request]
  (let [handler (reitit-ring/ring-handler
                 (reitit-ring/router (routes system))
                 #'not-found-handler)]
    (handler request)))