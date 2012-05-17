package com.blendlabsinc.facebook

import dispatch._
import net.liftweb.json.JsonParser
import net.liftweb.json.JsonAST._
import net.liftweb.json.Serialization
import net.liftweb.json.FullTypeHints
import dispatch.liftjson.Js._
import net.liftweb.json.JsonAST._

import com.blendlabsinc.models._

object Graph {
  private val accessTokenEnvName = "FACEBOOK_ACCESS_TOKEN"
  private def accessToken = {
    val a = java.lang.System.getenv(accessTokenEnvName)
    if (a == null) {
      throw new Exception(
        "The " + accessTokenEnvName +" environment variable is not set. To get your Facebook access token, go to " +
        "https://developers.facebook.com/tools/explorer?method=GET&path=me " +
        "and use the value in the \"Access Token:\" field. Then run " +
        "`export " + accessTokenEnvName + "=\"your access token\"` " +
        "in your shell."
      )
    } else {
      a
    }
  }

  private implicit val formats = Serialization.formats(FullTypeHints(List(classOf[Person])))

  def getFriends(id: String = "me"): List[Person] = {
    val url = dispatch.url("https://graph.facebook.com/" + id + "/friends") <<? Map("access_token" -> accessToken, "limit" -> "3")
    dispatch.Http(url ># { json =>
      (json \ "data").extract[List[Person]]
    })
  }
}
