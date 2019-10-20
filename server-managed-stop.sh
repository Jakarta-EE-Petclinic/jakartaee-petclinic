#!/usr/bin/env bash

mkdir -p log

source src/main/bash/run-lib.bash

function stopManaged(){
  BROWSER_PROFILE=$1
  startWithMessage " stop Managed Server"
  #stopManagedWildfly $BROWSER_PROFILE
  stopManagedLiberty $BROWSER_PROFILE
  doneAndReady
}

stopManaged $BROWSER_PROFILE_CHROME