package controllers

import com.google.inject.Inject
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import play.api.routing.JavaScriptReverseRouter

class JsRoutes @Inject() (cc: ControllerComponents) extends AbstractController(cc) {
  def jsRoutes: Action[AnyContent] = Action { implicit request =>
    Ok(
      JavaScriptReverseRouter("jsRoutes")(
        routes.javascript.HomeController.getMain,
      )).as("text/javascript")
  }

}