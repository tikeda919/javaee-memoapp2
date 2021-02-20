pipeline {
  agent any
  stages {
    stage('IMAGE CREATE') {
      steps {
        sh 'docker build -t my_tomcat_app .'
      }
    }

    stage('NETWORK CREATE') {
      steps {
        sh 'docker network create memoapp-network'
      }
    }

    stage('RUN MYSQL') {
      steps {
        sh 'docker run --network memoapp-network --name memoapp-db -e MYSQL_DATABASE=memoapp_db -e MYSQL_USER=memoapp -e MYSQL_PASSWORD=memoapp -e MYSQL_RANDOM_ROOT_PASSWORD=yes -d mysql:5.7 --character-set-server=utf8'
      }
    }

    stage('RUN APPLICATION') {
      steps {
        sh 'docker run --network memoapp-network -d -p 18082:8080 my_tomcat_app'
      }
    }

  }
}