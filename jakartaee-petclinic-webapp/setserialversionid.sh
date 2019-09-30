#!/usr/bin/env bash


echo "STARTED"
echo $CLASSPATH
echo $JAVA_HOME
echo $HOME
export CLASSPATH=
echo "START MAVEN 1"
../mvnw --batch-mode --log-file=etc/mavenlog1.txt clean install -DskipTests=true -Dmaven.javadoc.skip=true
echo "START MAVEN 2"
../mvnw --batch-mode --log-file=etc/mavenlog2.txt dependency:tree dependency:resolve dependency:resolve-plugins -DskipTests=true -Dmaven.javadoc.skip=true
echo "START MAVEN 3"
../mvnw --batch-mode --log-file=etc/mavenlog3.txt dependency:unpack-dependencies -DskipTests=true -Dmaven.javadoc.skip=true
echo "START MAVEN 4"
../mvnw --batch-mode --log-file=etc/mavenlog4.txt dependency:build-classpath -Dmdep.outputFile=etc/classpath.txt
echo "FINISHED MAVEN"
export MY_CLASSPATH_DEPS=`cat etc/classpath.txt`
export HERE=`pwd`
export MY_CLASSPATH_APP=$HERE/target/classes/
export CLASSPATH=.:$MY_CLASSPATH_APP:$MY_CLASSPATH_DEPS
echo $CLASSPATH
cd target/classes
echo "START serialver"
export TARGET_FILE=../../etc/serialversions.txt
source ../../setserialversionid2.sh
cat $TARGET_FILE
echo "FINISHED"
#exit 0
