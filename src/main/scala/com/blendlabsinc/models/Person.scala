package com.blendlabsinc.models

case class Person(id: String, name: String, groups: List[Group]) {
  def withGroups(newGroups: List[Group]) = Person(id = id, name = name, groups = newGroups)
}
