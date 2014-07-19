package org.scalatra.example.swagger

import org.scalatra.ScalatraServlet
import org.scalatra.swagger.{JacksonSwaggerBase, Swagger}


class FlowersSwagger(implicit val swagger: Swagger) extends ScalatraServlet with JacksonSwaggerBase
