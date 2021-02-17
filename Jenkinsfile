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
        sh 'pwd'
        sh 'ls -la'
        sh 'whoami'
      }
    }

    stage('Deploy') {
      steps {
        sh 'sudo whoami'
      }
    }

  }
}