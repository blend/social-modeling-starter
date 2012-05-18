package com.blendlabsinc.mapreduce

import org.apache.hadoop.conf.Configuration

import com.gravity.hbase.mapreduce._
import com.gravity.hbase.mapreduce.{HMapReduceTask, HJob}
import com.gravity.hbase.schema._

import com.blendlabsinc.models._
import com.blendlabsinc.schema._

import com.blendlabsinc.schema.PersonSchema.PersonTable

class CommonLikesMapper extends FromTableBinaryMapperFx(PersonTable) {
  val me = PersonHBaseStore.me
  val person = row.toPerson

  for (like <- person.likes.intersect(me.likes).toList) {
    val keyOutput = makeWritable(_.writeUTF(person.id))
    val valueOutput = makeWritable(_.writeUTF(like.name))
    write(keyOutput, valueOutput)
  }
}

class CommonLikesReducer extends ToTableBinaryReducerFx(PersonTable) {
  val personId = readKey(_.readUTF)
  perValue {
    valueInput => {
      val like = valueInput.readUTF
      println(PersonHBaseStore.get(personId).get.name + ": " + like)
    }
  }
}

class CommonLikesJob extends HJob[CommonLikesJobSettings](
  "Find common likes",
  HMapReduceTask(
    HTaskID("Find common likes task"),
    HTaskConfigs(),
    HIO(
      HTableSettingsQuery(CommonLikesQuery.apply),
      HTableOutput(PersonTable)
    ),
    new CommonLikesMapper,
    new CommonLikesReducer
  )
)

object CommonLikesJobSettings {
}

class CommonLikesJobSettings extends SettingsBase {
  override def fromSettings(conf: Configuration) {
  }

  override def toSettings(conf: Configuration) {
  }
}

object CommonLikesQuery {
  def apply(settings: CommonLikesJobSettings) = PersonTable.query2
}
