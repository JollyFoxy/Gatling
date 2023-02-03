package Requests

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

import java.util.UUID

object RequestDelete {
  val deleteUser: ChainBuilder = exec(
    session => session.set("UUID", UUID.randomUUID().toString)
  ).exec(
    http("delete")
      .delete("/Account/v1/User/${UUID}")
      .check(status.is(200))
  )
}
