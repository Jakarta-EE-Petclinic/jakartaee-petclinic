#!/usr/bin/env bash

source src/main/bash/run-lib.bash

function startRemoteServer(){
  startWithMessage " start remote AppServer"
  startRemoteAppServerWildfly17
  #startRemoteAppServerOpenLibertyWlp
  #startRemoteAppServerGlassfish51
  #startRemoteAppServerPayara
	doneAndReady
}

startRemoteServer
