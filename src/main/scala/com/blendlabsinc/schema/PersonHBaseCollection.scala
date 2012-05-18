package com.blendlabsinc.schema

import com.blendlabsinc.models.Person

object PersonHBaseCollection {
  val Me = "me"

  def put(person: Person) =
    PersonSchema.PersonTable
      .put(person.id)
      .value(_.name, person.name)
      .valueMap(_.like, (person.likes.map { like => like.id -> like.name }).toMap)
      .execute()

  def me = get(Me).getOrElse(throw new Exception("me not found."))

  def get(id: String): Option[Person] =
    PersonSchema.PersonTable.query2
      .withKey(id)
      .singleOption()
      .map(_.toPerson)
}
