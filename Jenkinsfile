pipeline {
  agent any
  stages {
    stage('STOP APPLICATION') {
      when {
        expression {
          def APP_CONTAINER = sh(returnStdout: true, script: 'grep my-tomcat-app <(docker ps --format "table {{.Names}}") || echo param.GREP_FALSE').trim()
          print APP_CONTAINER
          return APP_CONTAINER == params.INPUT_APP_CONTAINER
        }

      }
      steps {
        sh "docker stop ${params.INPUT_APP_CONTAINER} ; docker rm ${params.INPUT_APP_CONTAINER} ; docker rmi ${params.INPUT_APP_IMAGE_NAME} "
      }
    }

    stage('APP IMAGE RECREATE') {
      steps {
        sh 'docker build -t my-tomcat-app-img --no-cache .'
      }
    }

    stage('NETWORK CREATE') {
      when {
        expression {
          def NETWORK_NAME = sh(returnStdout: true, script: 'grep memoapp-network <(docker network ls --format "table {{.Name}}") || echo param.GREP_FALSE').trim()
          print params.INPUT_NETWORK_NAME
          echo params.INPUT_NETWORK_NAME
          return !(NETWORK_NAME == params.INPUT_NETWORK_NAME)
        }

      }
      steps {
        sh 'docker network create memoapp-network'
        sh "echo ------------------------------NETWORK IS CREATED AS ${params.INPUT_NETWORK_NAME}------------------------------"
      }
    }

    stage('RUN MYSQL') {
      when {
        expression {
          def MYSQL_CONTAINER = sh(returnStdout: true, script: 'grep memoapp-db <(docker ps -a --format "table {{.Names}}") || echo param.GREP_FALSE').trim()
          print MYSQL_CONTAINER
          return !(MYSQL_CONTAINER == params.INPUT_MYSQL_CONTAINER)
        }

      }
      steps {
        sh 'docker run --network memoapp-network --name memoapp-db -e MYSQL_DATABASE=memoapp_db -e MYSQL_USER=memoapp -e MYSQL_PASSWORD=memoapp -e MYSQL_RANDOM_ROOT_PASSWORD=yes -d mysql:5.7 --character-set-server=utf8'
        sh "echo ------------------------------DATABASE IS CREATED AS ${params.INPUT_MYSQL_CONTAINER}------------------------------"
      }
    }

    stage('RUN APPLICATION') {
      steps {
        sh 'docker run --name my-tomcat-app --network memoapp-network -d -p 18082:8080 my-tomcat-app-img'
        sh "echo ------------------------------APPLICATION IS CREATED AS ${params.INPUT_APP_CONTAINER}------------------------------"
      }
    }

    stage('test') {
      steps {
        script {
          String retStd = sh(returnStatus: true, script: 'docker images |grep \'<none>\' || echo param.GREP_FALSE').trim()
          sh "echo ---------retStd:${retStd}---------"

          String aaa = sh(returnStdout: true, script: 'ls |wc -l').trim()

          sh "echo ---------aaa:${aaa}---------"
          if(Integer.parseInt(aaa) > 1){
            sh "echo ${aaa} is true"
          } else {
            sh 'echo false'
          }
        }

        sh 'echo hahaha'
      }
    }

  }
  parameters {
    string(name: 'INPUT_NETWORK_NAME', defaultValue: 'memoapp-network', description: '')
    string(name: 'INPUT_MYSQL_CONTAINER', defaultValue: 'memoapp-db', description: '')
    string(name: 'INPUT_APP_CONTAINER', defaultValue: 'my-tomcat-app', description: '')
    string(name: 'INPUT_APP_IMAGE_NAME', defaultValue: 'my-tomcat-app-img', description: '')
    string(name: 'GREP_FALSE', defaultValue: 'false', description: '')
  }
}