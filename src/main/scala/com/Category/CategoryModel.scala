package com.Category

import java.sql.Timestamp
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import slick.sql.SqlProfile.ColumnOption.SqlType

class CategoryModel(tag: Tag) extends
  Table[(Int, String, String, Int, Timestamp, Timestamp)](tag, "categories") {

  // This is the primary key column:
  def id: Rep[Int] = column[Int]("id", O.PrimaryKey)

  def name: Rep[String] = column[String]("name")

  def slug: Rep[String] = column[String]("slug")

  def parentId: Rep[Int] = column[Int]("parent_id")

  def created_at = column[Timestamp]("created_at", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

  def updated_at = column[Timestamp]("updated_at", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

  // Every table needs a * projection with the same type as the table's type parameter
  def * : ProvenShape[(Int, String, String, Int, Timestamp, Timestamp)] =
    (id, name, slug, parentId, created_at, updated_at)
}