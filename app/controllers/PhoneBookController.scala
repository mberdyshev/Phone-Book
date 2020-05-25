package controllers

import java.util.UUID.randomUUID
import javax.inject._
import play.api.libs.json._
import play.api.mvc._

import models._

@Singleton
class PhoneBookController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  private var phoneBook = Map[String, PhoneBookEntry]()

  def index: Action[AnyContent] = Action {
    Redirect("/phones")
  }

  def addPhone(): Action[JsValue] = Action(parse.json) { implicit request =>
    val entry = request.body.as[PhoneBookEntry]
    val id = randomUUID().toString
    phoneBook += id -> entry
    Ok(id)
  }

  def getPhones(phone: String, name: String): Action[AnyContent] = Action {
    val matches = phoneBook.filter(e => e._2.name.contains(name) && e._2.phone.contains(phone))
    Ok(Json.toJson(matches))
  }

  def editEntry(id: String): Action[JsValue] = Action(parse.json) { implicit request =>
    val entry = request.body.as[PhoneBookEntry]
    phoneBook += id -> entry
    Ok(Json.toJson(entry))
  }

  def deleteEntry(id: String): Action[AnyContent] = Action {
    val entry = phoneBook(id)
    phoneBook -= id
    Ok(Json.toJson(entry))
  }
}
