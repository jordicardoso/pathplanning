(ns field-pathp.core-test
  (:require [clojure.test :refer :all]
            [field-pathp.core :refer :all])
  (:use [cljts geom analysis transform relation]))

#! "Documentation about clojure test syntax
#!   https://clojure.github.io/clojure/clojure.test-api.html"


(def squaredPolygonWithSquaredHole (polygon
            (linear-ring [(c 7 64) (c 43 64) (c 39 37) (c 7 37) (c 7 64)])
            [(linear-ring [(c 18 55) (c 33 55) (c 32 51) (c 16 51) (c 18 55)])]))



(deftest testPolygons
  (testing "Getting the coordinates of the hole of a given polygon"
    (is (= [[[18.0 55.0] [33.0 55.0] [32.0 51.0] [16.0 51.0] [18.0 55.0]]]
           (get-hole-coords squaredPolygonWithSquaredHole))))

  )

(def coord-2d (c 1 2))
(def coord-3d (c 1 2 3))

(deftest Coordinates2DTest
  (testing "Check coordinate is 2D" (is (true? (isCoordinate2D coord-2d))))
  (testing "Check coordinate is not 2D" (is (false? (isCoordinate2D coord-3d)))))

(deftest Coordinates3DTest
  (testing "Check coordinate is 3D" (is (true? (isCoordinate3D coord-3d))))
  (testing "Check coordinate is not 3D" (is (false? (isCoordinate3D coord-2d)))))


