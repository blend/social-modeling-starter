social-modeling-starter
=======================

Facebook social data modeling with Scala, HBase, and HPaste.

Setup
-----------------------

Visit https://developers.facebook.com/tools/explorer?method=GET&path=me .
Click "Get Access Token". Select the following permissions:
* User Data Permissions
    * user_groups
    * user_likes
* Friends Data Permissions
    * friends_groups
    * friends_likes

Click "Get Access Token", then "Allow". Copy and paste the Access Token.
In your console, run:

    export FACEBOOK_ACCESS_TOKEN=<your access token>

Download Cloudera's HBase distribution (CDH4 Beta 2) at
http://archive.cloudera.com/cdh4/cdh/4/hbase-0.92.1-cdh4.0.0b2.tar.gz.

Unarchive the file and run

    bin/start-hbase.sh
    sleep 5
    bin/hbase shell
    hbase> create 'person', 'info', 'like'

To load your Facebook data, run

    ./bin/load.sh

Two MapReduce calculations are included: "friend likes" and "top
likes". To execute these, run

    ./bin/topLikes.sh
    ./bin/friendLikes.sh
