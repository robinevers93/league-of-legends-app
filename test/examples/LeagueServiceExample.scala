package examples

import champStats.LeagueService
import lolclient.syntax.LOLSyntax.RunOps

object LeagueServiceExample extends App {

  {
    println(LeagueService.getMain("Red Flying Robin").unsafeRun)
  }

  {
    println(LeagueService.getMain("dvš").unsafeRun)
  }

}
