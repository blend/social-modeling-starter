package com.blendlabsinc.schema

import com.blendlabsinc.models.Person

object PersonHBaseStore {
  def mkHBasePutOperation(person: Person, personId: String) =
    PersonSchema.PersonTable
      .put(personId)
      .value(_.name, person.name)

  def get(row: PersonSchema.PersonRow): Person =
    Person(
      id = row.rowid,
      name = row.column(_.name).getOrElse(throw new Exception("Person has no name column"))
    )
}
