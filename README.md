# Clojure Live Reload HTML Server Template

Automatic reload for Clojure server rendered HTML.

This template will generate a minimal starting point for a clojure server that
serves HTML (created from Hiccup) and CSS files and will have your browser
perform a Figwheel/Shadow-like live reload without the need to tab over to your
browser when your clojure code is re-evaluated or your CSS files are saved.

## Usage

This is a template project for use with [deps-new](https://github.com/seancorfield/deps-new).
As originally generated, it will produce a new library project when run:

    $ clojure -Sdeps '{:deps {net.clojars.mycorp/template.interface.html {:local/root "."}}}' -Tnew create :template mycorp/template.interface.html :name myusername/mycoollib

Assuming you have installed `deps-new` as your `new` "tool" via:

```bash
clojure -Ttools install io.github.seancorfield/deps-new '{:git/tag "v0.4.13"}' :as new
```

> Note: once the template has been published (to a public git repo), the invocation will be the same, except the `:local/root` dependency will be replaced by a git or Maven-like coordinate.

## License

Copyright © 2023 Adam Frey

Distributed under the Eclipse Public License version 1.0.
