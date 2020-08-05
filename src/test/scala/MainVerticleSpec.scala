import org.junit.runner.RunWith
import org.scalatest.Matchers
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.junit.JUnitRunner
import scala.concurrent.Promise

@RunWith(classOf[JUnitRunner])
class MainVerticleSpec extends VerticleTesting[MainVerticle] with Matchers with ScalaFutures {
  import MainVerticle._

  "MainVerticle" should s"bind to 8666 and answer with '$response'" in {
    val promise = Promise[String]

    vertx.createHttpClient
      .getNow(8088, "127.0.0.1", routePath,
        httpClientResponse => {
          httpClientResponse.exceptionHandler(promise.failure)
          httpClientResponse.bodyHandler(buffer => promise.success(buffer.toString))
        }
      )

    whenReady(promise.future) {_ shouldBe response }
  }
}
