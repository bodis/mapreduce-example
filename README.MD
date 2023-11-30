https://hadoop.apache.org/docs/stable/hadoop-mapreduce-client/hadoop-mapreduce-client-core/MapReduceTutorial.html

# How to run
## Run with Hadoop standalone
- step 1: check for java version (max java11)
- step 2: check if hadoop is on path
```
hadoop version
``` 
- step 3: build the project with gradle
```
gradlew clean build
```
 
- step 4: run the built JAR + MapReduce Job
```
# direct hadoop command
# input directory has to exist and output mustn't
hadoop jar build/libs/mapreduce-example-0.0.1-SNAPSHOT.jar com.nitrowise.mapreduce.TestMapreduceApp input output

# written in script
cd scripts
./run-job.sh
```

