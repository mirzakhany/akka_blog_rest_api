package com.blog

import java.sql.Timestamp

package object Article {

  final case class ArticleObject(id: Int, Title: String, Author:Int, Body: String, Status:String, CreatedAt: Timestamp, UpdatedAt:Timestamp) {
    require(id != 0 , "id.empty")
    require(Title.nonEmpty, "title.empty")
    require(Author != 0, "author.empty")
    require(Body.nonEmpty, "body.empty")
    require(Status.nonEmpty, "status.empty")
    require(CreatedAt == null, "created_at.empty")
    require(UpdatedAt == null, "updated_at.empty")
  }

  final case class ArticleUpdate(Title: Option[String] = None, Body: Option[String] = None, Author: Option[Int] = None, Status: Option[String] = None ) {
    def merge(article: ArticleObject): ArticleObject =
      ArticleObject(
        article.id,
        Title.getOrElse(article.Title),
        Author.getOrElse(article.Author),
        Body.getOrElse(article.Body),
        Status.getOrElse(article.Status),
        article.CreatedAt,
        article.CreatedAt
      )
  }
}
