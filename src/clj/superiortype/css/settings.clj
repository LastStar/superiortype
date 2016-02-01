(ns superiortype.css.settings
  (:refer-clojure :exclude [+ - * /])
  (:require [garden.arithmetic :refer [+ - * /]]
            [garden.units :as u]))

(def modular 1.333)
(def default-size (u/rem 1))

(def default-space default-size)
(def half-space (/ default-space 2))
(def double-space (* default-space 2))
(def triple-space (* default-space 3))
(def quadruple-space (* default-space 4))
(def decuple-space (* default-space 10))

(def short-duration (u/ms 250))
(def default-duration (u/ms 450))
(def long-duration (u/ms 750))
(def longest-duration (u/ms 1000))

