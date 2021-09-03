def mysqlHost = 'punedevopsa56.conygre.com'
def projectName = 'team4d'
def version = "0.0.${currentBuild.number}"
def dockerImageTag = "${projectName}:${version}"

pipeline {
  agent any
 
  stages {
    stage('Test') {
      steps {
        sh 'chmod a+x mvnw'
        sh './mvnw clean test'
      }
    }

    stage('Build') {
      steps {
        sh './mvnw package'
      }
    }

    stage('Build Container') {
      steps {
        sh "docker build -t ${dockerImageTag} ."
      }
    }

    stage('Deploy Container To Openshift') {
      environment {
        OPENSHIFT_CREDS = credentials('openshiftCreds')
      }
      
      steps {
        sh "oc login -u ${OPENSHIFT_CREDS_USR} -p ${OPENSHIFT_CREDS_PSW}"
        sh "oc project ${projectName} || oc new-project ${projectName}"
        sh "oc delete all --selector app=${projectName} || echo 'Unable to delete all previous openshift resources'"
        sh "oc new-app -l version=${version} -e DB_HOST=${mysqlHost} -e DB_USER=conygre -e DB_PASS=C0nygre-C0nygre ${dockerImageTag}"
        sh "oc expose svc/${projectName}"
      }
    }
  }

  post {
    always {
      archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
      archiveArtifacts artifacts: 'target/site/jacoco/**/*'
      archiveArtifacts 'target/surefire-reports/**/*'
    }
  }
}
