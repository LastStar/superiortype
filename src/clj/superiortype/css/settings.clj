(ns superiortype.css.settings
  (:refer-clojure :exclude [+ - * /])
  (:require [garden.arithmetic :refer [+ - * /]]
            [garden.units :as u]))

(def modular 1.333)
(def default-size (u/rem 1))
(def double-size (u/rem 2))
(def triple-size (u/rem 3))
(def quadruple-size (u/rem 4))

(def smaller-size (/ default-size modular))
(def bigger-size (* default-size modular))
(def big-size (* bigger-size modular))
(def larger-size (* big-size modular))
(def large-size (* larger-size modular))
(def huge-size (* large-size modular))
(def epic-size (* huge-size modular))
(def monster-size (* epic-size modular))

(def short-duration (u/ms 250))
(def default-duration (u/ms 450))
(def long-duration (u/ms 750))
(def longest-duration (u/ms 1000))

