#!/bin/bash
cd cucumberselenium2library
mvn test -Dcucumber.options="--tags @EndToEndTest" -Dbrowser=firefox -DbaseURL=http://automationpractice.com -DremoteURL="False" -q
