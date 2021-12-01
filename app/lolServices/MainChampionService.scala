package lolServices

import cats.implicits._
import zio.RIO
import zio.blocking.Blocking
import zio.interop.catz._


class MainChampionService(summonerService: LOLSummonerService, matchService: LOLMatchService) {

  private def matchToChamp(matchId: String, puuid: String): RIO[Blocking, String] =
    matchService.getMatch(matchId)
      .map(_.info.participants)
      .map(_.find(participant => participant.player.puuid == puuid))
      .someOrFail(new RuntimeException("participant not found in match"))
      .map(_.champion.championName)

  def getMain(summonerName: String): RIO[Blocking, String] = {
    val champs: RIO[Blocking, List[String]] = for {
      puuid: String <- summonerService.getSummoner(summonerName).map(_.puuid)
      matches: List[String] <- matchService.listMatches(puuid, 20)
      champs: List[String] <- matches.traverse(matchToChamp(_, puuid))
    } yield champs

    champs.map(_.groupBy(identity).view.mapValues(_.size).maxBy(_._2)).map(_._1)
  }

}
