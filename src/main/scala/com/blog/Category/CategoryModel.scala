package com.blog.Category

import java.sql.Timestamp

import com.blog.Core.Database.DatabaseConnector
import slick.lifted.ProvenShape
import slick.sql.SqlProfile.ColumnOption.SqlType

private[Category] trait CategoryModel {

  protected val databaseConnector: DatabaseConnector

  import databaseConnector.profile.api._

  class Categories(tag: Tag) extends
    Table[(Int, String, String, Int, Timestamp, Timestamp)](tag, "categories") {

    def id: Rep[Int] = column[Int]("id", O.PrimaryKey)

    def created_at = column[Timestamp]("created_at", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

    def updated_at = column[Timestamp]("updated_at", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

    def name: Rep[String] = column[String]("name")

    def slug: Rep[String] = column[String]("slug")

    def parentId: Rep[Int] = column[Int]("parent_id")

    def * : ProvenShape[(Int, String, String, Int, Timestamp, Timestamp)] =
      (id, name, slug, parentId, created_at, updated_at)

    protected val categories = TableQuery[Categories]
  }

}