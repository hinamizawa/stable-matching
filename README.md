# stable-matching

This is an Clojure implantation of the [Gale–Shapley algorithm](https://en.wikipedia.org/wiki/Gale%E2%80%93Shapley_algorithm)

## Usage

Here goes an example:

```clojure
(ns stable-matching.core-test
  (:require [stable-matching.core :refer [stable-match]]))

(def women-preference {:charlotte [:bingley :darcy :collins :wockham]
                       :elizabeth [:wickham :darcy :bingley :collins]
                       :jane      [:bingley :wickham :darcy :collins]
                       :lydia     [:bingley :wickham :darcy :collins]})

(def men-preference {:bingley [:jane :elizabeth :lydia :charlotte]
                     :jane    [:jane :elizabeth :lydia :charlotte]
                     :darcy   [:elizabeth :jane :charlotte :lydia]
                     :wickham [:lydia :jane :elizabeth :charlotte]})

(stable-match women-preference men-preference)
=> 1
```

## License

Copyright © 2020 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
