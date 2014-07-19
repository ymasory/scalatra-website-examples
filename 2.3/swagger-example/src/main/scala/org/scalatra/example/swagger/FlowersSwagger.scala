package org.scalatra.example.swagger

import org.scalatra.swagger.{NativeSwaggerBase, Swagger, ApiInfo}

import org.scalatra.ScalatraServlet
import org.json4s.{DefaultFormats, Formats}


class ResourcesApp(implicit val swagger: Swagger) extends ScalatraServlet with NativeSwaggerBase {
}

object FlowersApiInfo extends ApiInfo(
    "The Flowershop API",
    "Docs for the Flowers API",
    "http://scalatra.org",
    "apiteam@scalatra.org",
    "MIT",
    "http://opensource.org/licenses/MIT")
class FlowersSwagger extends Swagger(Swagger.SpecVersion, "1", FlowersApiInfo)
