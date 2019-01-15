package com.blog

import java.sql.Timestamp

package object User {

  final case class UserObject(id: Int, UserName: String,Password:String,FullName:String, ParentId:Int, CreatedAt: Timestamp, UpdatedAt:Timestamp) {
    require(id != 0 , "id.empty")
    require(UserName.nonEmpty, "username.empty")
    require(Password.nonEmpty, "password.empty")
    require(FullName.nonEmpty, "full_name.empty")
    require(CreatedAt == null, "created_at.empty")
    require(UpdatedAt == null, "updated_at.empty")
  }
}
