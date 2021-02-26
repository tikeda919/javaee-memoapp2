pipeline {
  agent any
  stages {
    stage('STOP APPLICATION') {
      when {
        expression {
          def APP_CONTAINER = sh(returnStdout: true, script: "docker ps --format \"{{.Names}}\" --filter \"name=${params.INPUT_APP_CONTAINER}\"").trim()
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
          def NETWORK_NAME = sh(returnStdout: true, script: 'docker network ls --format "{{.Name}}" --filter "name=${params.INPUT_NETWORK_NAME}"').trim()
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

    stage('DELETE DANGLING IMAGES') {
      steps {
        script {
          def retCnt = sh(returnStdout: true, script: 'docker images --filter dangling=true --format "{{.ID}}" | wc -l').trim()
          sh "echo ------------------------------retCnt:${retCnt}------------------------------"

          /*
          String aaa = sh(returnStdout: true, script: 'ls |wc -l').trim()
          sh "echo ---------aaa:${aaa}---------"
          */
          if(Integer.parseInt(retCnt) > 0){
            sh 'docker system prune -f --volumes'
            sh "echo ------------------------------${retCnt} OF DANGLING IMAGES ARE DELETED------------------------------"
          } else {
            sh "echo ------------------------------DANGLING IMAGE IS NOTHING------------------------------"
          }
        }

        sh 'docker system prune -f --volumes'
      }
    }

  }
  environment {
    INPUT_NETWORK_NAME = 'memoapp-network'
    INPUT_MYSQL_CONTAINER = 'memoapp-db'
    INPUT_APP_CONTAINER = 'my-tomcat-app'
    INPUT_APP_IMAGE_NAME = 'my-tomcat-app-img'
  }
  parameters {
    string(name: 'GREP_FALSE', defaultValue: 'false', description: '')
  }
}