package com.constructiveproof.example


import com.constructiveproof.example.{ScentryauthdemoStack}
import com.constructiveproof.example.auth.AuthenticationSupport

class SessionsController extends ScentryauthdemoStack with AuthenticationSupport {

  before("/new") {
    logger.info("SessionsController: checking whether to run RememberMeStrategy: " + !isAuthenticated)
    if(!isAuthenticated) {
      scentry.authenticate("RememberMe")
    }
  }

  get("/new") {
    contentType="text/html"
    ssp("/sessions/new")
  }

  post("/") {
    scentry.authenticate()

    if (isAuthenticated) {
      redirect("/")
    }else{
      redirect("/sessions/new")
    }
  }

  get("/logout") {
    scentry.logout()
    redirect("/")
  }

}