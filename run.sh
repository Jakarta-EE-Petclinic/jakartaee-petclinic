#!/usr/bin/env bash

mkdir -p log

source src/main/bash/run-lib.bash

BROWSER_PROFILE1=it-browser-chrome
BROWSER_PROFILE2=it-browser-firefox
BROWSER_PROFILE3=it-browser-safari
BROWSER_PROFILE4=it-browser-opera
BROWSER_PROFILE5=it-browser-htmlunit
BROWSER_PROFILE6=it-browser-phantomjsdriver

TESTS_PROFILE1=it-skip-tests
TESTS_PROFILE2=it-run-tests

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
  #runRemoteWildfly $TESTS_PROFILE $BROWSER_PROFILE
  runRemoteLiberty $TESTS_PROFILE $BROWSER_PROFILE
  #runRemoteGlassfish $TESTS_PROFILE $BROWSER_PROFILE
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
  runRemote  $TESTS_PROFILE $BROWSER_PROFILE
  #runManaged  $TESTS_PROFILE $BROWSER_PROFILE
  echo "-------------------"
  echo " DONE and READY"
  echo "-------------------"
}

main $TESTS_PROFILE1 $BROWSER_PROFILE1