pipeline {
    agent any
    tools {
        // Make sure Maven and JDK tool names match those configured in Jenkins
        maven 'Maven' // Name of the Maven tool as configured in Jenkins
    }
    parameters {
      choice choices: ['fastSuite','login', 'address', 'shopping', 'all'], name: 'SUITE_NAME'
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
                sh 'mvn clean test -DsuiteName=${SUITE_NAME}.xml'
            }
        }
    }
    post {
        always {
            // Send email notification
            emailext (
                subject: "Build ${env.JOB_NAME} #${env.BUILD_NUMBER} Finished",
                            body: """<p>Build status: ${currentBuild.currentResult}</p>
                                     <p>View the build details at: <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>""",
                to: 'alin.nitu@3pillarglobal.com'
            )
        }
    }
}
