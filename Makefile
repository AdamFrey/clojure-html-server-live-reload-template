

clean:
	rm -rf target
.PHONY: clean

jar:
	clj -T:build jar
.PHONY: jar

release: export CLOJARS_PASSWORD = $(shell cat ~/.config/clojars/deploy-token)
release: export CLOJARS_USERNAME = afrey
release: clean jar
	clj -T:build deploy
.PHONY: release
