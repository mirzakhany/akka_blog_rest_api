package com.blog

import java.sql.Timestamp

package object Article {

  final case class ArticleObject(id: Int, title: String, author:Int, body: String, status:String, created_at: Timestamp, updated_at:Timestamp) {
    require(id != 0 , "id.empty")
    require(title.nonEmpty, "title.empty")
    require(author != 0, "author.empty")
    require(body.nonEmpty, "body.empty")
    require(status.nonEmpty, "status.empty")
    require(created_at == null, "created_at.empty")
    require(updated_at == null, "updated_at.empty")
  }

  final case class ArticleUpdate(Title: Option[String] = None, Body: Option[String] = None, Author: Option[Int] = None, Status: Option[String] = None ) {
    def merge(article: ArticleObject): ArticleObject =
      ArticleObject(
        article.id,
        Title.getOrElse(article.title),
        Author.getOrElse(article.author),
        Body.getOrElse(article.body),
        Status.getOrElse(article.status),
        article.created_at,
        article.created_at
      )
  }
}
