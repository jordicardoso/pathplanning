(ns field-pathp.core
  (:refer-clojure :exclude [empty?])
  (:use [cljts geom analysis transform relation])
  (:import [com.vividsolutions.jts.geom Coordinate Geometry Polygon
                                        LinearRing Point GeometryFactory
                                        PrecisionModel]))


(defn isCoordinate2D
  "Check if a given coordinate is 2D"
  [^Coordinate coordinate]
  (.isNaN (.z coordinate))
  )

(defn isCoordinate3D
  "Check if a given coordiante is 3D"
  [^Coordinate coordinate]
  (not (isCoordinate2D coordinate))
  )

(defn coord-vec
  "Convert a JTS Coordinate to a vector of 2D or 3D"
  [^Coordinate coordinate]
  (if (.isNaN (.z coordinate))
    [(.x coordinate) (.y coordinate)]
    [(.x coordinate) (.y coordinate) (.z coordinate)]))

(defn get-coords
  "Return a vec of coords for a JTS Geometry."
  [^Geometry geometry]
  (mapv coord-vec (.getCoordinates geometry)))

(defn get-interior-rings
  "Return a seq of JTS LinearRing that represent the holes in a Polygon."
  [^Polygon geometry]
  (for [i (range 0 (.getNumInteriorRing geometry))]
    (.getInteriorRingN geometry i)))

(defn get-hole-coords
  "Return a vec of coord data that represents the holes of a JTS Polygon."
  [^Polygon geometry]
  (mapv get-coords (get-interior-rings geometry)))
