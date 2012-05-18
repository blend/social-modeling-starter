package com.blendlabsinc.schema

import org.specs2.mutable._
import com.blendlabsinc.models.{Person,Like}

class PersonHBaseCollectionSpec extends Specification {
  val person = Person(id = "myId", name = "John Smith", likes = List(Like(id = "myLikeId", name = "myLike")))
  "PersonHBaseCollection" should {
    "put and get" in {
      PersonHBaseCollection.put(person)
      java.lang.Thread.sleep(500)
      val person2 = PersonHBaseCollection.get("myId").get
      person2.id must equalTo("myId")
      person2.name must equalTo("John Smith")
      person2.likes.length must equalTo(1)
      val like2 = person2.likes.head
      like2.id must equalTo("myLikeId")
      like2.name must equalTo("myLike")
    }
  }
}
