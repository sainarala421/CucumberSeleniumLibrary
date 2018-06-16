#!/usr/bin/env groovy
/*
 * This Jenkinsfile is intended to run on https://ci.jenkins.io and may fail anywhere else.
 * It makes assumptions about plugins being installed, labels mapping to nodes that can build what is needed, etc.
 *
 * The required labels are "java" and "docker" - "java" would be any node that can run Java builds. It doesn't need
 * to have Java installed, but some setups may have nodes that shouldn't have heavier builds running on them, so we
 * make this explicit. "docker" would be any node with docker installed.
 */

 pipeline {
    agent {
        label 'master'
    }
    parameters {
       string (name: 'TEST_SUITE_PATH', defaultValue: 'cucumberselenium2library',
              description: 'Path of cucumber tests.')

       string (name: 'TEST_TAGS', defaultValue: '@AvailabilityTest',
              description: 'Test tags, comma separated.')

      string (name: 'SONAR_SCANNER_PATH', defaultValue: '/usr/local/bin',
              description: 'sonar-scanner path on agent')
    }
    /*environment {
        ADMIN_USER = credentials('admin-user')
    } */
    stages {
        stage ("Stage 1: Get agent details") {
            steps {
                sh '''#!/bin/bash
                    python --version
                    whoami
                    sw_vers
                    pwd
                    ls -l
                '''
            }
        }
        /*
        stage ("Stage 2 checkout SCM"){
            steps{
                checkout SCM
            }
        }*/
        stage ('Stage 3: Code analysis.') {
            steps {
                withSonarQubeEnv('Local SonarQube') {
                    // sq-scanner value set in Jenkins > Global Tool Configuration seems to not work
                    sh '''#!/bin/bash
                        cd CucumberSelenium2Library
                        mvn clean
                        $SONAR_SCANNER_PATH/sonar-scanner
                    '''
                }
                timeout(time:5, unit:'MINUTES') {
                    script {
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            error "Pipeline aborted due to quality gate failure: ${qg.status}"
                        }
                    }
                }
            }
        }
        stage ("Stage 4: Run Maven Tests") {
            steps {
                sh '''#!/bin/bash
                    cd CucumberSelenium2Library/$TEST_SUITE_PATH
                    mvn clean
                    mvn test -Dcucumber.options="--tags $TEST_TAGS"
                '''
            }
        }
    }
    post {
        always {
            publishHTML([
                allowMissing: false, 
                alwaysLinkToLastBuild: false, 
                keepAll: true,
                alwaysLinkToLastBuild: true,
                reportDir: 'CucumberSelenium2Library/cucumberselenium2library/target/surefire-reports', 
                reportFiles: 'index.html', 
                reportName: 'HTML Report', 
                reportTitles: ''
            ])
        }
    }

}
