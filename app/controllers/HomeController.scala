package controllers

import javax.inject._
import play.api.libs.json._
import play.api.mvc._

@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index = Action {
    val content = "Hello, world!"
    Ok(Json.toJson(content))
  }

}
