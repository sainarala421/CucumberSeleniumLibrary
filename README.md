# Cucumber Selenium2library 
:exclamation: Installation documentation is for MacOS. Installing in Ubuntu or Windows, kindly troubleshoot according to the error message on installation

## Prerequisites
  - Text Editor / IDE
    - SublimeText or Eclipse
  - Softwares / Apps
    - git
    - java
    - maven
    - jenkins
    - docker
    - docker-selenium
    - sonarqube

## Environment Variables
_Note: Add the following in ~/.bash_profile. Replace the values with the correct version of the software you are using._

``` bash
# Maven
export M2_HOME="/etc/apache-maven-3.5.3/bin"
export PATH=$PATH:$M2_HOME

# Java
export JAVA_HOME_8_X64="/usr/bin/java"
export PATH=$PATH:$JAVA_HOME_8_X64

# Web drivers
export CHROMEDRIVER="/usr/local/bin/chromedriver"
export PATH=$PATH:$CHROMEDRIVER

export GECKODRIVER="/usr/local/bin/geckodriver"
export PATH=$PATH:$GECKODRIVER
``` 

## Clone this repository 
``` bash
$ git clone https://github.com/enhanceTAfrancis/CucumberSelenium2Library.git
```

## Running docker selenium remote url
_Note: cd to the `dockerfiles` directory inside this workspace, then run the following. Note that the docker must be installed and running. See documentation for_ [docker-selenium](https://github.com/SeleniumHQ/docker-selenium)
``` bash
$ docker-compose -f df_dockercompose.yml up
```

## Run the sample tests
_Note: Play with this maven script by changing the values of the browser, baseURL and remoteURL. You may remove -q and replace it with -X for debugging, or simply remove it._
``` bash
$ ## Running tests locally, set DremoteURL="False":
$ mvn test -Dcucumber.options="--tags @EndToEndTest" -Dbrowser=firefox -DbaseURL=http://automationpractice.com -DremoteURL="False" -q

$ ## Running tests remotely,:
$ mvn test -Dcucumber.options="--tags @EndToEndTest" -Dbrowser=chrome -DbaseURL=http://automationpractice.com -q
```
