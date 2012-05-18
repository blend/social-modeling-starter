package com.blendlabsinc.process

import com.blendlabsinc.facebook.Graph
import com.blendlabsinc.models.Person
import com.blendlabsinc.schema.PersonHBaseCollection

object PeopleLoader extends scala.App {
  val limitFriends = if (args.length == 1) args(0).toInt else 1000

  val people = Person(id = "me", name = "Me", likes = Graph.getLikes("me")) :: Graph.getFriendsWithLikes(limitFriends = limitFriends)
  people.foreach { person =>
    PersonHBaseCollection.put(person)
    println(" - " + person.name + " (id=" + person.id + ") in " + person.likes.length + " likes")
  }
}
