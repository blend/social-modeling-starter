package com.blendlabsinc.schema

import com.blendlabsinc.models.Person

object PersonHBaseStore {
  def put(person: Person) =
    PersonSchema.PersonTable
      .put(person.id)
      .value(_.name, person.name)
      .execute()

  def me = get("me")

  def get(id: String): Option[Person] =
    PersonSchema.PersonTable.query2
      .withKey(id)
      .singleOption()
      .map(_.toPerson)

}
