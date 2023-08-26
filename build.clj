(ns build
  (:refer-clojure :exclude [test])
  (:require
   [clojure.java.shell :as sh]
   [clojure.tools.build.api :as b]
   [deps-deploy.deps-deploy :as deploy]))

(def lib 'org.clojars.afrey/html-server-live-reload-template)

(defn version
  []
  (str (b/git-count-revs nil)
       "."
       (re-find #"\w+" (:out (sh/sh "git" "rev-parse" "--short" "HEAD")))))

(def -version (version))

(def class-dir "target/classes")
(def basis (b/create-basis {:project "deps.edn"}))
(def jar-file (format "target/%s-%s.jar" (name lib) -version))

(defn clean [_]
  (b/delete {:path "target"}))

(defn jar [_]
  (clean nil)
  (b/write-pom {:class-dir class-dir
                :lib lib
                :version -version
                :basis basis
                :src-dirs ["src"]})
  (b/copy-dir {:src-dirs ["src" "resources"]
               :target-dir class-dir})
  (b/jar {:class-dir class-dir
          :jar-file jar-file}))

(defn deploy [_]
  (deploy/deploy {:artifact       jar-file
                  :installer      :local
                  :pom-file       (str class-dir "/META-INF/maven/" lib "/pom.xml")
                  :sign-releases? false}))

(comment
  (jar nil)
  (deploy nil)
  )
