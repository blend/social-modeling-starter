package com.blendlabsinc.schema

import org.specs2.mutable._

import com.blendlabsinc.models.{Person,Group}

class PersonHBaseStoreSpec extends Specification {
  val person = Person(id = "myId", name = "John Smith", groups = List(Group(id = "myGroupId", name = "myGroup")))
  "PersonHBaseStore" should {
    "put and get" in {
      PersonHBaseStore.put(person)
      java.lang.Thread.sleep(500)
      val person2 = PersonHBaseStore.get("myId").get
      person2.id must equalTo("myId")
      person2.name must equalTo("John Smith")
      person2.groups.length must equalTo(1)
      val group2 = person2.groups.head
      group2.id must equalTo("myGroupId")
      group2.name must equalTo("myGroup")
    }
  }
}
