package com.blog

import akka.http.scaladsl.Http
import com.blog.Core.Modules.Production

import scala.concurrent.Future

object Boot extends App {

  def startApplication(): Future[Http.ServerBinding] = {

    val modules = new Production

    import modules._

    databaseMigrationManager.migrateDatabaseSchema()

    sys.addShutdownHook(actorSystem.terminate())
    sys.addShutdownHook(databaseConnector.closeDB())

    Http().bindAndHandle(httpRoute.route, config.http.host, config.http.port)

  }
  startApplication()
}
