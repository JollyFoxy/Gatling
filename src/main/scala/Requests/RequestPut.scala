package Requests

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

import java.util.UUID

object RequestPut {
  val putRequest: ChainBuilder =
    exec(
      session => session.set("UUID", UUID.randomUUID().toString)
    ).exec(
      session => session.set("userId", UUID.randomUUID().toString)
    ).exec(
      session => session.set("isbn", UUID.randomUUID().toString)
    ).exec(
    http("put")
      .post("/BookStore/v1/Books/${UUID}")
      .body(ElFileBody("PutObject.json")).asJson
      .check(status.is(200))
  )
}
