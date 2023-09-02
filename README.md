# Clojure Live Reload HTML Server Template

Automatic browser live reload for Clojure server rendered HTML. A
Figwheel/shadow style experience, but without ClojureScript.

This template will generate a starting-point project for a clojure server that
serves Hiccup-based HTML and CSS with live reload functionality. Your browser
tab will reload automatically when you re-evaluate the namespaces with your
hiccup clojure code and when your CSS files are saved.

See [introductory blog post](https://adamfrey.me/blog/post/clj-live-reload-template-release) for more information.

## Usage

This is a template project for use with [deps-new](https://github.com/seancorfield/deps-new).

To use it, first make sure you have installed `deps-new` as your `new` "tool" via:

```bash
clojure -Ttools install io.github.seancorfield/deps-new '{:git/tag "v0.4.13"}' :as new
```

Then replace `yourcorp/app-name` with your desired application name and run:

``` bash
    $ clojure -Sdeps '{:deps {net.clojars.afrey/html-server-live-reload-template {:mv/version "6.bec52da"}}}' -Tnew create :template afrey/html_server_live_reload_template :name yourcorp/app-name
```

Next, open up the `README.md` file in the newly created project for more information on usage.

## Alternatives

- [panas.reload](https://github.com/keychera/panas.reload) appears to aim for a similar goal, uses babashka

## License

Copyright © 2023 Adam Frey

Distributed under the Eclipse Public License version 1.0.
