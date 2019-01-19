package com.blog.Article

import java.sql.Timestamp
import com.blog.Core.Database.{DatabaseConnector, SlickSupport}
import slick.sql.SqlProfile.ColumnOption.SqlType

private[Article] trait ArticleModel extends SlickSupport{

  protected val databaseConnector: DatabaseConnector

  import databaseConnector.profile.api._

  class Articles(tag: Tag) extends Table[ArticleObject](tag, "articles") {

    def id: Rep[Int] = column[Int]("id", O.PrimaryKey)

    def created_at: Rep[Timestamp] = column[Timestamp]("created_at", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

    def updated_at: Rep[Timestamp] = column[Timestamp]("updated_at", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

    def title: Rep[String] = column[String]("title")

    def author: Rep[Int] = column[Int]("author_id")

    def body: Rep[String] = column[String]("body")

    def status: Rep[String] = column[String]("status")

    def * = (id, title, author, body, status, created_at, updated_at) <> ((ArticleObject.apply _).tupled, ArticleObject.unapply)
  }

  protected val articles = TableQuery[Articles]
}
