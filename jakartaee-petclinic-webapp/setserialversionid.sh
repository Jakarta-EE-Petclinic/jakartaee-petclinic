#!/usr/bin/env bash


echo "STARTED"
echo $CLASSPATH
echo $JAVA_HOME
echo $HOME
export CLASSPATH=
#./mvnw --batch-mode --log-file=etc/mavenlog1.txt clean install -Pdeveloping -DskipTests=true -Dmaven.javadoc.skip=true -Dmdep.outputFile=etc\classpath.txt
echo "START MAVEN 1"
./mvnw --batch-mode --log-file=etc/mavenlog1.txt clean package -Pdeveloping -DskipTests=true -Dmaven.javadoc.skip=true
echo "START MAVEN 2"
./mvnw --batch-mode --log-file=etc/mavenlog2.txt dependency:tree dependency:unpack-dependencies -Pdeveloping -DskipTests=true -Dmaven.javadoc.skip=true
echo "START MAVEN 3"
./mvnw --batch-mode --log-file=etc/mavenlog3.txt dependency:build-classpath -Pdeveloping -Dmdep.outputFile=etc/classpath.txt
echo "FINISHED MAVEN"
export MY_CLASSPATH_DEPS=`cat etc/classpath.txt`
export HERE=`pwd`
export MY_CLASSPATH_APP=$HERE/target/classes/
export CLASSPATH=".;$MY_CLASSPATH_APP"
echo $CLASSPATH
cd target/classes
echo "START serialver"
export TARGET_FILE=../../etc/serialversions.txt
serialver org.woehlke.bloodmoney.oodm.model.parts.AuditModel > $TARGET_FILE
serialver org.woehlke.bloodmoney.oodm.model.BloodPressureMeasurement >> $TARGET_FILE
serialver org.woehlke.bloodmoney.frontend.model.FlashMessage >> $TARGET_FILE
serialver org.woehlke.bloodmoney.frontend.model.UserSession >> $TARGET_FILE
serialver org.woehlke.bloodmoney.config.ApplicationProperties >> $TARGET_FILE
serialver org.woehlke.bloodmoney.user.model.LoginForm >> $TARGET_FILE
serialver org.woehlke.bloodmoney.user.model.UserAccount >> $TARGET_FILE
#serialver org.woehlke.bloodmoney.user.model.UserDetailsBean >> $TARGET_FILE
cat $TARGET_FILE
echo "FINISHED"
exit 0