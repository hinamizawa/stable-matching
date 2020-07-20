(ns stable-matching.core
  (:require [clojure.set :as set]))

;; stolen from https://github.com/weavejester/medley
(defn find-first
  "Finds the first item in a collection that matches a predicate. Returns a
  transducer when no collection is provided."
  ([pred]
   (fn [rf]
     (fn
       ([] (rf))
       ([result] (rf result))
       ([result x]
        (if (pred x)
          (ensure-reduced (rf result x))
          result)))))
  ([pred coll]
   (reduce (fn [_ x] (if (pred x) (reduced x))) nil coll)))

(defn- make-proposal
  [proposal-pool man woman]
  (update proposal-pool woman (fnil #(conj % man) [])))

(defn- get-most-preferred [coll prefers]
  (find-first #(.contains coll %) prefers))

(defn- update-suitor-pairs [proposal-pool suitor-pairs women-preference]
  (reduce (fn [pool [woman coll]]
            (assoc pool
              woman
              (get-most-preferred coll (women-preference woman))))
          suitor-pairs
          proposal-pool))

(defn- update-men-preference [men-preference proposed]
  (reduce (fn [preference man]
            (update preference man rest))
          men-preference proposed))

(defn stable-match [women-preference men-preference]
  (let [num-of-pairs (count women-preference)]
    (loop [men-preference men-preference
           suitor-pairs   {}]
      (if (= num-of-pairs (count suitor-pairs))
        suitor-pairs
        (let [men-single (set/difference (set (keys men-preference)) (set (vals suitor-pairs)))
              proposals  (reduce (fn [proposal man]
                                   (make-proposal proposal man (first (men-preference man))))
                                 (into {} (map (fn [[k v]] {k [v]}) suitor-pairs))
                                 men-single)]
          (recur (update-men-preference men-preference men-single)
                 (update-suitor-pairs proposals suitor-pairs women-preference)))))))

