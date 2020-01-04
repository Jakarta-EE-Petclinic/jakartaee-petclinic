#!/usr/bin/env bash

mkdir -p log

source src/main/bash/run-lib.bash

function dockerOpenLiberty() {
	#sudo docker stack rm JAKARTAEE_PETCLINIC
	sudo docker stack deploy --compose-file stack.yml JAKARTAEE_PETCLINIC
	sudo docker stack services JAKARTAEE_PETCLINIC
	sudo docker stack ps JAKARTAEE_PETCLINIC
}

function dockerOpenLibertyRemove() {
	sudo docker stack rm JAKARTAEE_PETCLINIC
	sudo docker stack services JAKARTAEE_PETCLINIC
	sudo docker stack ps JAKARTAEE_PETCLINIC
}

function runManaged(){
  BROWSER_PROFILE=$1
  startWithMessage " RUN Managed Server"
  runManagedWildfly $BROWSER_PROFILE
  #runManagedLiberty $BROWSER_PROFILE
  #runManagedGlassfish $BROWSER_PROFILE
  #runManagedPayara $BROWSER_PROFILE
  doneAndReady
}

dockerOpenLiberty
runManaged $BROWSER_PROFILE_CHROME