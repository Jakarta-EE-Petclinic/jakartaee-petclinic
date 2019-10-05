#!/usr/bin/env bash

function checkDependencies() {
    MY_PROFILES="it-default it-wildfly-managed it-wildfly-remote it-openliberty-managed it-openliberty-remote"
    for THIS_PROFILE in $MY_PROFILES
    do
      ./mvnw  -P$THIS_PROFILE clean \
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
  ./mvnw -Pit-openliberty-remote -Pit-skip-tests clean install liberty:deploy test
}

function runRemoteWildfly(){
  ./mvnw -Pit-wildfly-remote -Pit-skip-tests clean install wildfly:deploy test
}

function runManagedWildfly(){
  ./mvnw -Pit-wildfly-managed -Pit-skip-tests clean install wildfly:start test
  ./mvnw -Pit-wildfly-managed -Pit-skip-tests wildfly:shutdown
}

function runManagedLiberty(){
  ./mvnw  -Pit-openliberty-managed -Pit-skip-tests clean install liberty:start test
  ./mvnw  -Pit-openliberty-managed -Pit-skip-tests liberty:stop
}

function main(){
  checkDependencies
  runManagedWildfly
  #runRemoteWildfly
  runManagedLiberty
  #runRemoteLiberty
}

main



