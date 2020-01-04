#!/usr/bin/env bash

mkdir -p log

source src/main/bash/run-lib.bash

function dockerPostgreSqlForOpenLibertyDeploy() {
	#sudo docker stack rm JAKARTAEE_PETCLINIC
	sudo docker stack deploy --compose-file stack.yml JAKARTAEE_PETCLINIC
	sudo docker stack services JAKARTAEE_PETCLINIC
	sudo docker stack ps JAKARTAEE_PETCLINIC
}

function dockerPostgreSqlForOpenLibertyRemove() {
	sudo docker stack rm JAKARTAEE_PETCLINIC
	sudo docker stack services JAKARTAEE_PETCLINIC
	sudo docker stack ps JAKARTAEE_PETCLINIC
}

function runDev(){
  BROWSER_PROFILE=$1
  startWithMessage " RUN liberty:dev"
  #dockerPostgreSqlForOpenLibertyDeploy
  runManagedLibertyDev $BROWSER_PROFILE
  #dockerPostgreSqlForOpenLibertyRemove
  doneAndReady
}


runDev $BROWSER_PROFILE_CHROME