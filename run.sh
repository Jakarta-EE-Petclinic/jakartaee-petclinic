#!/usr/bin/env bash

MY_COMMAND_NO_DOCKER__WLP_DEV="./mvnw -P liberty-dev clean install liberty:dev"
MY_COMMAND_NO_DOCKER__WLP_RUN="./mvnw -Pliberty-run clean install liberty:run"
MY_COMMAND_NO_DOCKER__WILDFLY_RUN="./mvnw -Pwildfly-run clean install wildfly:run"


MY_COMMAND__WLP_DEV="./mvnw"
MY_COMMAND__WLP_RUN="./mvnw -Pwlp-run"
MY_COMMAND__WILDFLY_RUN="./mvnw -Pwildfly-run clean install wildfly:run"


function dockerUp() {
    ./mvnw docker-compose:up
}

function dockerDown() {
    ./mvnw docker-compose:down
}

function siteAll() {
    ./mvnw site site:deploy
    ./mvnw -Pwf-run site site:deploy
    ./mvnw -Pwlp-run site site:deploy
}

function runCommand(){
	MY_COMMAND=$MY_COMMAND_WILDFLY_RUN
	echo "$MY_COMMAND"
	$MY_COMMAND
}

function runAllProfiles(){
	$MY_COMMAND_NO_DOCKER__WLP_DEV
	#$MY_COMMAND_NO_DOCKER__WLP_RUN
	#$MY_COMMAND_NO_DOCKER__WILDFLY_RUN
	#$MY_COMMAND_WLP_DEV
	#$MY_COMMAND_WLP_RUN
	#dockerDown
}

function firstSetup() {
    ./mvnw -Pliberty-dev dpendency:purge-local-repository clean install -DskipTests=true
    ./mvnw -Pliberty-run dpendency:purge-local-repository clean install -DskipTests=true
    ./mvnw -Pwildfly-run dpendency:purge-local-repository clean install -DskipTests=true

    ./mvnw -Pliberty-dev dpendency:purge-local-repository clean install site -DskipTests=true
    ./mvnw -Pliberty-run dpendency:purge-local-repository clean install site -DskipTests=true
    ./mvnw -Pwildfly-run dpendency:purge-local-repository clean install site -DskipTests=true
}

function main() {
	#runCommand
	#runAllProfiles
	firstSetup
}

main
