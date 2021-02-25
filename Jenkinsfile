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
          sh(returnStdout: true, script: 'dogger network ls')
          GIT_BRANCH = 'origin/' + sh(returnStdout: true, script: 'git rev-parse --abbrev-ref HEAD').trim()
          print NETWORK_NAME
          sh 'echo ${GIT_BRANCH}'
          return !(NETWORK_NAME == 'memoapp-network')
        }

      }
      steps {
        sh 'docker network create memoapp-network'
      }
    }

    stage('RUN MYSQL') {
      steps {
        sh 'echo hello'
      }
    }

    stage('RUN APPLICATION') {
      steps {
        sh 'docker run --network memoapp-network -d -p 18082:8080 my_tomcat_app | echo "ignore failure"'
      }
    }

    stage('test') {
      when {
        expression {
          def result = sh (
            script: "docker run --network memoapp-network --name memoapp-db -e MYSQL_DATABASE=memoapp_db -e MYSQL_USER=memoapp -e MYSQL_PASSWORD=memoapp -e MYSQL_RANDOM_ROOT_PASSWORD=yes -d mysql:5.7 --character-set-server=utf8",
            returnStatus: true
          )
        }

      }
      steps {
        sh 'echo HelloWorld'
      }
    }

  }
  parameters {
    booleanParam(defaultValue: false, description: '', name: 'SUCCESS')
  }
}