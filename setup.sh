#!/usr/bin/env bash

echo "./mvnw -Psetup"
./mvnw -Psetup > x.txt

echo "deprecated"
grep -n "deprecated" x.txt > x_deprecated.txt
echo "ERROR"
grep -n "ERROR" x.txt > x_ERROR.txt
echo "WARNING"
grep -n "WARNING" x.txt > x_WARNING.txt
echo "Caused by"
grep -n "Caused by" x.txt > x_Caused_by.txt
echo "ArtifactResolutionException"
grep -n "ArtifactResolutionException" x.txt > x_ArtifactResolutionException.txt

echo "FINISHED"