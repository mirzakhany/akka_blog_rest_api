package com.blog.Core.Http

import java.sql.Timestamp
import java.time.format.DateTimeFormatter
import java.time.{LocalDate, LocalDateTime}
import java.util.UUID

import com.blog.Article.{ArticleObject, ArticleUpdate}
import spray.json.{DefaultJsonProtocol, JsString, JsValue, RootJsonFormat, _}

trait JsonSupports extends DefaultJsonProtocol {

  val shortDateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
  val shortDateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

  implicit val localDateTimeFormat: RootJsonFormat[LocalDateTime] = new RootJsonFormat[LocalDateTime] {
    def write(x: LocalDateTime) = JsString(x.format(shortDateTimeFormatter))

    def read(value: JsValue): LocalDateTime = value match {
      case JsString(x) if x.contains("T") =>
        LocalDateTime.parse(x, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
      case JsString(s) => LocalDateTime.parse(s, shortDateTimeFormatter)
      case x => throw new RuntimeException(s"Unexpected type ${x.getClass.getName} when trying to parse LocalDateTime")
    }
  }

  implicit val localDateFormat: RootJsonFormat[LocalDate] = new RootJsonFormat[LocalDate] {
    private val iso_date = DateTimeFormatter.ISO_DATE

    def write(x: LocalDate) = JsString(iso_date.format(x))

    def read(value: JsValue): LocalDate = value match {
      case JsString(x) => LocalDate.parse(x, iso_date)
      case x => throw new RuntimeException(s"Unexpected type ${x.getClass.getName} when trying to parse LocalDateTime")
    }
  }

  implicit val TimestampFormat: RootJsonFormat[Timestamp] = new RootJsonFormat[Timestamp] {
    private val iso_date = DateTimeFormatter.ISO_DATE

    def write(x: Timestamp) = JsString(iso_date.format(x.toInstant))

    def read(value: JsValue): Timestamp = value match {
      case JsString(x) => Timestamp.valueOf(x)
      case x => throw new RuntimeException(s"Unexpected type ${x.getClass.getName} when trying to parse LocalDateTime")
    }
  }

  implicit val articleFormat: RootJsonFormat[ArticleObject]       = jsonFormat7(ArticleObject)
  implicit val articleUpdateFormat: RootJsonFormat[ArticleUpdate] = jsonFormat4(ArticleUpdate)


  implicit val uuidJsonFormat: RootJsonFormat[UUID] = new RootJsonFormat[UUID] {
    def write(uuid: UUID): JsValue =
      uuid.toString.toJson

    def read(value: JsValue): UUID =
      UUID.fromString(value.prettyPrint)

  }
}

object JsonSupportsImpl extends JsonSupports
