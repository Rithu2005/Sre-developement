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

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonar-server') {
                    sh '''
                    mvn sonar:sonar \
                    -Dsonar.projectKey=autopilot-sre \
                    -Dsonar.host.url=http://host.docker.internal:9000 \
                    -Dsonar.login=$SONAR_AUTH_TOKEN
                    '''
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t autopilot-sre .'
            }
        }

        stage('Deploy Container') {
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