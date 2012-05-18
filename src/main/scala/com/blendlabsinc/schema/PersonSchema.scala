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

    val like = family[String, String, String]("like")
  }

  class PersonRow(table: PersonTable, result: DeserializedResult) extends HRow[PersonTable, String](result, table) {
    def toPerson: Person =
      Person(
        id = rowid,
        name = column(_.name).getOrElse(throw new Exception("Person has no name column")),
        likes = (family(_.like).map { case (id, name) => Like(id, name) }).toList
      )
  }

  val PersonTable = table(new PersonTable)
}
