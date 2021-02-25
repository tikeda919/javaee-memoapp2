pipeline {
  agent any
  stages {
    stage('APP IMAGE RECREATE') {
      steps {
        sh 'docker build -t my-tomcat-app-img .'
      }
    }

    stage('NETWORK CREATE') {
      when {
        expression {
          def NETWORK_NAME = sh(returnStdout: true, script: 'grep memoapp-network <(docker network ls --format "table {{.Name}}") || echo aaa').trim()
          print params.INPUT_NETWORK_NAME
          echo params.INPUT_NETWORK_NAME
          return !(NETWORK_NAME == params.INPUT_NETWORK_NAME)
        }

      }
      steps {
        sh '''echo params.INPUT_NETWORK_NAME
docker network create memoapp-network'''
      }
    }

    stage('RUN MYSQL') {
      when {
        expression {
          def MYSQL_CONTAINER = sh(returnStdout: true, script: 'grep memoapp-db <(docker ps -a --format "table {{.Names}}") || echo aaa').trim()
          print MYSQL_CONTAINER
          return !(MYSQL_CONTAINER == 'memoapp-db')
        }

      }
      steps {
        sh 'docker run --network memoapp-network --name memoapp-db -e MYSQL_DATABASE=memoapp_db -e MYSQL_USER=memoapp -e MYSQL_PASSWORD=memoapp -e MYSQL_RANDOM_ROOT_PASSWORD=yes -d mysql:5.7 --character-set-server=utf8'
      }
    }

    stage('STOP APPLICATION') {
      when {
        expression {
          def APP_CONTAINER = sh(returnStdout: true, script: 'grep my-tomcat-app <(docker ps --format "table {{.Names}}") || echo aaa').trim()
          print APP_CONTAINER
          return APP_CONTAINER == 'my-tomcat-app'
        }

      }
      steps {
        sh 'docker stop my-tomcat-app ; docker rm my-tomcat-app'
      }
    }

    stage('RUN APPLICATION') {
      steps {
        sh 'docker run --name my-tomcat-app --network memoapp-network -d -p 18082:8080 my-tomcat-app-img'
      }
    }

  }
  parameters {
    string(name: 'INPUT_NETWORK_NAME', defaultValue: 'memoapp-network', description: '')
  }
}