#!/usr/bin/env bash

mkdir -p log

source src/main/bash/run-lib.bash

main $TESTS_PROFILE_SKIP $BROWSER_PROFILE_CHROME