pipeline {
    agent any

    
    stages {
		stage ("build") {   
          steps {
                sh './gradlew clean build'
              }
        }
      
    stage ('Build docker image') {
          steps {
              sh 'docker build -t securityapp .'
          }
        }
      
    }
}
