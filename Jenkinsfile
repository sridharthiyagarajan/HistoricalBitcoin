pipeline {
    agent any

    environment {
        // Define any environment variables here if needed
        SPRING_BOOT_APP_DIR = './bitcoinservice'
        UI_APP_DIR = './bitcoinui'
    }

    stages {
        stage('Cleanup') {
            steps {
                script {
                    deleteDir()  // Deletes everything in the workspace
                }
            }
        }

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/sridharthiyagarajan/HistoricalBitcoin.git'
            }
        }

        stage('Build Spring Boot Application') {
            steps {
                script {
                    // Running Maven build for the Spring Boot application
                    sh 'mvn clean install -f ${SPRING_BOOT_APP_DIR}/pom.xml'
                }
            }
        }

        stage('Build Spring Boot Docker Image') {
            steps {
                sh 'docker build -t springboot-app:latest ${SPRING_BOOT_APP_DIR}'
            }
        }

        stage('Run Spring Boot Container') {
            steps {
                sh 'docker run -d -p 8080:8080 springboot-app:latest'
            }
        }

        stage('Build UI Docker Image') {
            steps {
                sh 'docker build -t ui-app:latest ${UI_APP_DIR}'
            }
        }

        stage('Run UI Container') {
            steps {
                sh 'docker run -d -p 3000:3000 ui-app:latest'
            }
        }
    }
}
