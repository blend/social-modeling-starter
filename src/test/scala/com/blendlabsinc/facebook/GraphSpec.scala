package com.blendlabsinc.facebook

import org.specs2.mutable._

class GraphSpec extends Specification {
  val samplePersonId = "5"

  "Graph.getFriends" should {
    "return my friends if no argument is given" in {
      val firstFriend = Graph.getFriends().head
      firstFriend.name must equalTo("Chris Hughes")
    }
  }
}
