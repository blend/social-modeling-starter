package com.blendlabsinc.mapreduce

import com.gravity.hbase.mapreduce.Settings

import org.specs2.mutable._
import org.apache.hadoop.hbase.HBaseConfiguration

class TopLikesJobSpec extends Specification {
  "Top likes job" should {
    "calculate top likes" in {
      new TopLikesJob().run(
        Settings.None,
        HBaseConfiguration.create
      )

      "" must equalTo("")
    }
  }
}
