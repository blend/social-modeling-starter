package com.blendlabsinc.schema

import com.blendlabsinc.models.Person

object PersonHBaseStore {
  def put(person: Person) =
    PersonSchema.PersonTable
      .put(person.id)
      .value(_.name, person.name)
      .execute()

  def get(row: PersonSchema.PersonRow): Person =
    Person(
      id = row.rowid,
      name = row.column(_.name).getOrElse(throw new Exception("Person has no name column"))
    )
}
