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
        sh 'docker build -t memoapp .'
      }
    }

    stage('DB') {
      steps {
        sh 'docker run --network memoapp-network --name memoapp-db -e MYSQL_DATABASE=memoapp_db -e MYSQL_USER=memoapp -e MYSQL_PASSWORD=memoapp -e MYSQL_RANDOM_ROOT_PASSWORD=yes -d mysql:5.7 --character-set-server=utf8'
      }
    }

  }
}