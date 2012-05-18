#!/bin/bash

BASEDIR=$(dirname $0)
cd $BASEDIR/.. && sbt "run-main com.blendlabsinc.process.PeopleLoader $@"

