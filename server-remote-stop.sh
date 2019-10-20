#!/usr/bin/env bash

source src/main/bash/run-lib.bash

function stopRemoteServer(){
  startWithMessage " stop remote AppServer"
  stopRemoteAppServerWildfly17
  #stopRemoteAppServerOpenLibertyWlp
  #stopRemoteAppServerGlassfish51
  #stopRemoteAppServerPayara
  doneAndReady
}

stopRemoteServer $BROWSER_PROFILE_CHROME