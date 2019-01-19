package com.blog.Core.Modules

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.blog.Article.{ArticleRoutes, ArticleService, ArticleStorage, JdbcArticleStorage}
import com.blog.Core.Config
import com.blog.Core.Database.{DatabaseConnector, DatabaseMigrationManager}
import com.blog.Core.Http.HttpRoute
import com.softwaremill.macwire._

import scala.concurrent.ExecutionContext

class Production extends EndpointModule

trait ConfigModule {
  lazy val config: Config = Config.load()
}

trait ScalaJDBCModule extends ConfigModule {

  lazy val databaseMigrationManager = new DatabaseMigrationManager(
    config.database.jdbcUrl,
    config.database.username,
    config.database.password
  )

  lazy val databaseConnector =
    new DatabaseConnector(
      config.database.jdbcUrl,
      config.database.username,
      config.database.password
    )
}

trait RepositoryModule extends ScalaJDBCModule with AkkaModule {
  lazy val articleStorage: ArticleStorage = wire[JdbcArticleStorage]
}

trait ServiceModule extends RepositoryModule with AkkaModule {
  lazy val articleService: ArticleService = wire[ArticleService]
}

trait AkkaModule {
  implicit val actorSystem: ActorSystem = ActorSystem("blog-actor-system")
  implicit val executor: ExecutionContext = actorSystem.dispatcher
  implicit val materializer: ActorMaterializer = ActorMaterializer()
}


trait EndpointModule extends AkkaModule with ServiceModule {
  lazy val articleRoutes: ArticleRoutes = wire[ArticleRoutes]
  lazy val httpRoute: HttpRoute = wire[HttpRoute]
}