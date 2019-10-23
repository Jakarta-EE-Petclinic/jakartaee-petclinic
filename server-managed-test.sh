#!/usr/bin/env bash

mkdir -p log

source src/main/bash/run-lib.bash

function testManaged(){
  BROWSER_PROFILE=$1
  startWithMessage " TEST Managed Server"
  testManagedWildfly $BROWSER_PROFILE
  #testManagedLiberty $BROWSER_PROFILE
  #testManagedGlassfish $BROWSER_PROFILE
  #testManagedPayara $BROWSER_PROFILE
  doneAndReady
}

testManaged $BROWSER_PROFILE_CHROME