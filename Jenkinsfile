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
      // Publie les rapports de tests (marque UNSTABLE si des tests échouent)
      junit '**/target/surefire-reports/*.xml'

      // Envoi d'un email à chaque build (SUCCESS / FAILURE / UNSTABLE / ABORTED)
      mail to: 'rassanane@gmail.com',
           subject: "[${currentBuild.currentResult}] ${env.JOB_NAME} #${env.BUILD_NUMBER}",
           body: """Résultat: ${currentBuild.currentResult}
            Job: ${env.JOB_NAME}
            Build: #${env.BUILD_NUMBER}
            URL: ${env.BUILD_URL}"""
    }
  }
}
