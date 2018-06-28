#!/bin/bash
cd cucumberselenium2library
mvn test -Dcucumber.options="--tags $1" -Dbrowser=$2 -DbaseURL=$3 -DremoteURL="$4" -q
