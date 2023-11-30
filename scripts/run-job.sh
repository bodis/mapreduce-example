#!/bin/zsh
HADOOP_HOME=/Users/bodist/work/server/hadoop
PATH=$HADOOP_HOME/bin:$PATH

# check HADOOP runtime
hadoop version

# remove old output
rm -rf ../output

# start job
hadoop jar ../build/libs/mapreduce-example-0.0.1-SNAPSHOT.jar com.nitrowise.mapreduce.TestMapreduceApp ../input ../output

