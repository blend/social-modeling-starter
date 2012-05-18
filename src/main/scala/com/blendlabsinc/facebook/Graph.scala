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

  private implicit val formats = Serialization.formats(FullTypeHints(List(classOf[Person], classOf[Group])))

  private def apiURL(id: String, path: String, limit: Int, fields: String) =
    dispatch.url("https://graph.facebook.com/" + id + "/" + path) <<? Map(
      "fields" -> fields,
      "access_token" -> accessToken,
      "limit" -> limit.toString
    )

  def getFriends(id: String = "me", limit: Int = 20): List[Person] = {
    val url = apiURL(id, "friends", fields = "id,name", limit = limit)
    dispatch.Http(url ># { json =>
      (json \ "data").extract[List[Person]]
    })
  }

  def getGroups(id: String = "me", limit: Int = 50): List[Group] = {
    val url = apiURL(id, "groups", fields = "id,name", limit = limit)
    dispatch.Http(url ># { json =>
      (json \ "data").extract[List[Group]]
    })
  }

  def getFriendsWithGroups(id: String = "me", limitFriends: Int = 20): List[Person] =
    getFriends(id, limit = limitFriends).map { friend =>
      friend.withGroups(getGroups(friend.id))
    }
}
