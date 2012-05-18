package com.blendlabsinc.mapreduce

import org.specs2.mutable._
import org.apache.hadoop.hbase.HBaseConfiguration

class CommonGroupsJobSpec extends Specification {
  "Common groups job" should {
    "calculate common groups" in {
      new CommonGroupsJob().run(
        new CommonGroupsJobSettings,
        HBaseConfiguration.create
      )

      "" must equalTo("")
    }
  }
}
