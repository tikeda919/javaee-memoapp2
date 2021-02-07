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

  }
}