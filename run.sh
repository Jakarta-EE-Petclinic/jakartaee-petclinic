#!/usr/bin/env bash

mkdir -p log

source src/main/bash/run-lib.bash

function runManaged() {
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  runManagedWildfly $TESTS_PROFILE $BROWSER_PROFILE
  #runManagedLiberty $TESTS_PROFILE $BROWSER_PROFILE
  #runManagedGlassfish $TESTS_PROFILE $BROWSER_PROFILE
}

function runRemote() {
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  #runRemoteWildfly $TESTS_PROFILE $BROWSER_PROFILE
  runRemoteLiberty $TESTS_PROFILE $BROWSER_PROFILE
  #runRemoteGlassfish $TESTS_PROFILE $BROWSER_PROFILE
}

function testManaged() {
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  #testManagedWildfly $TESTS_PROFILE $BROWSER_PROFILE
  #testManagedLiberty $TESTS_PROFILE $BROWSER_PROFILE
  #testManagedGlassfish $TESTS_PROFILE $BROWSER_PROFILE
}

function testRemote() {
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  #testRemoteWildfly $TESTS_PROFILE $BROWSER_PROFILE
  #testRemoteLiberty $TESTS_PROFILE $BROWSER_PROFILE
  #testRemoteGlassfish $TESTS_PROFILE $BROWSER_PROFILE
}

function main(){
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  echo "-------------------"
  echo " main"
  echo "-------------------"
  #resolveDependencies $TESTS_PROFILE $BROWSER_PROFILE
  #runQa $TESTS_PROFILE $BROWSER_PROFILE
  #setSerialVersionId
  #runRemote $TESTS_PROFILE $BROWSER_PROFILE
  runManaged $TESTS_PROFILE $BROWSER_PROFILE
  #testManaged $TESTS_PROFILE $BROWSER_PROFILE
  #testRemote $TESTS_PROFILE $BROWSER_PROFILE
  echo "-------------------"
  echo " DONE and READY"
  echo "-------------------"
}

main $TESTS_PROFILE_SKIP $BROWSER_PROFILE_CHROME