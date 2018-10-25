pipeline {
    agent any
    tools { 
        maven 'Maven3.3.3' 
    }
    options {
      buildDiscarder(logRotator(numToKeepStr:'10'))
    }
    
    stages {
      stage('Build') {
          steps {
            sh 'mvn clean install'
          }
      }
      stage('Deploy') {
          steps {
              withCredentials([string(credentialsId: 'KEY_GPG_PASSPHRASE', variable: 'KEY_GPG_PASSPHRASE'),
                               usernamePassword(credentialsId: 'OSSRH', usernameVariable: 'OSSRH_USER', passwordVariable: 'OSSRH_PWD')]) {
                 sh 'mvn deploy'
              }
          }
      }
       stage('SonarQube analysis') {
          steps {
              withSonarQubeEnv('SonarMonext') {
                  sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.2:sonar'
              }
          }
      } 
    }
}
