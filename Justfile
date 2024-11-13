help:
    just --list

run:
    clojure -M -m vibes.main

nrepl:
    clojure -M:nREPL -m nrepl.cmdline

format_check:
    clojure -M:format -m cljfmt.main check src dev

format:
    clojure -M:format -m cljfmt.main fix src dev