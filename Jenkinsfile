pipeline {
  agent none
  stages {
    stage('DockerComposeBuilder') {
      agent any
      steps {
        sh 'ls'
        sh 'sudo docker-compose up -d'
      }
    }
  }
}
