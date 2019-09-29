#!/usr/bin/env bash

function checkDependencies() {
    ../mvnw clean dependency:tree dependency:resolve dependency:resolve-plugins
}

function deployDefaultWildfly(){
  ../mvnw clean install wildfly:deploy && google-chrome http://localhost:8080/petclinic
}

function main(){
  #checkDependencies
  deployDefaultWildfly
}

main

