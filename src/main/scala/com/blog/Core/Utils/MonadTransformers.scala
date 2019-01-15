package com.blog.Core.Utils

import scala.concurrent.{ExecutionContext, Future}

/**
  * Monad transformers its classes that extends default monad's like Future or Option.
  * They handle situation when monad's contain each other, its helping to reduce amount of boilerplate code.
  * For example in that project there are lots of Future[Option[T]] classes that must be handled somehow.
  */
object MonadTransformers {

  val t: Option[Int]      = Option(1)
  def func: Int => String = _.toString

  def func2: Int => Option[String] = i => Option(i.toString)

  val k: Option[String] = t.map(func)

  val s: Option[String] = t.flatMap(func2)

  implicit class FutureOptionMonadTransformer[A](t: Future[Option[A]])(
      implicit val executionContext: ExecutionContext
  ) {

    def mapT[B](f: A => B): Future[Option[B]] =
      t.map(_.map(f))

    def filterT(f: A => Boolean): Future[Option[A]] =
      t.map {
        case Some(data) if f(data) =>
          Some(data)
        case _ =>
          None
      }

    def flatMapT[B](f: A => Future[Option[B]]): Future[Option[B]] =
      t.flatMap {
        case Some(data) =>
          f(data)
        case None =>
          Future.successful(None)
      }

    def flatMapTOuter[B](f: A => Future[B]): Future[Option[B]] =
      t.flatMap {
        case Some(data) =>
          f(data).map(Some.apply)
        case None =>
          Future.successful(None)
      }

    def flatMapTInner[B](f: A => Option[B]): Future[Option[B]] =
      t.map(_.flatMap(f))

  }

}
