pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                git 'https://github.com/sridharthiyagarajan/HistoricalBitcoin.git'
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
