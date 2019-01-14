package com.Article

import java.sql.Timestamp
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import slick.sql.SqlProfile.ColumnOption.SqlType

class ArticleModel(tag: Tag) extends
  Table[(Int, String, Int, String, String, Timestamp, Timestamp)](tag, "Articles") {

  // This is the primary key column:
  def id: Rep[Int] = column[Int]("id", O.PrimaryKey)

  def title: Rep[String] = column[String]("title")

  def author: Rep[Int] = column[Int]("author")

  def body: Rep[String] = column[String]("body")

  def status: Rep[String] = column[String]("status")

  def created_at = column[Timestamp]("created_at", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

  def updated_at = column[Timestamp]("updated_at", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

  // Every table needs a * projection with the same type as the table's type parameter
  def * : ProvenShape[(Int, String, Int, String, String, Timestamp, Timestamp)] =
    (id, title, author, body, status, created_at, updated_at)
}