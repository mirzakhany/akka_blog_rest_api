package com.blog

import java.sql.Timestamp

package object Category {

  final case class CategoryObject(id: Int, Name: String,Slug:String, ParentId:Int, CreatedAt: Timestamp, UpdatedAt:Timestamp) {
    require(id != 0 , "id.empty")
    require(Name.nonEmpty, "title.empty")
    require(Slug.nonEmpty, "slug.empty")
    require(ParentId >= 0, "parentId.empty")
    require(CreatedAt == null, "created_at.empty")
    require(UpdatedAt == null, "updated_at.empty")
  }
}
