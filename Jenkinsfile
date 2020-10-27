pipeline {
  agent none
  stages {
    stage('Docker Build') {
      agent any
      steps {
        sh 'docker-compose up -d'
      }
    }
  }
}
