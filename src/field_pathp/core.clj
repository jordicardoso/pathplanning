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

(defn getVectorFromCoordinate
  "Convert a JTS Coordinate to a vector of 2D or 3D"
  [^Coordinate coordinate]
  (if (.isNaN (.z coordinate))
    [(.x coordinate) (.y coordinate)]
    [(.x coordinate) (.y coordinate) (.z coordinate)]))

(defn getCoordinatesFromGeometry
  "Return a vec of coords for a JTS Geometry."
  [^Geometry geometry]
  (mapv getVectorFromCoordinate (.getCoordinates geometry)))

(defn getInteriorLinearRings
  "Return a Lazy-seq of JTS LinearRing that represent the holes in a Polygon."
  [^Polygon geometry]
  (map #(.getInteriorRingN geometry %) (range (.getNumInteriorRing geometry))))

(defn getHoleCoordinates
  "Return a vec of coord data that represents the holes of a JTS Polygon."
  [^Polygon geometry]
  (mapv getCoordinatesFromGeometry (getInteriorLinearRings geometry)))


