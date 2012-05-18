package com.blendlabsinc.schema

import org.apache.hadoop.hbase.HBaseConfiguration
import com.gravity.hbase.schema._
import com.blendlabsinc.models._

object PersonSchema extends Schema {
  implicit val conf = HBaseConfiguration.create

  class PersonTable extends HbaseTable[PersonTable, String, PersonRow](
    tableName = "person",
    rowKeyClass = classOf[String]
  ) {
    def rowBuilder(result: DeserializedResult) = new PersonRow(this, result)

    val info = family[String, String, Any]("info")
    val name = column(info, "name", classOf[String])

    val group = family[String, String, Any]("group")
  }

  class PersonRow(table: PersonTable, result: DeserializedResult) extends HRow[PersonTable, String](result, table)

  val PersonTable = table(new PersonTable)
}

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
