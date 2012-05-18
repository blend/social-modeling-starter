package com.blendlabsinc.process

import com.blendlabsinc.facebook.Graph
import com.blendlabsinc.models.Person
import com.blendlabsinc.schema.PersonHBaseStore

object PeopleLoader extends scala.App {
  val people = Person(id = "me", name = "Me", groups = Graph.getGroups("me")) :: Graph.getFriendsWithGroups()
  people.foreach { person =>
    PersonHBaseStore.put(person)
    println(" - " + person.name + " (id=" + person.id + ") in " + person.groups.length + " groups")
  }
}
