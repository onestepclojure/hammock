(ns hammock.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[hammock started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[hammock has shut down successfully]=-"))
   :middleware identity})
