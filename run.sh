#!/usr/bin/env bash

function checkDependencies() {
    TESTS_PROFILE=$1
    SRV_PROFILES="it-default it-wildfly-managed it-wildfly-remote it-openliberty-managed it-openliberty-remote"
    for SRV_PROFILE in $SRV_PROFILES
    do
      ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE \
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
  SRV_PROFILE=it-openliberty-remote
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE clean install liberty:deploy
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE test
}

function runRemoteWildfly(){
  TESTS_PROFILE=$1
  SRV_PROFILE=it-wildfly-remote
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE clean install wildfly:deploy
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE test
}

function runManagedWildfly(){
  TESTS_PROFILE=$1
  SRV_PROFILE=it-wildfly-managed
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE clean install wildfly:start wildfly:deploy
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE test
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE wildfly:shutdown
}

function runManagedLiberty(){
  TESTS_PROFILE=$1
  SRV_PROFILE=it-openliberty-managed
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE clean install liberty:start liberty:deploy
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE test
  ./mvnw -P$SRV_PROFILE -P$TESTS_PROFILE liberty:stop
}

function main(){
  TESTS_PROFILE=$1
  #checkDependencies $TESTS_PROFILE
  runManagedWildfly $TESTS_PROFILE
  #runRemoteWildfly $TESTS_PROFILE
  runManagedLiberty $TESTS_PROFILE
  #runRemoteLiberty $TESTS_PROFILE
}

#TESTS_PROFILE=it-skip-tests
TESTS_PROFILE=it-run-tests

main $TESTS_PROFILE



