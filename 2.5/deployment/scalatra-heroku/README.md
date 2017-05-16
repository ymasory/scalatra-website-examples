# scalatra-heroku #

Corresponds to the guide at [http://scalatra.org/2.4/guides/deployment/heroku.html](http://scalatra.org/2.4/guides/deployment/heroku.html).

## Build & Run ##

```sh
$ git clone https://github.com/scalatra/scalatra-website-examples.git
$ cd scalatra-website-examples/2.4/deployment/scalatra-heroku
$ chmod +x sbt
$ ./sbt stage
$ ./target/universal/stage/bin/heroku-example
```

Then open [http://localhost:8080](http://localhost:8080).
