(ns field-pathp.core-test
  (:require [clojure.test :refer :all]
            [field-pathp.core :refer :all])
  (:use [cljts geom analysis transform relation])
  (:import (clojure.lang LazySeq)))

#! "Documentation about clojure test syntax
#!   https://clojure.github.io/clojure/clojure.test-api.html"

(def polygonWithoutHoles (polygon (linear-ring [(c 20 40) (c 20 46) (c 34 56) (c 20 40)]) nil))


(def squaredPolygonWithSquaredHole (polygon
            (linear-ring [(c 7 64) (c 43 64) (c 39 37) (c 7 37) (c 7 64)])
            [(linear-ring [(c 18 55) (c 33 55) (c 32 51) (c 16 51) (c 18 55)])]))

(def internalHole (linear-ring [(c 18 55) (c 33 55) (c 32 51) (c 16 51) (c 18 55)]))


(deftest testPolygons
  (testing "Getting the coordinates of the hole of a given polygon"
    (is (= [[[18.0 55.0] [33.0 55.0] [32.0 51.0] [16.0 51.0] [18.0 55.0]]]
           (getHoleCoordinates squaredPolygonWithSquaredHole))))

  )

(def coord-2d (c 1 2))
(def coord-3d (c 1 2 3))

(deftest Coordinates2DTest
  (testing "Verify coordinate is 2D" (is (true? (isCoordinate2D coord-2d))))
  (testing "Verify coordinate is not 2D" (is (false? (isCoordinate2D coord-3d)))))

(deftest Coordinates3DTest
  (testing "Verify coordinate is 3D" (is (true? (isCoordinate3D coord-3d))))
  (testing "Verify coordinate is not 3D" (is (false? (isCoordinate3D coord-2d)))))

(deftest TestGetVectorFromCoordinates
  (testing "Verify vector values from a given coordinate 2D"
    (is (= [1.0 2.0] (getVectorFromCoordinate coord-2d))))
  (testing "Verify vector values from a given coordinate 3D"
    (is (= [1.0 2.0 3.0] (getVectorFromCoordinate coord-3d))))
  )

(deftest TestGetCoordinatesFromGeometry
  (testing "Verify coordinates from a given polygon in 2D Coordinates"
    (is (= [[20.0 40.0] [20.0 46.0] [34.0 56.0] [20.0 40.0]]
           (getCoordinatesFromGeometry polygonWithoutHoles))))
  )

(deftest TestGetLinearRingOfTheHole
  (testing "Verify linear ring of interior hole"
    (is (= internalHole
           (first (getInteriorLinearRings squaredPolygonWithSquaredHole)))))

  (testing "Verify is of type LazySeq"
    (is (= LazySeq
           (type (getInteriorLinearRings squaredPolygonWithSquaredHole))))
    )
  )

(deftest TestGetCoordinatesOfTheHoleOfAGivenPolygon
  (testing "Verify the coordinates of the hole"
    (is (= [[[18.0 55.0] [33.0 55.0] [32.0 51.0] [16.0 51.0] [18.0 55.0]]]
           (getHoleCoordinates squaredPolygonWithSquaredHole))))
  )

