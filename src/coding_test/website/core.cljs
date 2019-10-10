(ns coding-test.website.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            [reagent.core :as r]))


(def my-data (r/atom []))

(def list-style
  {:margin "auto"
   :width "50%"
   :border "3px solid black"
   :padding "40px"})

(go (let [response
            (<! (http/get "https://gbfs.urbansharing.com/oslobysykkel.no/station_information.json"
                          {:headers {"Client-Identifier" "IDENTIFIER"}}))
            stations (:stations (:data (:body response)))]
        (swap! my-data (constantly stations))))

(defn bike-station
  "Renders a bike station element"
  [{:keys [name capacity]}]
  [:div
   {:style
    {:color "white"
     :text-align "left"
     :border "2px solid black"
     :position "relative"
     :margin "10px"
     :padding "15px"}}
   [:h3 {:style
         {:top "-20px"
          :position "relative"
          :border "2px solid red"
          :width "35%"
          :text-align "left"}}
    name]
   (str "Capacity: " capacity)])
                     

(defn render-ui []
  (into [:div {:style list-style}]
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
