package com.blendlabsinc.process

import com.blendlabsinc.facebook.Graph
import com.blendlabsinc.schema.PersonHBaseStore

object PeopleLoader extends scala.App {
  Graph.getFriendsWithGroups().foreach { friend =>
    PersonHBaseStore.put(friend)
    println(" - " + friend.name + " (id=" + friend.id + ")")
  }
}
