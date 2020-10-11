#!/usr/bin/env bash

function smokeTestProfiles() {
	echo "smokeTestProfiles"
	TEST_CATEGORY=$1
	PROFILES=$2
	for i in $PROFILES
	do
		echo "===================================="
		LOGFILE="log/smoke-$TEST_CATEGORY-$i.log"
		echo "------------------------------------"
		echo $TEST_CATEGORY
		echo $i
		echo "------------------------------------"
		echo $LOGFILE
		./mvnw -P$i dependency:purge-local-repository clean install > $LOGFILE
		echo "------------------------------------"
		cat $LOGFILE | grep -v "INFO"
		echo "------------------------------------"
		cat $LOGFILE | grep "BUILD SUCCESS"
	 	echo "------------------------------------"
	 	echo $LOGFILE
	 	echo "------------------------------------"
	done
}

function smokeTestAll() {
	smokeTestProfiles "ALL" "setup qa wlp-dev wlp-run wf-test wlp-run-remote wlp-test-remote wf-run wf-test wf-remote-run wf-remote-test"
}

function smokeTestRegression() {
	smokeTestProfiles "Regression" "setup qa wlp-dev"
}

function smokeTestWorkInProgress() {
	smokeTestProfiles "WorkInProgress" "wlp-run wf-run"
}

function smokeTestsAdHoc() {
	echo "smokeTestsAdHoc"
	#./mvnw -Psetup
	#./mvnw -Pqa
	#./mvnw clean install | grep -v "INFO"
	#./mvnw -Pwlp-run clean install | grep -v "INFO"
	#./mvnw -Pwf-run clean install | grep -v "INFO"
}

function smoke() {
	smokeTestRegression
	smokeTestWorkInProgress
}

function main() {
		#smokeTestAll
		smoke
		#smokeTestsAdHoc
}

main
