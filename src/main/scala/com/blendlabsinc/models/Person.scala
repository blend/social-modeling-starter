package com.blendlabsinc.models

import org.apache.hadoop.hbase.HBaseConfiguration
import com.blendlabsinc.mapreduce._

case class Person(id: String, name: String, likes: List[Like]) {
  def withLikes(newLikes: List[Like]) = Person(id = id, name = name, likes = newLikes)

  def friendLikes {
    new CommonLikesJob().run(
      new CommonLikesJobSettings,
      HBaseConfiguration.create
    )
  }
}
