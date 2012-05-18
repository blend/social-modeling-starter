package com.blendlabsinc.models

import com.gravity.hbase.mapreduce.Settings
import org.apache.hadoop.hbase.HBaseConfiguration
import com.blendlabsinc.mapreduce._

case class Person(id: String, name: String, likes: List[Like]) {
  def withLikes(newLikes: List[Like]) = Person(id = id, name = name, likes = newLikes)

  def friendLikes {
    new CommonLikesJob().run(Settings.None, HBaseConfiguration.create)
  }
}
