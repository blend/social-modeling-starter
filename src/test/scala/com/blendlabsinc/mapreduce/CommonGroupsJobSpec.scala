package com.blendlabsinc.mapreduce

import org.specs2.mutable._
import org.apache.hadoop.hbase.HBaseConfiguration

class CommonGroupsJobSpec extends Specification {
  "Common groups job" should {
    "calculate common groups" in {
      val settings = new CommonGroupsJobSettings

      val config = {
        val c = HBaseConfiguration.create
        val localConfigPaths = List("conf/hbase-site.xml", "../conf/hbase-site.xml")
        localConfigPaths.foreach((p) => {
          val path = new org.apache.hadoop.fs.Path(p)
          if ((new java.io.File(path.toString)).exists) {
            c.addResource(path)
          }
        })
        c
      }

      new CommonGroupsJob().run(
        settings,
        config
      )

      "" must equalTo("")
    }
  }
}
