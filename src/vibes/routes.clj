(ns vibes.routes
  (:require [reitit.ring :as reitit-ring]))

(defn a-handler
  [system request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "a vibe is a vibe is a vibe"})

(defn gm-handler
  [system request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "gm"})

(defn gn-handler
  [system request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "gn"})

(defn routes
  [system]
  [["/"   {:get {:handler (partial #'a-handler system)}}]
   ["/gm" {:get {:handler (partial #'gm-handler system)}}]
   ["/gn" {:get {:handler (partial #'gn-handler system)}}]])

(defn not-found-handler
  [_request]
  {:status 404
   :headers {"Content-Type" "text/html"}
   :body "Not Found"})

(defn root-handler
  [system request]
  (let [handler (reitit-ring/ring-handler
                 (reitit-ring/router
                  (routes system))
                 #'not-found-handler)]
    (handler request)))