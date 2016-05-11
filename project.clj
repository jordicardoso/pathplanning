(defproject field_pathp "0.1.0-SNAPSHOT"
  :description "Dune Field Path Planning"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/data.json "0.2.6"]
                 [cljts "0.2.0"]
                 [com.vividsolutions/jts "1.13"]]
  :main ^:skip-aot field-pathp.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
