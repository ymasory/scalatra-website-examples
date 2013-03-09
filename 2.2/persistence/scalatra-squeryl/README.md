Scalatra Squeryl Example
========================

This is a quick demonstration of [Squeryl](http://squeryl.org) integration with [Scalatra](http://scalatra.org). 

It demonstrates how to use Scalatra with an object-relational mapper, integrate C3P0 connection pooling, and bootstrap the database connections into existence. 

It uses Scalatra 2.2's [ScalatraBootstrap](http://scalatra.org/2.2/guides/deployment/configuration.html) class to kick off the database connection.

There's a scalatest example showing the use of before and after blocks to configure the database when testing.

### Create the database

 Whether you're testing or running the application in a browser, you'll need a blank MySQL schema called `squeryltryout`. You can change database creds in `org.scalatra.example.init.DatabaseInit`.

### Run the application

To try it out, `git clone` the code and:

1. Launch [SBT](http://www.scala-sbt.org/).

```
./sbt
```

2. Run Jetty

```
container:start
```

3. Go to [http://localhost:8080/articles/create-db](http://localhost:8080/articles/create-db). This will create the database schema.

4. You'll be redirected to an article creation form.

5. Learn more at http://www.scalatra.org/

6. Happy hacking!
