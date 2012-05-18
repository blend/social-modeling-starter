#!/bin/bash

BASEDIR=$(dirname $0)
cd $BASEDIR/.. && sbt "test-only com.blendlabsinc.mapreduce.TopLikesJobSpec"
