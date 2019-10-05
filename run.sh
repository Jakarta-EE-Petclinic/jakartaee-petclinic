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
              dependency:analyze-duplicates \
              dependency:tree
    done
}

function deployRemoteWildfly(){
  ./mvnw clean install wildfly:deploy && google-chrome http://localhost:8080/petclinic
}

function deployManagedWildfly(){
  ./mvnw clean install wildfly:run -Pit-wildfly-managed && google-chrome http://localhost:8080/petclinic
}

function deployManagedLiberty(){
  ./mvnw clean install liberty:start -Pit-wildfly-managed && google-chrome http://localhost:8080/petclinic
}

function main(){
  #checkDependencies
  deployRemoteWildfly
  #deployManagedWildfly
  #deployManagedLiberty
}

main


