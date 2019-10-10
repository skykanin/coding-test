(ns coding-test.website.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            [reagent.core :as r]))

(def my-data (r/atom []))

(def styling
  {:list-style {:margin "auto"
                :width "50%"
                :border "3px solid black"
                :padding "40px"}
   :info-div {:color "white"
              :text-align "left"
              :border "2px solid black"
              :position "relative"
              :margin "10px"
              :padding "15px"}
   :info-title {:top "-10px"
                :position "relative"
                :border "2px solid red"
                :width "35%"
                :text-align "left"}
   :info-list {:list-style-type "none"
               :width "75%"
               :position "relative"
               :border "2px solid green"
               :padding "0"}
   :info-li {:display "inline-block"
             :padding "15px"}})

(go (let [response
          (<! (http/get
               "https://gbfs.urbansharing.com/oslobysykkel.no/station_information.json"
               {:headers {"Client-Identifier" "IDENTIFIER"}}))
          stations (:stations (:data (:body response)))]
      (swap! my-data (constantly stations))))

(defn list-item
  "Renders a list element containing text information"
  [[name val]]
  [:li
   {:style (:info-li styling)}
   (str (apply str (rest (str name))) ": " val)])

(defn bike-station
  "Renders a bike station element"
  [data]
  [:div
   {:style (:info-div styling)}
   [:h3 {:style (:info-title styling)} (:name data)]
   (into [:ul {:style (:info-list styling)}]
         (map list-item (dissoc data :name)))])

(defn render-ui []
  (into [:div {:style (:list-style styling)}]
        (map bike-station @my-data)))

(defn title-ui [title]
  [:h1 {:style
        {:color "white"
         :text-align "center"
         :padding "25px"}}
   title])

(defn mountit []
  (r/render [:div
             [title-ui "Site title"]
             [render-ui]]
            (.getElementById js/document "app")))

(mountit)
