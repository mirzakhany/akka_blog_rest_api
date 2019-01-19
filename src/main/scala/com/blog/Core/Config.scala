package com.blog.Core

import pureconfig.loadConfig

case class Config(
 ENV: String,
 timezone: String,
 language: String,
 secretKey: String,
 http: HttpConfig,
 database: DatabaseConfig,
)

object Config {
  def load(): Config =
    loadConfig[Config] match {
      case Right(config) => config
      case Left(error) =>
        throw new RuntimeException("Cannot read config file, errors:\n" + error.toList.mkString("\n"))
    }
}

protected case class HttpConfig(host: String, port: Int)
protected case class DatabaseConfig(jdbcUrl: String, username: String, password: String)
