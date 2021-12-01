package controllers

import lolServices.LOLService
import play.api.libs.json.Json
import play.api.mvc._
import zio.Runtime

import javax.inject._
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  val runtime: Runtime[zio.ZEnv] = Runtime.default

  def index: Action[AnyContent] = Action {
    Ok(views.html.app.index("Welcome!"))
  }

  def getMain(summonerName: String): Action[AnyContent] = Action.async { implicit request =>
    runtime.unsafeRunToFuture(LOLService.getMain(summonerName))
      .map(champion => Results.Ok(Json.toJson(champion)))
  }

}