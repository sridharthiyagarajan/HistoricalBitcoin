pipeline {
    agent any

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

        stage('Build UI Docker Image') {
            steps {
                sh 'docker build -t ui-app:latest ./bitcoinui'
            }
        }

        stage('Run UI Container') {
            steps {
                sh 'docker run -d -p 3000:3000 ui-app:latest'
            }
        }
    }
}
