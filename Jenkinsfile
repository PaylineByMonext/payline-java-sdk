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
                echo '-----'
                echo 'Build with MAVEN version'
                sh 'mvn --version'
                sh 'mvn clean package'
            }
        }

        stage('Tests') {
            steps {
                sh "mvn test"
            }
            post {
                always {
                    catchError {
                        junit '**/target/surfire-reports/**/*.xml'
                    }
                }
            }
        }

        stage('SonarQube') {
            steps {
                withSonarQubeEnv('SonarMonext') {
                    script {
                        if (BRANCH_NAME == 'master') {
                            sh 'mvn sonar:sonar -Dsonar.branch.name=${BRANCH_NAME}'
                        }
                        if (BRANCH_NAME != 'master') {
                            sh 'mvn sonar:sonar -Dsonar.branch.name=${BRANCH_NAME} -Dsonar.branch.target=master'
                        }
                    }
                }
            }
        }

//        stage('Deploy') {
//            when { branch 'master' }
//            steps {
//                withCredentials([string(credentialsId: 'KEY_GPG_PASSPHRASE', variable: 'KEY_GPG_PASSPHRASE'),
//                                 usernamePassword(credentialsId: 'OSSRH', usernameVariable: 'OSSRH_USER', passwordVariable: 'OSSRH_PWD')]) {
//                    sh 'mvn -Psign deploy'
//                }
//            }
//        }
    }
}
