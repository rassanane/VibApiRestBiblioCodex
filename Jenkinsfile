pipeline {
  agent any
  tools { jdk 'jdk17'; maven 'maven-3.9' } // adapte aux noms configurés dans Manage Jenkins → Tools

  triggers { githubPush() } // déclenché par le webhook

  stages {
    stage('Checkout') {
      steps { checkout scm }
    }
    stage('Build & Test') {
      steps { sh 'mvn -B -U -DskipTests=false clean test' }
    }
  }
  post {
    always {
      junit '**/target/surefire-reports/*.xml'
    }
  }
}
