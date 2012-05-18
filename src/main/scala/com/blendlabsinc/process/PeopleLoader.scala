package com.blendlabsinc.process

import com.blendlabsinc.facebook.Graph
import com.blendlabsinc.schema.PersonHBaseCollection

object PeopleLoader extends scala.App {
  Graph.getFriends().foreach { friend =>
    PersonHBaseCollection.put(friend)
    println(" - " + friend.name + " (id=" + friend.id + ")")
  }
}
