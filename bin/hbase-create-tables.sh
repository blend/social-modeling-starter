#!/bin/sh

BASEDIR=$(dirname $0)

hbase shell $BASEDIR/hbase-create-tables.rb
