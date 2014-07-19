package org.scalatra.example.swagger

import org.scalatra.swagger.{NativeSwaggerBase, Swagger}

import org.scalatra.ScalatraServlet
import org.json4s.{DefaultFormats, Formats}


class FlowersSwagger(implicit val swagger: Swagger) extends ScalatraServlet with NativeSwaggerBase
