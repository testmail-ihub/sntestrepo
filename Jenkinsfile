pipeline {
    agent any

    options {
        // Trigger pipeline on pull request events
        triggerOnPullRequest(true)
        // Disable this project for new jobs
        disableConcurrentBuilds()
    }

    environment {
        // Define environment variables
        GRADLE_OPTS = '-Xmx1024m -Dhttp.proxyHost=your-proxy-host -Dhttp.proxyPort=your-proxy-port'
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the repository
                checkout scm
            }
        }
        stage('Build Project') {
            steps {
                // Build the project using Gradle
                sh './gradlew build'
            }
        }
        stage('Run Selenium Tests') {
            steps {
                // Run Selenium tests
                sh './gradlew test'
            }
        }
        stage('Report Results') {
            steps {
                // Publish test results
                junit 'build/test-results/**/*.xml'
                // Archive test reports
                archiveArtifacts artifacts: 'build/reports/**', allowEmptyArchive: true
            }
        }
    }
    post {
        // Send notifications on failure
        failure {
            // Send email notification on failure
            mail to: 'your-email@example.com',
                subject: 'Jenkins Pipeline Failed: ${env.JOB_NAME}',
                body: 'Pipeline failed for ${env.JOB_NAME} on ${env.BUILD_URL}',
                from: 'jenkins@your-domain.com'
        }
    }
}