# ScentryAuthDemo #

## Build & Run ##

```sh
$ cd ScentryAuthDemo
$ ./sbt
> container:start
> browse
```

If `browse` doesn't launch your browser, manually open [http://localhost:8080/](http://localhost:8080/) in your browser.

NOTE: For anyone who runs across this in the repo, this example application is currently not fully working.

Although the UserPasswordStrategy basically works, the `isValid` for every strategy always evaluates to true - I think
I've structured something in the wrong way and am waiting to hear back on how to fix it.

Don't use this as the basis of any production code until we fix up this example.

