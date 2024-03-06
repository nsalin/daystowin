pipeline {
    agent any
    tools {
        // Make sure Maven and JDK tool names match those configured in Jenkins
        maven 'Maven' // Name of the Maven tool as configured in Jenkins
    }
    environment {
            SELENIUM_RUN_ENVIRONMENT = 'grid'
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
            publishTestResults autoCreateTestCases: true, customTestCycle: [customFields: '{"checkbox": true}', description: 'BuildResults', folderId: '13089886', jiraProjectVersion: '10000', name: 'Build'], filePath: 'target/surefire-reports/junitreports/*.xml', format: 'JUnit XML Result File', projectKey: 'IND', serverAddress: 'https://innovationdays.atlassian.net'    }
}
