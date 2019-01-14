package com.Auth

import java.sql.Timestamp
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import slick.sql.SqlProfile.ColumnOption.SqlType

class UserModel(tag: Tag) extends
  Table[(Int, String, String, String, Timestamp, Timestamp)](tag, "Users") {

  // This is the primary key column:
  def id: Rep[Int] = column[Int]("id", O.PrimaryKey)

  def username: Rep[String] = column[String]("username")

  def password: Rep[String] = column[String]("password")

  def full_name: Rep[String] = column[String]("full_name")

  def created_at = column[Timestamp]("created_at", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

  def updated_at = column[Timestamp]("updated_at", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

  // Every table needs a * projection with the same type as the table's type parameter
  def * : ProvenShape[(Int, String, String, String, Timestamp, Timestamp)] =
    (id, username, password, full_name, created_at, updated_at)
}