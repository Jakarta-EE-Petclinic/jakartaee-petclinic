#!/usr/bin/env bash

mkdir -p log

source src/main/bash/run-lib.bash

function runManaged(){
  BROWSER_PROFILE=$1
  startWithMessage " RUN Managed Server"
  #runManagedWildfly $BROWSER_PROFILE
  runManagedLiberty $BROWSER_PROFILE
  #runManagedGlassfish $BROWSER_PROFILE
  #runManagedPayara $BROWSER_PROFILE
  doneAndReady
}

runManaged $BROWSER_PROFILE_CHROME