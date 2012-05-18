package com.blendlabsinc.mapreduce

import com.gravity.hbase.mapreduce._
import com.gravity.hbase.schema.makeWritable

import com.blendlabsinc.schema.PersonHBaseCollection
import com.blendlabsinc.schema.PersonSchema.PersonTable

class TopLikesMapper extends FromTableBinaryMapperFx(PersonTable) {
  val me = PersonHBaseCollection.me
  val person = row.toPerson

  for (like <- person.likes.intersect(me.likes)) {
    val keyOutput = makeWritable(_.writeUTF(like.name))
    val valueOutput = makeWritable(_.writeBoolean(true))
    write(keyOutput, valueOutput)
  }
}

class TopLikesReducer extends ToTableBinaryReducerFx(PersonTable) {
  val like = readKey(_.readUTF)

  var sum = 0
  perValue(_ => sum += 1)

  println((like, sum))
}

class TopLikesJob extends HJob[NoSettings](
  "Find common likes",
  HMapReduceTask(
    HTaskID("Find common likes task"),
    HTaskConfigs(),
    HIO(
      HTableSettingsQuery((settings: NoSettings) => PersonTable.query2),
      HTableOutput(PersonTable)
    ),
    new TopLikesMapper,
    new TopLikesReducer
  )
)
