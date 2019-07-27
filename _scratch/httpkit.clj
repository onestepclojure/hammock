(ns httpkit
 (:require [org.httpkit.client :as http]))

(http/get "http://requestbin.fullcontact.com/195gy4y1")


;;;;;;;;;
;; synchronous

(let [{:keys [status headers body error] :as resp} @(http/get "http://jsonplaceholder.typicode.com/todos/1")]
  (if error
    (println "Failed, exception: " error)
    (println "HTTP GET success: " body)))


;; concurrently
(let [resp1 (http/get "http://http-kit.org/")
      resp2 (http/get "http://clojure.org/")]
  (println "Response 1's status: " (:status @resp1)) ; wait as necessary
  (println "Response 2's status: " (:status @resp2)))


;;;;;;;;;
;; asynchronous

(def options {:timeout 500             ; ms
              :basic-auth ["user" "pass"]
              :query-params {:param "value" :param2 ["value1" "value2"]}
              :user-agent "User-Agent-string"
              :headers {"X-Header" "Value"}})

(http/get "https://jsonplaceholder.typicode.com/todos/1" options
          (fn [{:keys [status headers body error]}] ;; asynchronous response handling
            (if error
              (println "Failed, exception is " error)
              (println "Async HTTP GET: " status))))


