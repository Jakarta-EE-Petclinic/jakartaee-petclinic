#!/usr/bin/env bash

mkdir -p log

source src/main/bash/run-lib.bash

function main(){
  TESTS_PROFILE=$1
  BROWSER_PROFILE=$2
  echo "-------------------"
  echo " main"
  echo "-------------------"
  stopManagedWildfly $TESTS_PROFILE $BROWSER_PROFILE
  echo "-------------------"
  echo " DONE and READY"
  echo "-------------------"
}

main $TESTS_PROFILE_SKIP $BROWSER_PROFILE_CHROME