pipeline {
  agent any
  stages {
    stage('Build') {
      agent {
        docker {
          image 'maven'
        }

      }
      steps {
        sh 'hostname'
        sh 'mvn package'
        archiveArtifacts 'target/*.war'
      }
    }

    stage('message test') {
      steps {
        input(message: 'test', ok: 'OK comment')
        sh 'pwd'
        sh 'ls -la'
      }
    }

    stage('Deploy') {
      steps {
        sh 'ls /usr/local/bin'
        sh 'docker-compose up -d'
      }
    }

  }
}