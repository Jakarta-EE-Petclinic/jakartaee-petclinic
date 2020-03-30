#!/usr/bin/env bash

MY_COMMAND_1="./mvnw clean install site site:deploy liberty:run"
MY_COMMAND_2="./mvnw"
MY_COMMAND_3="./mvnw -Pwf-run"
MY_COMMAND_4="./mvnw -Pwlp-run"


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
	MY_COMMAND=$MY_COMMAND_4
	echo "$MY_COMMAND"
	$MY_COMMAND
}

function runAllProfiles(){
	$MY_COMMAND_2
	$MY_COMMAND_3
	$MY_COMMAND_4
}

function main() {
	runCommand
	#runAllProfiles
}

main