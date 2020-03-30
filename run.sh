#!/usr/bin/env bash

MY_COMMAND_1="./mvnw clean install site site:deploy liberty:run"
MY_COMMAND_2="./mvnw"
MY_COMMAND_3="./mvnw -Pwf-run"
MY_COMMAND_4="./mvnw -Pwlp-run"

MY_COMMAND=$MY_COMMAND_4

echo "$MY_COMMAND"
$MY_COMMAND

