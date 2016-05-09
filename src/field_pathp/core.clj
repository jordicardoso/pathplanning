(ns field-pathp.core
  (:refer-clojure :exclude [empty?])
  (:use [cljts geom analysis transform])
  (:import [com.vividsolutions.jts.geom
            Coordinate
            GeometryFactory
            PrecisionModel
            LinearRing
            Polygon
            Point]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))


