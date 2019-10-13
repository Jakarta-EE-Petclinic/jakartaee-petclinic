#!/usr/bin/env bash

#export MAVEN=./mvnw
export MAVEN=mvn
export LOGFILE=log/run.log.txt

export ALL_SRV_PROFILES_REMOTE="it-wildfly-remote it-openliberty-remote it-glassfish-remote"
export ALL_SRV_PROFILES_MANAGED="it-wildfly-managed it-openliberty-managed"
export ALL_SRV_PROFILES="it-default $ALL_SRV_PROFILES_REMOTE $ALL_SRV_PROFILES_MANAGED"
export ALL_TESTS_PROFILES="it-skip-tests it-skip-tests"
export ALL_BROWSER_PROFILES="it-browser-chrome it-browser-firefox it-browser-safari it-browser-opera"

function echoEnv() {
  echo "CLASSPATH: $CLASSPATH"
  echo "JAVA_HOME: $JAVA_HOME"
  echo "HOME:      $HOME"
}

function setSerialVersionId() {
  LOGFILE2="log/setSerialVersionId.log.txt"
  RUN_SCRIPT="log/setSerialVersionId.sh"
  LOGFILE_RESULT="log/setSerialVersionId.log.txt"
  echo "STARTED"
  echo "$MAVEN -Pprepare-serialver clean install | grep WARNING | grep serialVersionUID > $LOGFILE2"
  #$MAVEN -Pprepare-serialver clean install | grep WARNING | grep serialVersionUID > $LOGFILE2
  echo "FINISHED MAVEN"
  echo "cat $LOGFILE2"
  cat $LOGFILE2 | cut -d" " -f5 | sed "s/^/serialver /g" > $RUN_SCRIPT
  HERE=`pwd`
  cd target/prepareserialver/
  echo "--------------------------------------------------------------------------"
  source ../../$RUN_SCRIPT
  echo "--------------------------------------------------------------------------"
  cd $HERE
  echo "FINISHED"
}

function checkProfileDependencies() {
    TESTS_PROFILE=$1
    BROWSER_PROFILE=$2
    SRV_PROFILE=$3
    echo "--------------------------------------------------------------------------"
    echo " checkProfileDependencies $SRV_PROFILE $TESTS_PROFILE $BROWSER_PROFILE"
    echo "--------------------------------------------------------------------------"
    echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE \\"
    echo "        clean \\"
    echo "        dependency:resolve \\"
    echo "        dependency:sources \\"
    echo "        dependency:resolve-plugins \\"
    echo "        dependency:analyze \\"
    echo "        dependency:analyze-dep-mgt \\"
    echo "        dependency:analyze-duplicate \\"
    echo "        dependency:tree"
    echo "--------------------------------------------------------------------------"
    $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE \
      clean \
      dependency:resolve \
      dependency:sources \
      dependency:resolve-plugins \
      dependency:analyze \
      dependency:analyze-dep-mgt \
      dependency:analyze-duplicate \
      dependency:tree
    echo "--------------------------------------------------------------------------"
}

function checkDependencies() {
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  echo "--------------------------------------------------------------------------"
  echo " check Dependencies $TESTS_PROFILE $BROWSER_PROFILE"
  echo "--------------------------------------------------------------------------"
  for SRV_PROFILE in $ALL_SRV_PROFILES
  do
    checkProfileDependencies $SRV_PROFILE $TESTS_PROFILE $BROWSER_PROFILE
  done
  echo "------------------------------------"
}

function checkAllDependencies(){
  echo "--------------------------------------------------------------------------"
  echo " checkAllDependencies"
  echo "--------------------------------------------------------------------------"
  for TESTS_PROFILE in $ALL_TESTS_PROFILES
  do
    for BROWSER_PROFILE in $ALL_BROWSER_PROFILES
    do
        checkDependencies $TESTS_PROFILE $BROWSER_PROFILE
    done
  done
}

function buildSiblings(){
  echo "--------------------------------------------------------------------------"
  echo " buildSiblings"
  echo "--------------------------------------------------------------------------"
  cd $HOME/IdeaProjects/jakartaee-api
  $MAVEN -Pstaging install
  cd $HOME/IdeaProjects/jakartaee-petclinic
}

function testRemoteLiberty(){
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  SRV_PROFILE=it-openliberty-remote
  checkProfileDependencies $SRV_PROFILE $TESTS_PROFILE $BROWSER_PROFILE
  echo "--------------------------------------------------------------------------"
  echo " test Remote Liberty $TESTS_PROFILE $BROWSER_PROFILE"
  echo "--------------------------------------------------------------------------"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install liberty:deploy"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE test"
  echo "--------------------------------------------------------------------------"
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install liberty:deploy
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE test
  echo "--------------------------------------------------------------------------"
}

