package Requests

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

import java.util.UUID

object RequestPost {
  val postUser: ChainBuilder = exec(
    session => session.set("userName", UUID.randomUUID().toString)
  ).exec(
    session => session.set("password", UUID.randomUUID().toString)
  ).exec(
    http("new user")
      .post("/Account/v1/User")
      .body(ElFileBody("body.json")).asJson
      .check(status.is(200))
  )
}
