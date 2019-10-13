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

function main(){
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  echo "-------------------"
  echo " main"
  echo "-------------------"
  #resolveDependencies $TESTS_PROFILE $BROWSER_PROFILE
  #runQa $TESTS_PROFILE $BROWSER_PROFILE
  setSerialVersionId
  #runRemote  $TESTS_PROFILE $BROWSER_PROFILE
  #runManaged  $TESTS_PROFILE $BROWSER_PROFILE
  echo "-------------------"
  echo " DONE and READY"
  echo "-------------------"
}

main $TESTS_PROFILE1 $BROWSER_PROFILE1