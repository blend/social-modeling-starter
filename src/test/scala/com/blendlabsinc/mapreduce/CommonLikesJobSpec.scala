package com.blendlabsinc.mapreduce

import org.specs2.mutable._
import org.apache.hadoop.hbase.HBaseConfiguration

class CommonLikesJobSpec extends Specification {
  "Common likes job" should {
    "calculate common likes" in {
      val settings = new CommonLikesJobSettings
      new CommonLikesJob().run(
        settings,
        HBaseConfiguration.create
      )

      "" must equalTo("")
    }
  }
}
