pipeline {
    agent any

    environment {
        MAVEN_CMD = 'mvn'
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }
        stage('Build with Maven') {
            steps {
                sh "${MAVEN_CMD} clean package"
            }
        }
        stage('Run Selenium Tests') {
            steps {
                sh "${MAVEN_CMD} test"
            }
        }
        stage('Report Results') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}