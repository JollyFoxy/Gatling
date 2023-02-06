package Requests.Local

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

object RequestHelloWorld {
  val getHelloWorld: ChainBuilder =exec(
    http("getHelloWorld")
      .get("/some/thing")
      .check(status.is(200))
  )
}
