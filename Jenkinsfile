pipeline {
  agent {
    docker {
      image 'maven'
    }

  }
  stages {
    stage('Build') {
      steps {
        sh 'pwd'
        sh 'mvn package'
        archiveArtifacts 'target/*.war'
      }
    }

    stage('message test') {
      steps {
        input(message: 'test', ok: 'OK comment')
      }
    }

    stage('Deploy') {
      steps {
        sh 'docker-compose up -d'
      }
    }

  }
}