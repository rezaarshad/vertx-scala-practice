import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.ext.web.{Route, Router}

import scala.concurrent.Future

object MainVerticle {
  val routePath = "/api/hi"
  val response = "welcome"
}


class MainVerticle extends ScalaVerticle {
  import MainVerticle._

  override def startFuture(): Future[_] = {
    val router = Router.router(vertx)

    val route: Route = router       // used in requestHandler below
      .get(routePath)
      .handler(_.response.end(response))

    vertx
      .createHttpServer()
      .requestHandler(router.accept(_))
      .listenFuture(8088, "0.0.0.0")
      .map { httpServer =>
        println(s"""httpServer connected on port: ${ httpServer.actualPort }
                   |""".stripMargin)
      }
  }
}
