import Requests.Local.Get._
import Requests.Local.Post._
import Requests.RequestDelete._
import Requests.RequestGet._
import Requests.RequestPost._
import Requests.RequestPut._
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.{aResponse, create, created, jsonResponse, ok, okJson, urlEqualTo}
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration._
import scala.language.postfixOps
class LoadTest extends Simulation{
  val Port = 8080
  val Host = "localhost"
  val wireMockServer = new WireMockServer()

  before {
    wireMockServer.start()
    WireMock.configureFor(Host,Port)
    wireMockServer.stubFor(WireMock.get(urlEqualTo("/some/thing")).willReturn(ok()))
    wireMockServer.stubFor(WireMock.post(urlEqualTo("/some/thing2"))
      .willReturn(jsonResponse(
        "{\"username\": \"{{jsonPath request.body '$.userName'}}\",\"password\": \"{{jsonPath request.body '$.password'}}\"}",
        201)))
  }

  after {
    wireMockServer.stop()
  }


  val httpConf: HttpProtocolBuilder =http.baseUrl("http://localhost:8080")

  val snc: ScenarioBuilder = scenario("First")randomSwitch(
    (25,putRequest),
    (25,getUser),
    (25,deleteUser),
    (25,postUser)
  )
  val scn2: ScenarioBuilder = scenario("Second")
    .exec(getHelloWorld).exec(postLocalUser)

  setUp(
    scn2.inject(constantUsersPerSec(20).during(1 minute))
      .protocols(httpConf))
}
