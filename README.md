# Clojure Live Reload HTML Server Template

Automatic reload for Clojure server rendered HTML.

This template will generate starting-point code for a clojure server that serves
Hiccup-based HTML and CSS with live reload functionality. Your browser tab will
perform a Figwheel/Shadow-like live reload with no need to change focus over to your
browser when your clojure code is re-evaluated or your CSS files are saved.

## Usage

This is a template project for use with [deps-new](https://github.com/seancorfield/deps-new).

To use it first make sure you have installed `deps-new` as your `new` "tool" via:

```bash
clojure -Ttools install io.github.seancorfield/deps-new '{:git/tag "v0.4.13"}' :as new
```

Then replace `yourcorp/app-name` with your desired application name and run:

``` bash
    $ clojure -Sdeps '{:deps {net.clojars.afrey/html-server-live-reload-template {:mv/version "6.bec52da"}}}' -Tnew create :template afrey/html_server_live_reload_template :name yourcorp/app-name
```


## License

Copyright © 2023 Adam Frey

Distributed under the Eclipse Public License version 1.0.
