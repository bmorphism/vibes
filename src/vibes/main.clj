(ns vibes.main
  (:require [vibes.system :as system]))

(defn -main []
  (system/start-system))