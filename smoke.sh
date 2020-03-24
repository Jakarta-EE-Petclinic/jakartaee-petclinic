#!/usr/bin/env bash


function smokeTests01() {
	echo "smokeTests01"
	#PROFILES="setup qa wlp-dev wf-run"
	PROFILES="setup qa wlp-dev"
	for i in $PROFILES
	do
		echo "===================================="
		LOGFILE="log/smoke_$i.log"
		echo $i
		echo "------------------------------------"
		echo $LOGFILE
		./mvnw -P$i clean install > $LOGFILE
		echo "------------------------------------"
		cat $LOGFILE | grep -v "INFO"
		echo "------------------------------------"
		cat $LOGFILE | grep "BUILD SUCCESS"
	 	echo "------------------------------------"
	 	echo $LOGFILE
	 	echo "------------------------------------"
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
    smokeTests01
    #smokeTests02
}

main


