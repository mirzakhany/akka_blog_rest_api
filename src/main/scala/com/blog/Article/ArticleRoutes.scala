package com.blog.Article
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.blog.Core.Http.JsonSupports

import scala.concurrent.ExecutionContext

class ArticleRoutes(articleService: ArticleService)(implicit executionContext: ExecutionContext) extends JsonSupports {

  import StatusCodes._
  import articleService._

  val route: Route = pathPrefix("articles") {
    pathEndOrSingleSlash {
      get {
        complete(getArticles)
      }
    } ~
      pathPrefix(Segment) { id =>
        pathEndOrSingleSlash {
          get {
            complete(getArticle(id.toInt).map {
              case Some(article) =>
                OK -> Some(article)
              case None =>
                BadRequest -> None.asInstanceOf[Option[ArticleObject]]

            })
          } ~
            post {
              entity(as[ArticleUpdate]) { articleUpdate =>
                complete(updateArticle(id.toInt, articleUpdate).map {
                  case Some(article) =>
                    OK -> Some(article)
                  case None =>
                    BadRequest -> None
                })
              }
            }
        }
      }
  }
}
