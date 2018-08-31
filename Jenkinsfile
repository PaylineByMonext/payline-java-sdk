pipeline {
    agent any

    options {
      buildDiscarder(logRotator(numToKeepStr:'10'))
    }
    
    stages {
      stage('Build') {
          steps {
              gitlabCommitStatus("Assemble") {
                  sh 'mvn clean install'
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
