(ns stable-matching.core-test
  (:require [clojure.test :refer :all]
            [stable-matching.core :refer [stable-match]]))

(deftest stable-match
  (testing "Sample test"
    (let [women-preference {:charlotte [:bingley :darcy :collins :wickham]
                            :elizabeth [:wickham :darcy :bingley :collins]
                            :jane      [:bingley :wickham :darcy :collins]
                            :lydia     [:bingley :wickham :darcy :collins]}

          men-preference   {:bingley [:jane :elizabeth :lydia :charlotte]
                            :collins [:jane :elizabeth :lydia :charlotte]
                            :darcy   [:elizabeth :jane :charlotte :lydia]
                            :wickham [:lydia :jane :elizabeth :charlotte]}]
      (is (= (stable-match women-preference men-preference)
             {:jane      :bingley
              :lydia     :wickham
              :elizabeth :darcy
              :charlotte :collins})))

    (let [women-preference {:a [:B :A :C]
                            :b [:B :A :C]
                            :c [:A :C :B]}
          men-preference   {:A [:a :c :b]
                            :B [:b :a :c]
                            :C [:c :a :b]}]
      (is (= (stable-match women-preference men-preference)
             {:a :A
              :b :B
              :c :C})))

    (let [women-preference {:a [:A :B :C]
                            :b [:A :B :C]
                            :c [:B :C :A]}
          men-preference   {:A [:b :a :c]
                            :B [:a :c :b]
                            :C [:a :b :c]}]
      (is (= (stable-match women-preference men-preference)
             {:b :A
              :a :B
              :c :C})))
    (let [women-preference {:abi  [:bob :fred :jon :gav :ian :abe :dan :ed :col :hal]
                            :bea  [:bob :abe :col :fred :gav :dan :ian :ed :jon :hal]
                            :cath [:fred :bob :ed :gav :hal :col :ian :abe :dan :jon]
                            :dee  [:fred :jon :col :abe :ian :hal :gav :dan :bob :ed]
                            :eve  [:jon :hal :fred :dan :abe :gav :col :ed :ian :bob]
                            :fay  [:bob :abe :ed :ian :jon :dan :fred :gav :col :hal]
                            :gay  [:jon :gav :hal :fred :bob :abe :col :ed :dan :ian]
                            :hope [:gav :jon :bob :abe :ian :dan :hal :ed :col :fred]
                            :ivy  [:ian :col :hal :gav :fred :bob :abe :ed :jon :dan]
                            :jan  [:ed :hal :gav :abe :bob :jon :col :ian :fred :dan]}
          men-preference   {:abe  [:abi :eve :cath :ivy :jan :dee :fay :bea :hope :gay]
                            :bob  [:cath :hope :abi :dee :eve :fay :bea :jan :ivy :gay]
                            :col  [:hope :eve :abi :dee :bea :fay :ivy :gay :cath :jan]
                            :dan  [:ivy :fay :dee :gay :hope :eve :jan :bea :cath :abi]
                            :ed   [:jan :dee :bea :cath :fay :eve :abi :ivy :hope :gay]
                            :fred [:bea :abi :dee :gay :eve :ivy :cath :jan :hope :fay]
                            :gav  [:gay :eve :ivy :bea :cath :abi :dee :hope :jan :fay]
                            :hal  [:abi :eve :hope :fay :ivy :cath :jan :bea :gay :dee]
                            :ian  [:hope :cath :dee :gay :bea :abi :fay :ivy :jan :eve]
                            :jon  [:abi :fay :jan :gay :eve :bea :dee :cath :ivy :hope]}]
      (is (= (stable-match women-preference men-preference)
             {:eve  :hal
              :jan  :ed
              :bea  :fred
              :hope :ian
              :fay  :dan
              :dee  :col
              :abi  :jon
              :gay  :gav
              :ivy  :abe
              :cath :bob})))))