#!/usr/bin/env bash

function checkDependencies() {
    TESTS_PROFILE=$1
    BROWSER_PROFILE=$2
    SRV_PROFILES="it-default it-wildfly-managed it-wildfly-remote it-openliberty-managed it-openliberty-remote"
    echo "------------------------------------"
    echo "check Dependencies"
    for SRV_PROFILE in $SRV_PROFILES
    do
      echo "------------------------------------"
      echo "./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE \\"
      echo "        clean \\"
      echo "        dependency:resolve \\"
      echo "        dependency:sources \\"
      echo "        dependency:resolve-plugins \\"
      echo "        dependency:analyze \\"
      echo "        dependency:analyze-dep-mgt \\"
      echo "        dependency:analyze-duplicate \\"
      echo "        dependency:tree"
      echo "------------------------------------"
      ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE \
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

function runRemoteLiberty(){
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  SRV_PROFILE=it-openliberty-remote
  echo "------------------"
  echo "run Remote Liberty"
  echo "------------------"
  echo "./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install liberty:deploy"
  echo "./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE test"
  echo "------------------"
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install liberty:deploy
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE test
}


function testRemoteWildfly(){
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  SRV_PROFILE=it-wildfly-remote
  echo "--------------------"
  echo "test Remote Wildfly"
  echo "--------------------"
  echo "./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install wildfly:deploy"
  echo "./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE test"
  echo "--------------------"
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install wildfly:deploy
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE test
}

function runRemoteWildfly(){
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  SRV_PROFILE=it-wildfly-remote
  echo "------------------"
  echo "run Remote Wildfly"
  echo "------------------"
  echo "./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install wildfly:deploy"
  echo "------------------"
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install wildfly:deploy
}

function runManagedWildfly(){
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  SRV_PROFILE=it-wildfly-managed
  echo "-------------------"
  echo "run Managed Wildfly"
  echo "-------------------"
  echo "./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install wildfly:start wildfly:deploy"
  echo "./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE wildfly:start wildfly:deploy"
  echo "./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE test"
  echo "./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE wildfly:shutdown"
  echo "------------------"
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE wildfly:start wildfly:deploy
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE test
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE wildfly:shutdown
}

function testManagedLiberty(){
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  SRV_PROFILE=it-openliberty-managed
  echo "--------------------"
  echo "test Managed Liberty"
  echo "--------------------"
  echo "./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install"
  echo "./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE liberty:test-start-server"
  echo "./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE test"
  echo "./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE liberty:test-stop-server"
  echo "-------------------"
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE liberty:test-start-server
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE test
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE liberty:test-stop-server
}

function runManagedLiberty(){
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  SRV_PROFILE=it-openliberty-managed
  echo "-------------------"
  echo "run Managed Liberty"
  echo "-------------------"
  echo "./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install liberty:run-server"
  echo "------------------"
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install liberty:run-server
}

function checkAllDependencies(){
  ALL_TESTS_PROFILES="it-skip-tests it-skip-tests"
  ALL_BROWSER_PROFILES="it-browser-chrome it-browser-firefox it-browser-safari it-browser-opera it-browser-htmlunit it-browser-phantomjsdriver"
  for TESTS_PROFILE in $ALL_TESTS_PROFILES
  do
    for BWROWSER_PROFILE in $ALL_BROWSER_PROFILES
    do
        checkDependencies $TESTS_PROFILE $BROWSER_PROFILE
    done
  done
}

function main(){
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  echo "-------------------"
  echo "main"
  echo "-------------------"
  checkAllDependencies
  #checkDependencies $TESTS_PROFILE $BROWSER_PROFILE
  #runManagedLiberty $TESTS_PROFILE $BROWSER_PROFILE
  #runRemoteWildfly $TESTS_PROFILE $BROWSER_PROFILE
  #TODO:runManagedWildfly $TESTS_PROFILE $BROWSER_PROFILE
  #TODO:runRemoteLiberty $TESTS_PROFILE $BROWSER_PROFILE
  echo "-------------------"
  echo "DONE and READY"
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



