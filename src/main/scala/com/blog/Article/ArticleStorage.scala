package com.blog.Article

import com.blog.Core.Database.DatabaseConnector

import scala.concurrent.{ExecutionContext, Future}


sealed trait ArticleStorage {

  def getArticles: Future[Seq[ArticleObject]]

  def getArticle(id: Int): Future[Option[ArticleObject]]

  def saveArticle(article: ArticleObject): Future[ArticleObject]

}

class JdbcArticleStorage(
                          val databaseConnector: DatabaseConnector
                        )(implicit executionContext: ExecutionContext)
  extends ArticleModel
    with ArticleStorage {

  import databaseConnector._
  import databaseConnector.profile.api._

  def getArticles: Future[Seq[ArticleObject]] = db.run(articles.result)

  def getArticle(id: Int): Future[Option[ArticleObject]] = db.run(articles.filter(_.id === id) .result.headOption)

  def saveArticle(article: ArticleObject): Future[ArticleObject] =
    db.run(articles.insertOrUpdate(article)).map(_ => article)

}