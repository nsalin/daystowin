pipeline {
    agent any
    tools {
        // Make sure Maven and JDK tool names match those configured in Jenkins
        maven 'Maven' // Name of the Maven tool as configured in Jenkins
    }
    parameters {
      choice choices: ['suite', 'testng'], name: 'SUITE_NAME'
    }
    stages {
        stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }
        stage('Clone Repository') {
            steps {
                git credentialsId: '7ea5dd73-51e2-4c25-b5c6-5c24caea6358', url: 'https://github.com/nsalin/daystowin.git'
            }
        }
        stage('Run TestNG Tests') {
            steps {
                sh 'mvn clean test -Dsurefire.suiteXmlFiles=${SUITE_NAME}'
            }
        }
    }
    post {
        always {
            // Send email notification
            emailext (
                subject: "Build ${currentBuild.fullDisplayName}",
                body: """<p>Build status: ${currentBuild.currentResult}</p>
                         <p>Check console output at <a href='${buildUrl}console'>${buildUrl}console</a></p>""",
                to: 'alin.nitu@3pillarglobal.com'
            )
        }
    }
}
