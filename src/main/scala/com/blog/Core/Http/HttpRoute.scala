package com.blog.Core.Http

import ch.megard.akka.http.cors.scaladsl.CorsDirectives._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.blog.Article.ArticleRoutes

import scala.concurrent.ExecutionContext

class HttpRoute( articleRoutes: ArticleRoutes,
               )(
                 implicit executionContext: ExecutionContext
               ) {
  val route: Route =
    cors() {
      pathPrefix("v1") {
        articleRoutes.route
      }
    }
}
