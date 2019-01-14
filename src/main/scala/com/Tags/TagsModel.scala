package com.Tags

import java.sql.Timestamp
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import slick.sql.SqlProfile.ColumnOption.SqlType

class TagsModel(tag: Tag) extends
  Table[(Int, String, Timestamp)](tag, "tags") {

  // This is the primary key column:
  def id: Rep[Int] = column[Int]("id", O.PrimaryKey)

  def name: Rep[String] = column[String]("tag")

  def created_at = column[Timestamp]("created_at", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

  // Every table needs a * projection with the same type as the table's type parameter
  def * : ProvenShape[(Int, String, Timestamp)] = (id, name, created_at)
}