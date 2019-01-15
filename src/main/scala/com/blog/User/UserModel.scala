package com.blog.User

import java.sql.Timestamp

import com.blog.Core.Database.DatabaseConnector
import slick.lifted.ProvenShape
import slick.sql.SqlProfile.ColumnOption.SqlType

private[User] trait UserModel {

  protected val databaseConnector: DatabaseConnector

  import databaseConnector.profile.api._

  class Users(tag: Tag) extends
    Table[(Int, String, String, String, Timestamp, Timestamp)](tag, "Users") {

    def id: Rep[Int] = column[Int]("id", O.PrimaryKey)

    def created_at = column[Timestamp]("created_at", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

    def updated_at = column[Timestamp]("updated_at", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

    def username: Rep[String] = column[String]("username")

    def password: Rep[String] = column[String]("password")

    def full_name: Rep[String] = column[String]("full_name")

    def * : ProvenShape[(Int, String, String, String, Timestamp, Timestamp)] =
      (id, username, password, full_name, created_at, updated_at)

    protected val users = TableQuery[Users]
  }

}