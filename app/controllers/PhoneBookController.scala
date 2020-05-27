package controllers

import scala.concurrent._
import javax.inject._
import play.api.libs.json._
import play.api.mvc._

import models._

@Singleton
class PhoneBookController @Inject()(repo: PhoneBookRepo, cc: ControllerComponents)(implicit ec: ExecutionContext)
  extends AbstractController(cc) {

  private var phoneBook = Map[String, PhoneBookEntry]()

  def index: Action[AnyContent] = Action {
    Redirect("/phones")
  }

  def addPhone(): Action[JsValue] = Action.async(parse.json) { implicit request =>
    val entry = request.body.as[PhoneBookEntry]
    repo.create(entry).map { id =>
      Created(id.toString)
    }
  }

  def getPhones(phone: String, name: String): Action[AnyContent] = Action.async {
    repo.get(phone, name).map { entries =>
      Ok(Json.toJson(entries))
    }
  }

  def editEntry(id: Long): Action[JsValue] = Action.async(parse.json) { implicit request =>
    val entry = request.body.as[PhoneBookEntry]
    repo.edit(id, entry).map { entry =>
      Created(Json.toJson(entry))
    }
  }

  def deleteEntry(id: Long): Action[AnyContent] = Action.async {
    repo.delete(id).map { _ => NoContent }
  }
}
