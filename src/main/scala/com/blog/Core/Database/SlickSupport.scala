package com.blog.Core.Database

import java.sql.{Date, Timestamp}
import java.time.{LocalDate, LocalDateTime}

import com.blog.Core.Http.JsonSupports

trait SlickSupport extends PostgresProfiler with JsonSupports {

  import api._

  implicit val timestamplocalDateTimeMapper: BaseColumnType[LocalDateTime] =
    MappedColumnType.base[LocalDateTime, Timestamp](
      { localDateTime =>
        Timestamp.valueOf(localDateTime)
      }, { timeStamp =>
        timeStamp.toLocalDateTime
      }
    )

//  implicit val timestampMapper: BaseColumnType[Timestamp] =
//    MappedColumnType.base[Timestamp, String](_.toString(), Timestamp.valueOf)

  implicit val dateMapper: BaseColumnType[Date] = MappedColumnType.base[Date, String](_.toString, Date.valueOf)
  implicit val localDateMapper: BaseColumnType[LocalDate] =
    MappedColumnType.base[LocalDate, String](_.toString, LocalDate.parse(_, shortDateFormatter))

}
