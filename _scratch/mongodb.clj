(ns mongodb
  (:require [monger.core :as mg]
            [monger.collection :as mc])
  (:import [com.mongodb MongoOptions ServerAddress]
           [org.bson.types ObjectId]
           [com.mongodb DB WriteConcern]))

;; localhost, default port
(let [conn (mg/connect)])

;; using MongoOptions allows fine-tuning connection parameters,
;; like automatic reconnection (highly recommended for production environment)
(let [^MongoOptions opts (mg/mongo-options {:threads-allowed-to-block-for-connection-multiplier 300})
      ^ServerAddress sa  (mg/server-address "127.0.0.1" 27017)
      conn               (mg/connect sa opts)])

(let [conn (mg/connect)
      db   (mg/get-db conn "monger-test")]
  ;; with a generated document id, returns the complete
  ;; inserted document
  (mc/insert-and-return db "documents" {:name "John" :age 30})

  ;; with explicit document id (recommended)
  (mc/insert db "documents" {:_id (ObjectId.) :first_name "John" :last_name "Lennon"})

  ;; multiple documents at once
  (mc/insert-batch db "documents" [{:first_name "John" :last_name "Lennon"}
                                   {:first_name "Paul" :last_name "McCartney"}])

  ;; without document id (when you don't need to use it after storing the document)
  (mc/insert db "documents" {:first_name "John" :last_name "Lennon"})

  ;; with a different write concern
  (mc/insert db "documents" {:_id (ObjectId.) :first_name "John" :last_name "Lennon"} WriteConcern/JOURNAL_SAFE))


;; Find documents with Monger


(let [conn (mg/connect)
      db   (mg/get-db conn "monger-test")
      coll "documents"]
  (mc/insert db coll {:first_name "John"  :last_name "Lennon"})
  (mc/insert db coll {:first_name "Ringo" :last_name "Starr"})

  (println
   (mc/find db coll {:first_name "Ringo"})))



