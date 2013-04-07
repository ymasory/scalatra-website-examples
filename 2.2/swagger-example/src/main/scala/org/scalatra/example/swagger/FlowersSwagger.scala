package org.scalatra.example.swagger

import org.scalatra.swagger.{JacksonSwaggerBase, Swagger, SwaggerBase}

import org.scalatra.ScalatraServlet
import org.json4s.{DefaultFormats, Formats}


class ResourcesApp(implicit val swagger: Swagger) extends ScalatraServlet with JacksonSwaggerBase {
  implicit override val jsonFormats: Formats = DefaultFormats
}

class FlowersSwagger extends Swagger("1.0", "1")
