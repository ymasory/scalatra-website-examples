package org.scalatra.example.swagger.data

import org.scalatra.example.swagger.models._

object FlowerData {

  /**
   * Some fake flowers data so we can simulate retrievals.
   */
  var all = List(
      Flower("yellow-tulip", "Yellow Tulip"),
      Flower("red-rose", "Red Rose"),
      Flower("black-rose", "Black Rose"))

}
