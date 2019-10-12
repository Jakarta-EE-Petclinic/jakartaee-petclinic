#!/usr/bin/env bash

#export MAVEN=./mvnw
export MAVEN=mvn

function checkDependencies() {
    TESTS_PROFILE=$1
    BROWSER_PROFILE=$2
    SRV_PROFILES="it-default it-wildfly-managed it-wildfly-remote it-openliberty-managed it-openliberty-remote"
    echo "------------------------------------"
    echo "check Dependencies"
    for SRV_PROFILE in $SRV_PROFILES
    do
      echo "------------------------------------"
      echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE \\"
      echo "        clean \\"
      echo "        dependency:resolve \\"
      echo "        dependency:sources \\"
      echo "        dependency:resolve-plugins \\"
      echo "        dependency:analyze \\"
      echo "        dependency:analyze-dep-mgt \\"
      echo "        dependency:analyze-duplicate \\"
      echo "        dependency:tree"
      echo "------------------------------------"
      $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE \
              clean \
              dependency:resolve \
              dependency:sources \
              dependency:resolve-plugins \
              dependency:analyze \
              dependency:analyze-dep-mgt \
              dependency:analyze-duplicate \
              dependency:tree
    done
}

function checkAllDependencies(){
  ALL_TESTS_PROFILES="it-skip-tests it-skip-tests"
  ALL_BROWSER_PROFILES="it-browser-chrome it-browser-firefox it-browser-safari it-browser-opera it-browser-htmlunit it-browser-phantomjsdriver"
  for TESTS_PROFILE in $ALL_TESTS_PROFILES
  do
    for BROWSER_PROFILE in $ALL_BROWSER_PROFILES
    do
        checkDependencies $TESTS_PROFILE $BROWSER_PROFILE
    done
  done
}

function buildSiblings(){
  cd $HOME/IdeaProjects/jakartaee-api
  $MAVEN -Pstaging install
  cd $HOME/IdeaProjects/jakartaee-petclinic
}

function testRemoteLiberty(){
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  SRV_PROFILE=it-openliberty-remote
  echo "------------------"
  echo "run Remote Liberty"
  echo "------------------"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install liberty:deploy"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE test"
  echo "------------------"
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install liberty:deploy
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE test
}

function runRemoteLiberty(){
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  SRV_PROFILE=it-openliberty-remote
  echo "------------------"
  echo "run Remote Liberty"
  echo "------------------"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install liberty:deploy"
  echo "------------------"
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install liberty:deploy
  echo "http://10.4.25.161:8080/petclinic/ (IP-Number may vary)"
}

function testRemoteWildfly(){
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  SRV_PROFILE=it-wildfly-remote
  echo "--------------------"
  echo "test Remote Wildfly"
  echo "--------------------"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install wildfly:deploy"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE test"
  echo "--------------------"
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install wildfly:deploy
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE test
}

function runRemoteWildfly(){
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  SRV_PROFILE=it-wildfly-remote
  echo "------------------"
  echo "run Remote Wildfly"
  echo "------------------"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install wildfly:deploy"
  echo "------------------"
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install wildfly:deploy
  echo "http://localhost:8080/petclinic/"
}

function runManagedWildfly(){
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  SRV_PROFILE=it-wildfly-managed
  echo "-------------------"
  echo "run Managed Wildfly"
  echo "-------------------"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install wildfly:start wildfly:deploy"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE wildfly:start wildfly:deploy"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE test"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE wildfly:shutdown"
  echo "------------------"
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE wildfly:start wildfly:deploy
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE test
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE wildfly:shutdown
}

function testManagedLiberty(){
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  SRV_PROFILE=it-openliberty-managed
  echo "--------------------"
  echo "test Managed Liberty"
  echo "--------------------"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE liberty:test-start-server"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE test"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE liberty:test-stop-server"
  echo "-------------------"
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE liberty:test-start-server
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE test
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE liberty:test-stop-server
}

function runManagedLiberty(){
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  SRV_PROFILE=it-openliberty-managed
  echo "-------------------"
  echo "run Managed Liberty"
  echo "-------------------"
  echo "$MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install liberty:run-server"
  echo "------------------"
  $MAVEN -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install liberty:run-server
}

function resolveDependencies() {
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  #checkAllDependencies
  checkDependencies $TESTS_PROFILE $BROWSER_PROFILE
}

function runManaged() {
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  #runManagedLiberty $TESTS_PROFILE $BROWSER_PROFILE
  #runManagedWildfly $TESTS_PROFILE $BROWSER_PROFILE
  #runManagedGlassfish $TESTS_PROFILE $BROWSER_PROFILE
}

function runRemote() {
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  runRemoteWildfly $TESTS_PROFILE $BROWSER_PROFILE
  #runRemoteLiberty $TESTS_PROFILE $BROWSER_PROFILE
  #runRemoteGlassfish $TESTS_PROFILE $BROWSER_PROFILE
}

function main(){
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  echo "-------------------"
  echo " main"
  echo "-------------------"
  #resolveDependencies $TESTS_PROFILE $BROWSER_PROFILE
  runRemote  $TESTS_PROFILE $BROWSER_PROFILE
  #runManaged  $TESTS_PROFILE $BROWSER_PROFILE
  echo "-------------------"
  echo " DONE and READY"
  echo "-------------------"
}

BROWSER_PROFILE=it-browser-chrome
#BROWSER_PROFILE=it-browser-firefox
#BROWSER_PROFILE=it-browser-safari
#BROWSER_PROFILE=it-browser-opera
#BROWSER_PROFILE=it-browser-htmlunit
#BROWSER_PROFILE=it-browser-phantomjsdriver

TESTS_PROFILE=it-skip-tests
#TESTS_PROFILE=it-run-tests

main $TESTS_PROFILE $BROWSER_PROFILE
