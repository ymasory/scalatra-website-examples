package com.constructiveproof.example.models

/**
 * Created with IntelliJ IDEA.
 * User: dave
 * Date: 12/10/13
 * Time: 16:48
 * To change this template use File | Settings | File Templates.
 */
case class User(id:String)               {

  def forgetMe = {

  }

}


object User {
  def validateRememberToken(token: String): Option[User] = {
    //    val usr = User.findAll("rememberMe" -> token)
    //    Some(usr.first)
    Some(User("foo"))
  }
}