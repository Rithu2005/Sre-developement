pipeline {
    agent any

    tools {
        maven 'Maven-3'
    }

    stages {

        stage('Build Jar') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t autopilot-sre .'
            }
        }

        stage('Run Container') {
            steps {
                sh '''
                docker stop autopilot-backend || true
                docker rm autopilot-backend || true
                docker run -d --name autopilot-backend -p 9091:8080 autopilot-sre
                '''
            }
        }
    }
}