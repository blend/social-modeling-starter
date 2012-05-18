package com.blendlabsinc.mapreduce

import org.apache.hadoop.conf.Configuration

import com.gravity.hbase.mapreduce._
import com.gravity.hbase.mapreduce.{HMapReduceTask, HJob}
import com.gravity.hbase.schema._

import com.blendlabsinc.models._
import com.blendlabsinc.schema._

import com.blendlabsinc.schema.PersonSchema.PersonTable

class CommonGroupsMapper extends FromTableBinaryMapperFx(PersonTable) {
  val me = PersonHBaseStore.me
  val person = row.toPerson

  for (group <- person.groups.intersect(me.groups).toList) {
    val keyOutput = makeWritable(_.writeUTF(person.id))
    val valueOutput = makeWritable(_.writeUTF(group.name))
    write(keyOutput, valueOutput)
  }
}

class CommonGroupsReducer extends ToTableBinaryReducerFx(PersonTable) {
  val personId = readKey(_.readUTF)
  perValue {
    valueInput => {
      val group = valueInput.readUTF
      println(PersonHBaseStore.get(personId).get.name + ": " + group)
    }
  }
}

class CommonGroupsJob extends HJob[CommonGroupsJobSettings](
  "Find common groups",
  HMapReduceTask(
    HTaskID("Find common groups task"),
    HTaskConfigs(),
    HIO(
      HTableSettingsQuery(CommonGroupsQuery.apply),
      HTableOutput(PersonTable)
    ),
    new CommonGroupsMapper,
    new CommonGroupsReducer
  )
)

object CommonGroupsJobSettings {
}

class CommonGroupsJobSettings extends SettingsBase {
  override def fromSettings(conf: Configuration) {
  }

  override def toSettings(conf: Configuration) {
  }
}

object CommonGroupsQuery {
  def apply(settings: CommonGroupsJobSettings) = PersonTable.query2
}
