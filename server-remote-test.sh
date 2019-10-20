#!/usr/bin/env bash

source src/main/bash/run-lib.bash

function testRemote() {
  startWithMessage " TEST Managed Server"
  BROWSER_PROFILE=$1
  #testRemoteWildfly $BROWSER_PROFILE
  #testRemoteLiberty $BROWSER_PROFILE
  #testRemoteGlassfish $BROWSER_PROFILE
  #testRemotePayara $BROWSER_PROFILE
  doneAndReady
}

testRemote $BROWSER_PROFILE_CHROME
