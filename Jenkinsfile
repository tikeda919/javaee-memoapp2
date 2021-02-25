pipeline {
  agent any
  stages {
    stage('IMAGE CREATE') {
      steps {
        sh 'docker build -t my_tomcat_app .'
        sh 'docker network ls |grep memoapp-network |awk \'{print$2}\''
      }
    }

    stage('NETWORK CREATE') {
      when {
        expression {
          def NETWORK_NAME = sh(returnStdout: true, script: 'awk \'{print$2}\' <(grep memoapp-network <(docker network ls))').trim()
          return !(NETWORK_NAME == 'memoapp-network')
        }

      }
      steps {
        sh 'docker network create memoapp-network'
      }
    }

    stage('RUN MYSQL') {
      when {
        expression {
          def MYSQL_CONTAINER = sh(returnStdout: true, script: 'cut -f 1 -d ":" <(awk \'{print$2}\' <(grep mysql <(docker ps -a)))').trim()
          return !(MYSQL_CONTAINER == 'mysql')
        }

      }
      steps {
        sh 'docker run --network memoapp-network --name memoapp-db -e MYSQL_DATABASE=memoapp_db -e MYSQL_USER=memoapp -e MYSQL_PASSWORD=memoapp -e MYSQL_RANDOM_ROOT_PASSWORD=yes -d mysql:5.7 --character-set-server=utf8'
      }
    }

    stage('RUN APPLICATION') {
      when {
        expression {
          def APP_CONTAINER = sh(returnStdout: true, 'awk \'{print$2}\' <(grep my_tomcat_app <(docker ps -a))').trim()
          return !(APP_CONTAINER == 'my_tomcat_app')
        }

      }
      steps {
        sh 'docker run --network memoapp-network -d -p 18082:8080 my_tomcat_app'
      }
    }

  }
}