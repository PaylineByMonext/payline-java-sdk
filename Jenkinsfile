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
      stage('SonarQube analysis') {
        steps {
            withSonarQubeEnv('SonarMonext') {
                sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
            }
        }
      }
      stage('Deploy') {
          steps {
              withCredentials([string(credentialsId: 'KEY_GPG_PASSPHRASE', variable: 'KEY_GPG_PASSPHRASE'),
                               usernamePassword(credentialsId: 'OSSRH', usernameVariable: 'OSSRH_USER', passwordVariable: 'OSSRH_PWD')]) {
                 sh 'mvn -Psign deploy'
              }
          }
      }
    }
}
