Scalatra Squeryl Example
========================

Corresponds to the guide at [http://scalatra.org/2.3/guides/persistence/squeryl.html](http://scalatra.org/2.3/guides/persistence/squeryl.html)

This app demonstrates how to use Scalatra with an object-relational mapper, integrate C3P0 connection pooling, and bootstrap the database connections into existence. 

It uses Scalatra 2.2's [ScalatraBootstrap](http://scalatra.org/2.3/guides/deployment/configuration.html) class to kick off the database connection.

There's a scalatest example showing the use of before and after blocks to configure the database when testing.

### Create the database

 Whether you're testing or running the application in a browser, you'll need a blank MySQL schema called `squeryltryout`. You can change database creds in `org.scalatra.example.init.DatabaseInit`.

## Build & Run ##

```sh
$ git clone https://github.com/scalatra/scalatra-website-examples.git
$ cd scalatra-website-examples/2.3/persistence/scalatra-squeryl
$ chmod +x sbt
$ ./sbt
> container:start
```

Go to [http://localhost:8080/articles/create-db](http://localhost:8080/articles/create-db). This will create the database schema.

You'll be redirected to an article creation form.
