(ns stable-matching.core-test
  (:require [clojure.test :refer :all]
            [stable-matching.core :refer [stable-match]]))

(deftest a-test
  (testing "Sample test"
    (let [women-preference {:charlotte [:bingley :darcy :collins :wockham]
                            :elizabeth [:wickham :darcy :bingley :collins]
                            :jane      [:bingley :wickham :darcy :collins]
                            :lydia     [:bingley :wickham :darcy :collins]}

          men-preference   {:bingley [:jane :elizabeth :lydia :charlotte]
                            :jane    [:jane :elizabeth :lydia :charlotte]
                            :darcy   [:elizabeth :jane :charlotte :lydia]
                            :wickham [:lydia :jane :elizabeth :charlotte]}]
      (is (= (stable-match women-preference men-preference)
             1)))))
