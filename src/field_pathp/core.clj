(ns field-pathp.core
  (:refer-clojure :exclude [empty?])
  (:use [cljts geom analysis transform relation])
  (:import [com.vividsolutions.jts.geom Coordinate Geometry Polygon LineString LinearRing Point GeometryFactory PrecisionModel]))

(def pol1 (polygon
            (linear-ring [(c 7 64) (c 43 64) (c 39 37) (c 7 37) (c 7 64)])
            [(linear-ring [(c 18 55) (c 33 55) (c 32 51) (c 16 51) (c 18 55)])]))

(def toolsize 5) "Tractor tool size, distance between linestrings"

(defn coord-vec
  "Convert a JTS Coordinate to a vector."
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

"Variable Definitions needed to decomposition process"
(def hole (get-hole-coords pol1))
(def enve (envelope pol1)) "Minimum envolpe rectangle"
(def cenve (get-coords enve)) "Vertex coords"
(def xaxis (get (cenve 0) 0)) "X Axis "
(def yaxistop (get (cenve 1) 1)) "top Y Axis"
(def yaxisbot (get (cenve 0) 1)) "bottom Y Axis"
(def envecoord (coordinates enve))
(def toplns (line-string [(envecoord 1) (envecoord 2)])) "Top linestring of the envelope rectangle"
(def botlns (line-string [(envecoord 3) (envecoord 4)])) "Bottom linestring of the evelope rectangle"

"linestrings recursive generator"
(loop [x xaxis]
  (when (< x (+ (length toplns) xaxis) )
    (line-string [(envecoord 1) (envecoord 2)])
    (println lns)
    (recur ( + x toolsize))))
