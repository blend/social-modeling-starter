package com.blendlabsinc.facebook

import org.specs2.mutable._

class GraphSpec extends Specification {
  val samplePersonId = "5"

  "Graph.getFriends" should {
    "return my friends if no argument is given" in {
      val firstFriend = Graph.getFriends(limit = 1).head
      firstFriend.name must equalTo("Chris Hughes")
    }
  }

  "Graph.getGroups" should {
    "return groups of a user" in {
      Graph.getGroups(samplePersonId, limit = 1).head.name must contain("I pledge")
    }
  }

  "Graph.getFriendsWithGroups" should {
    "return friends and groups" in {
      val firstFriend = Graph.getFriendsWithGroups(limitFriends = 1).head
      firstFriend.name must equalTo("Chris Hughes")
      firstFriend.groups.head.name must contain("I pledge")
    }
  }
}
