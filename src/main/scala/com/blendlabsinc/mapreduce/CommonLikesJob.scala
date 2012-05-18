package com.blendlabsinc.mapreduce

import com.gravity.hbase.mapreduce._
import com.gravity.hbase.schema.makeWritable

import com.blendlabsinc.schema.PersonHBaseCollection
import com.blendlabsinc.schema.PersonSchema.PersonTable

class CommonLikesMapper extends FromTableBinaryMapperFx(PersonTable) {
  val me = PersonHBaseCollection.me
  val person = row.toPerson

  for (like <- person.likes.intersect(me.likes)) {
    val keyOutput = makeWritable(_.writeUTF(person.id))
    val valueOutput = makeWritable(_.writeUTF(like.name))
    write(keyOutput, valueOutput)
  }
}

class CommonLikesReducer extends ToTableBinaryReducerFx(PersonTable) {
  val person = {
    val personId = readKey(_.readUTF)
    PersonHBaseCollection.get(personId).get
  }
  println(person.name)

  perValue(valueInput => {
    val like = valueInput.readUTF
    println("- " + like)
  })
}

class CommonLikesJob extends HJob[NoSettings](
  "Find common likes",
  HMapReduceTask(
    HTaskID("Find common likes task"),
    HTaskConfigs(),
    HIO(
      HTableSettingsQuery((settings: NoSettings) => PersonTable.query2),
      HTableOutput(PersonTable)
    ),
    new CommonLikesMapper,
    new CommonLikesReducer
  )
)
