package org.scalatra.example

import org.slf4j.LoggerFactory

class SessionsController extends FormsAuthDemoStack with AuthenticationSupport {

  val logger = LoggerFactory.getLogger(getClass)


  get("/new") {
    if (isAuthenticated) redirect("/")

    contentType="text/html"
    ssp("/sessions/new", "authenticated" -> isAuthenticated)
  }

  post("/") {
    logger.info(("Starting authentication"))
    scentry.authenticate()

    if (isAuthenticated) {
      redirect("/")
    }else{
      redirect("/sessions/new")
    }
  }

}
