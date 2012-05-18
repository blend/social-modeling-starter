package com.blendlabsinc.facebook

import org.specs2.mutable._

class GraphSpec extends Specification {
  val samplePersonId = "220920"

  "Graph.getFriends" should {
    "return my friends if no argument is given" in {
      val firstFriend = Graph.getFriends(limit = 1).head
      firstFriend.name must equalTo("Chris Hughes")
    }
  }

  "Graph.getLikes" should {
    "return likes of a user" in {
      Graph.getLikes(samplePersonId, limit = 1).head.name must contain("Raw Chips")
    }
  }

  "Graph.getFriendsWithLikes" should {
    "return friends and likes" in {
      val firstFriend = Graph.getFriendsWithLikes(limitFriends = 1).head
      firstFriend.name must contain("Chris")
      firstFriend.likes.head.name must contain("The New Yorker")
    }
  }
}
