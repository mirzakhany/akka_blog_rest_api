package com.blog.Tags

import java.sql.Timestamp

import com.blog.Core.Database.DatabaseConnector
import slick.lifted.ProvenShape
import slick.sql.SqlProfile.ColumnOption.SqlType

private[Tags] trait TagsModel {

  protected val databaseConnector: DatabaseConnector

  import databaseConnector.profile.api._

  class Tags(tag: Tag) extends
    Table[(Int, String, Timestamp, Timestamp)](tag, "tags") {
    def name: Rep[String] = column[String]("tag")

    def id: Rep[Int] = column[Int]("id", O.PrimaryKey)

    def created_at = column[Timestamp]("created_at", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

    def updated_at = column[Timestamp]("updated_at", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

    def * : ProvenShape[(Int, String, Timestamp, Timestamp)] = (id, name, created_at, updated_at)

    protected val tags = TableQuery[Tags]
  }
}