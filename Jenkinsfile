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
      }
    }

    stage('Deploy') {
      steps {
        sh 'pwd ; cd /var/lib/jenkins/workspace/javaee-memoapp2_master ; docker-compose up -d'
      }
    }

  }
}