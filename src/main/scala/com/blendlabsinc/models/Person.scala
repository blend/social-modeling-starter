package com.blendlabsinc.models

case class Person(id: String, name: String, likes: List[Like]) {
  def withLikes(newLikes: List[Like]) = Person(id = id, name = name, likes = newLikes)
}
