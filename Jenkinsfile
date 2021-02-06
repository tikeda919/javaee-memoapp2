pipeline {
  agent {
    docker {
      image 'maven'
    }

  }
  stages {
    stage('Build') {
      parallel {
        stage('Build') {
          steps {
            sh 'mvn package'
          }
        }

        stage('whoami') {
          steps {
            sh 'whoami'
          }
        }

      }
    }

  }
}