package lolServices

import lolclient.LOLConfig
import lolclient.models.summoner.Summoner
import lolclient.services.auth.AuthToken
import lolclient.services.lol.{LOLService, SummonerService}
import lolclient.syntax.LOLSyntax._
import zio.RIO
import zio.blocking.Blocking

class LOLSummonerService(config: LOLConfig, token: AuthToken) {

  val summonerService: SummonerService = LOLService.withConfig(config, token).summonerService

  def getSummoner(name: String): RIO[Blocking, Summoner] = summonerService.getSummoner(name).getOrFail
}
