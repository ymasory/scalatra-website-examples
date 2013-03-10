# scalatra-cloudbees #

Corresponds to the guide at [http://scalatra.org/2.2/guides/deployment/cloudbees.html](http://scalatra.org/2.2/guides/deployment/cloudbees.html).

## Build & Run ##

```sh
$ git clone https://github.com/scalatra/scalatra-website-examples.git
$ cd scalatra-website-examples/2.2/deployment/scalatra-cloudbees
$ chmod +x sbt
$ ./sbt
> container:start
> browse
```

If `browse` doesn't launch your browser, manually open [http://localhost:8080/](http://localhost:8080/) in your browser.
