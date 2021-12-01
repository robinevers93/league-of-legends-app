package lolServices

import lolclient.LOLConfig
import lolclient.models.`match`.Match
import lolclient.services.auth.AuthToken
import lolclient.services.lol.{LOLService, MatchService}
import lolclient.syntax.LOLSyntax._
import zio.RIO
import zio.blocking.Blocking

class LOLMatchService(config: LOLConfig, token: AuthToken) {

  val matchService: MatchService = LOLService.withConfig(config, token).matchService

  def getMatch(matchId: String): RIO[Blocking, Match] = matchService.getMatch(matchId).getOrFail
  def listMatches(puuid: String, count: Int): RIO[Blocking, List[String]] = matchService.listMatches(puuid, count).getOrFail
}
