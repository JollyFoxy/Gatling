package Requests.Local

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

import scala.util.Random

object Post {


  private val feederName = Iterator.continually {
    Map("userName" -> s"${Random.nextString(10).mkString}")
  }
  private val feederPass = Iterator.continually {
    Map("password" -> s"${Random.alphanumeric.take(10).mkString}")
  }
    val postLocalUser: ChainBuilder = feed(feederPass)
      .feed(feederName)
      .exec(
        http("new user")
        .post("/some/thing2")
        .body(ElFileBody("body.json")).asJson
        .check(status.is(201))
      )
}
