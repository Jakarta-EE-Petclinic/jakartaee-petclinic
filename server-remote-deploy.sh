#!/usr/bin/env bash

source src/main/bash/run-lib.bash

function deployRemote() {
  startWithMessage " start remote AppServer"
  BROWSER_PROFILE=$1
  runRemoteWildfly $BROWSER_PROFILE
  #runRemoteLiberty $BROWSER_PROFILE
  #runRemoteGlassfish $BROWSER_PROFILE
  #runRemotePayara $BROWSER_PROFILE
  doneAndReady
}

deployRemote
