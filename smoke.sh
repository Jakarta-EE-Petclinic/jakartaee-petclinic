#!/usr/bin/env bash


function smokeTests01() {
	echo "smokeTests01"
	#PROFILES="setup qa wlp-dev wf-run"
	PROFILES="setup qa wlp-dev"
	for i in $PROFILES
	do
		LOGFILE="log/smoke_$i.log"
		echo $i
		./mvnw -P$i clean install | grep -v "INFO" > $LOGFILE
		cat $LOGFILE
	done
}

function smokeTests02() {
	echo "smokeTests02"
	#./mvnw -Psetup | grep -v "INFO"
	./mvnw -Pqa
	#./mvnw clean install | grep -v "INFO"
	#./mvnw -Pwlp-run clean install | grep -v "INFO"
	#./mvnw -Pwf-run clean install | grep -v "INFO"
}

function main() {
    #smokeTests01
    smokeTests02
}

main


