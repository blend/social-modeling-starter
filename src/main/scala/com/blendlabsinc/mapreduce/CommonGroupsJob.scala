package com.blendlabsinc.mapreduce

import org.apache.hadoop.conf.Configuration

import com.gravity.hbase.mapreduce._
import com.gravity.hbase.mapreduce.{HMapReduceTask, HJob}
import com.gravity.hbase.schema._

import com.blendlabsinc.models._
import com.blendlabsinc.schema._

import com.blendlabsinc.schema.PersonSchema.PersonTable

class CommonGroupsMapper extends FromTableBinaryMapperFx(PersonTable) {
  val person = PersonHBaseStore.get(row)

  // TODO
}

class CommonGroupsReducer extends ToTableBinaryReducerFx(PersonTable) {
  // TODO
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

// Adding constructor params here causes problems.
class CommonGroupsJobSettings extends SettingsBase

object CommonGroupsQuery {
  def apply(settings: CommonGroupsJobSettings) = PersonTable.query2
}
