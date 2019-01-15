package com.blog.Core.Database

import com.github.tminglei.slickpg._
import com.github.tminglei.slickpg.json.PgJsonExtensions
import com.zaxxer.hikari.{ HikariConfig, HikariDataSource }


class DatabaseConnector(jdbcUrl: String, dbUser: String, dbPassword: String) extends PostgresProfiler {

  private val hikariDataSource = {
    val hikariConfig = new HikariConfig()
    hikariConfig.setJdbcUrl(jdbcUrl)
    hikariConfig.setUsername(dbUser)
    hikariConfig.setPassword(dbPassword)

    new HikariDataSource(hikariConfig)
  }

  import api._

  val db: backend.DatabaseDef = Database.forDataSource(hikariDataSource, None)
  db.createSession()

  def closeDB(): Unit = {
    logger.info("closing db connections")
    db.close()
  }

}

trait PostgresProfiler
  extends ExPostgresProfile
    with PgArraySupport
    with PgRangeSupport
    with PgDateSupport
    with PgDate2Support
    with PgSearchSupport
    with PgSprayJsonSupport
    with PgEnumSupport {

  override val pgjson = "jsonb"

  override val api: API = new API {}

  trait API
    extends super.API
      with ArrayImplicits
      with DateTimeImplicits
      with SimpleDateTimeImplicits
      with SearchImplicits
      with SearchAssistants
      with JsonImplicits

}

object PostgresProfiler extends PostgresProfiler with PgJsonExtensions