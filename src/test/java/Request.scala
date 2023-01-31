import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Request {
  val account =exec(
    http("account")
    .get("/Account/v1/User")
    .check(status.is(200))
  )
}
