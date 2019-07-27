(ns hammock.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [hammock.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[hammock started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[hammock has shut down successfully]=-"))
   :middleware wrap-dev})
