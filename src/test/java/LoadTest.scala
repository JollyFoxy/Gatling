import Request._
import io.gatling.http.Predef._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration._
import scala.language.postfixOps
class LoadTest extends Simulation{
  val httpConf: HttpProtocolBuilder =http.baseUrl("https://demoqa.com/swagger/#/")

  val snc: ScenarioBuilder = scenario("First")randomSwitch(
    (25,account),
    (25,getUser),
    (25,deleteUser),
    (25,postUser)
  )

  setUp(
    snc.inject(
      rampConcurrentUsers(10).to(1000).during(20 seconds)
      /*nothingFor(4), // 1
      atOnceUsers(10), // 2
      rampUsers(10).during(5), // 3
      constantUsersPerSec(20).during(15), // 4
      constantUsersPerSec(20).during(15).randomized, // 5
      rampUsersPerSec(10).to(20).during(10.minutes), // 6
      rampUsersPerSec(10).to(20).during(10.minutes).randomized, // 7
      stressPeakUsers(1000).during(20)*/
    )
    .protocols(httpConf))
}
