pipeline {
    agent any
    
    environment {
        // Define environment variables
        DOCKER_REGISTRY_CREDENTIALS = 'dockerhub-credentials'
        DOCKER_IMAGE_NAME = 'your-dockerhub-username/your-image-name'
    }
    
    stages {
        stage('Checkout') {
            steps {
                // Checkout the source code from GitHub
                git 'https://github.com/oshan35/FashionFitsMe-Backend.git'
            }
        }
        
        stage('Build and Test') {
            steps {
                // Build the Spring Boot application
                sh './gradlew build'
                
                // Run tests
                sh './gradlew test'
            }
        }
        
        stage('Docker Build and Push') {
            steps {
                // Docker build and push
                script {
                    // Build Docker image
                    docker.build(env.DOCKER_IMAGE_NAME)
                    
                    // Login to DockerHub
                    docker.withRegistry('https://index.docker.io/v1/', env.DOCKER_REGISTRY_CREDENTIALS) {
                        // Push Docker image to DockerHub
                        docker.image(env.DOCKER_IMAGE_NAME).push('latest')
                    }
                }
            }
        }
    }
    
    post {
        success {
            echo 'Pipeline succeeded! Docker image built and pushed.'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}

