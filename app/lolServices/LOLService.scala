package lolServices

import lolclient.LOLConfig
import lolclient.services.auth.AuthToken
import zio.RIO
import zio.blocking.Blocking

object LOLService {

  private val config = LOLConfig("api.riotgames.com", "europe", "euw1", None)
  private val token = AuthToken("RGAPI-3d26b5e9-9273-499a-9419-89d0c02ebf4e")

  private val summonerService = new LOLSummonerService(config, token)
  private val matchService = new LOLMatchService(config, token)
  private val mainChampionService = new MainChampionService(summonerService, matchService)

  def getMain(summonerName: String): RIO[Blocking, String] = mainChampionService.getMain(summonerName)
}
