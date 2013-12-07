package org.scalatra.example

case class User(id: String, login: String, password: String) {

	def userIdAsString: String = id.toString

}

object User {

	def login(username: String, password: String): Option[User] = {
		User.findByLogin(username) match {
			case Some(user) => {
		    if(username == user.login && password == user.password){
		      Some(user)
		    }else{
		      None
		    }
		  }

		  case None => None	
			}
	  }

	def find(id: String) = {
		User.all find (_.id == id) match {
      case Some(u) => u
      case None => "foo"
    }		
	}

	def findByLogin(login: String): Option[User] = {
		User.all find (_.login == login) match {
      case Some(u) => Some(u)
      case None => None
    }
	}

	def all = List(User("1", "timmy", "password"), User("2", "samantha", "password"))

}