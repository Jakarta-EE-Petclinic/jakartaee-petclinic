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

function runRemoteWildfly(){
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  SRV_PROFILE=it-wildfly-remote
  echo "------------------"
  echo "run Remote Wildfly"
  echo "------------------"
  echo "./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install wildfly:deploy"
  echo "./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE test"
  echo "------------------"
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install wildfly:deploy
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE test
}

function runManagedWildfly(){
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  SRV_PROFILE=it-wildfly-managed
  echo "-------------------"
  echo "run Managed Wildfly"
  echo "-------------------"
  echo "./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install wildfly:start wildfly:deploy"
  echo "./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE test"
  echo "./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE wildfly:shutdown"
  echo "------------------"
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install wildfly:start wildfly:deploy
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE test
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE wildfly:shutdown
}

function runManagedLiberty(){
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  SRV_PROFILE=it-openliberty-managed
  echo "-------------------"
  echo "run Managed Liberty"
  echo "-------------------"
  echo "./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install liberty:create-server liberty:test-start-server"
  echo "./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE test"
  echo "./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE liberty:test-stop-server"
  echo "------------------"
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE clean install liberty:create-server liberty:test-start-server
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE test
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE -P$BROWSER_PROFILE liberty:test-stop-server
}

function main(){
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  echo "-------------------"
  echo "main"
  echo "-------------------"
  #checkDependencies $TESTS_PROFILE $BROWSER_PROFILE
  #runManagedWildfly $TESTS_PROFILE $BROWSER_PROFILE
  runManagedLiberty $TESTS_PROFILE $BROWSER_PROFILE
  #runRemoteWildfly $TESTS_PROFILE $BROWSER_PROFILE
  #runRemoteLiberty $TESTS_PROFILE $BROWSER_PROFILE
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

#TESTS_PROFILE=it-skip-tests
TESTS_PROFILE=it-run-tests

main $TESTS_PROFILE $BROWSER_PROFILE



