package com.blendlabsinc.mapreduce

import com.gravity.hbase.mapreduce.Settings

import org.specs2.mutable._
import org.apache.hadoop.hbase.HBaseConfiguration

class CommonLikesJobSpec extends Specification {
  "Common likes job" should {
    "calculate common likes" in {
      new CommonLikesJob().run(
        Settings.None,
        HBaseConfiguration.create
      )

      "" must equalTo("")
    }
  }
}
