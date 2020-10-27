pipeline {
  agent none
  stages {
    stage('DockerComposeBuilder') {
      agent any
      steps {
        sh '/usr/local/bin/docker-compose up -d'
      }
    }
  }
}
