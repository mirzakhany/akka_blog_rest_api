package com.blog.Article

import scala.concurrent.{ExecutionContext, Future}
import com.blog.Core.Utils.MonadTransformers._

class ArticleService(articleStorage: ArticleStorage)(implicit executionContext: ExecutionContext) {

  def getArticles: Future[Seq[ArticleObject]] =
    articleStorage.getArticles

  def getArticle(id: Int): Future[Option[ArticleObject]] =
    articleStorage.getArticle(id)

  def createArticle(profile: ArticleObject): Future[ArticleObject] =
    articleStorage.saveArticle(profile)

  def updateArticle(id: Int, articleUpdate: ArticleUpdate): Future[Option[ArticleObject]] =
    articleStorage
      .getArticle(id)
      .mapT(articleUpdate.merge)
      .flatMapTOuter(articleStorage.saveArticle)
}
