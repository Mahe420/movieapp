pipeline {
  agent none
  stages {
    stage('DockerComposeBuilder') {
      agent any
      steps {
        sh 'sudo docker-compose up -d'
      }
    }
  }
}
