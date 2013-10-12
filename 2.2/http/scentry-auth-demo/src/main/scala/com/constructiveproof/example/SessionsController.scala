package com.constructiveproof.example


import com.constructiveproof.example.{ScentryauthdemoStack}
import com.constructiveproof.example.auth.AuthenticationSupport

class SessionsController extends ScentryauthdemoStack with AuthenticationSupport {

  before() {
    redirectIfLoggedIn()
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

}