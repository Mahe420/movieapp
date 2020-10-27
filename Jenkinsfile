pipeline {
  agent none
  stages {
    stage('DockerComposeBuilder') {
      agent any
      steps {
        sh 'sudo /usr/local/bin/docker-compose up -d'
      }
    }
  }
}
