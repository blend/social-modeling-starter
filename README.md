social-modeling-starter
=======================

Facebook social data modeling with Scala, HBase, and HPaste

Setup
-----------------------

Download Cloudera's HBase distribution (CDH4 Beta 2) at
http://archive.cloudera.com/cdh4/cdh/4/hbase-0.92.1-cdh4.0.0b2.tar.gz.

Unarchive the file and run

    bin/start-hbase.sh
    sleep 5
    bin/hbase shell
    hbase> create 'person', 'info', 'group'
