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
  val person = PersonHBaseStore.get(row)

  // TODO output (person id, groupid, true)
}

class CommonGroupsReducer extends ToTableBinaryReducerFx(PersonTable) {
  // TODO output
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
  val MeId = "meId"
}

// Adding constructor params here causes problems.
class CommonGroupsJobSettings extends SettingsBase {
  var meId : String = _

  override def fromSettings(conf: Configuration) {
  }

  override def toSettings(conf: Configuration) {
  }
}

object CommonGroupsQuery {
  def apply(settings: CommonGroupsJobSettings) = PersonTable.query2
}
