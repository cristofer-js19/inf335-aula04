pipeline {
    agent {
        docker { image 'maven:3.9-eclipse-temurin-17' }
    }

    stages {
        stage('Build') {
            steps {
                echo 'Compilando o projeto...'
                sh 'mvn -B compile'
            }
        }

        stage('Test') {
            steps {
                echo 'Executando testes JUnit...'
                sh 'mvn -B test'
            }
        }

        stage('Package') {
            steps {
                echo 'Gerando o arquivo JAR...'
                sh 'mvn -B package'
            }
        }
    }

    post {
        always {
            echo 'Pipeline finalizado. Limpando o workspace...'
            cleanWs()
        }
    }
}