function runRemoteLiberty(){
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  SRV_PROFILE=it-openliberty-remote
  checkProfileDependencies $SRV_PROFILE $TESTS_PROFILE $BROWSER_PROFILE
  echo "--------------------------------------------------------------------------"
  echo " run Remote Liberty $TESTS_PROFILE $BROWSER_PROFILE"
  echo "--------------------------------------------------------------------------"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install liberty:deploy"
  echo "--------------------------------------------------------------------------"
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install liberty:deploy
  echo "--------------------------------------------------------------------------"
  echo " http://10.4.25.161:8080/petclinic/ (IP-Number may vary)"
  echo "--------------------------------------------------------------------------"
}

function testRemoteWildfly(){
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  SRV_PROFILE=it-wildfly-remote
  checkProfileDependencies $SRV_PROFILE $TESTS_PROFILE $BROWSER_PROFILE
  echo "--------------------------------------------------------------------------"
  echo " test Remote Wildfly $TESTS_PROFILE $BROWSER_PROFILE"
  echo "--------------------------------------------------------------------------"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install wildfly:deploy"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE test"
  echo "--------------------------------------------------------------------------"
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install wildfly:deploy
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE test
  echo "--------------------------------------------------------------------------"
}

function runRemoteWildfly(){
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  SRV_PROFILE=it-wildfly-remote
  checkProfileDependencies $SRV_PROFILE $TESTS_PROFILE $BROWSER_PROFILE
  echo "--------------------------------------------------------------------------"
  echo " run Remote Wildfly $TESTS_PROFILE $BROWSER_PROFILE"
  echo "--------------------------------------------------------------------------"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install wildfly:deploy"
  echo "--------------------------------------------------------------------------"
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install wildfly:deploy
  echo "--------------------------------------------------------------------------"
  echo " http://localhost:8080/petclinic/"
  echo "--------------------------------------------------------------------------"
}

function runManagedWildfly(){
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  SRV_PROFILE=it-wildfly-managed
  checkProfileDependencies $SRV_PROFILE $TESTS_PROFILE $BROWSER_PROFILE
  echo "--------------------------------------------------------------------------"
  echo " run Managed Wildfly $TESTS_PROFILE $BROWSER_PROFILE"
  echo "--------------------------------------------------------------------------"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install wildfly:start wildfly:deploy"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE wildfly:start wildfly:deploy"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE test"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE wildfly:shutdown"
  echo "--------------------------------------------------------------------------"
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE wildfly:start wildfly:deploy
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE test
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE wildfly:shutdown
  echo "--------------------------------------------------------------------------"
}

function testManagedLiberty(){
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  SRV_PROFILE=it-openliberty-managed
  checkProfileDependencies $SRV_PROFILE $TESTS_PROFILE $BROWSER_PROFILE
  echo "--------------------------------------------------------------------------"
  echo " test Managed Liberty $TESTS_PROFILE $BROWSER_PROFILE"
  echo "--------------------------------------------------------------------------"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE liberty:test-start-server"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE test"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE liberty:test-stop-server"
  echo "--------------------------------------------------------------------------"
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE liberty:test-start-server
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE test
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE liberty:test-stop-server
  echo "--------------------------------------------------------------------------"
}

function runManagedLiberty(){
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  SRV_PROFILE=it-openliberty-managed
  checkProfileDependencies $SRV_PROFILE $TESTS_PROFILE $BROWSER_PROFILE
  echo "--------------------------------------------------------------------------"
  echo " run Managed Liberty $TESTS_PROFILE $BROWSER_PROFILE"
  echo "--------------------------------------------------------------------------"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install liberty:run-server"
  echo "--------------------------------------------------------------------------"
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install liberty:run-server
  echo "--------------------------------------------------------------------------"
}

function runManagedGlassfish(){
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  SRV_PROFILE=it-glassfish-managed
  checkProfileDependencies $SRV_PROFILE $TESTS_PROFILE $BROWSER_PROFILE
  echo "------------------------"
  echo "run Managed Glassfish"
  echo "------------------------"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install"
  echo "------------------------"
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install
}

function runRemoteGlassfish(){
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  SRV_PROFILE=it-glassfish-remote
  checkProfileDependencies $SRV_PROFILE $TESTS_PROFILE $BROWSER_PROFILE
  echo "------------------------"
  echo "run Remote Glassfish"
  echo "------------------------"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install"
  echo "------------------------"
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install
  echo "http://localhost:8080/petclinic/"
}

function runQa() {
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  $MAVEN -Pcheck-code -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install > $LOGFILE
  cat $LOGFILE
}

function resolveDependencies() {
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  #checkAllDependencies
  checkDependencies $TESTS_PROFILE $BROWSER_PROFILE
}
