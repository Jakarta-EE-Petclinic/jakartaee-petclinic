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
  ./mvnw -Pit-openliberty-remote clean install liberty:deploy test
}

function runRemoteWildfly(){
  ./mvnw -Pit-wildfly-remote clean install wildfly:deploy test
}

function runManagedWildfly(){
  ./mvnw -Pit-wildfly-managed clean install wildfly:start test
  ./mvnw -Pit-wildfly-managed wildfly:shutdown
}

function runManagedLiberty(){
  ./mvnw  -Pit-openliberty-managed clean install liberty:start test
  ./mvnw  -Pit-openliberty-managed liberty:stop
}

function main(){
  #checkDependencies
  runManagedWildfly
  #runRemoteWildfly
  #runManagedLiberty
  #runRemoteLiberty
}

main



