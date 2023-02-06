package Requests.Local

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

import scala.util.Random

object RequestWorldPost {


  private val feederName = Iterator.continually {
    Map("userName" -> s"${Random.alphanumeric.take(20).mkString}@foo.com")
  }
  private val feederPass = Iterator.continually {
    Map("password" -> s"${Random.alphanumeric.take(10).mkString}@foo.com")
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
