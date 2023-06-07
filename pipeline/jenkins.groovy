pipeline {
    agent any
    parameters {

        choice(name: 'OS', choices: ['linux', 'darwin', 'windows', 'all'], description: 'Pick OS')

    }
    environment {
        REPO = 'https://github.com/Sergo03/kbot'
        BRANCH = 'main'
    }

    stages {
        stage('clone') {
            steps {
                echo "CLONE REPOSITORY"

                git branch: "${BRANCH}", url: "${REPO}"

            }
        }
    

    
        stage('test') {
            steps {
                echo "TEST EXEC"

                sh 'go test -v'

            }
        }
    

    
        stage('build') {
            steps {
                echo "BUILD EXEC"

                sh 'make build'

            }
        }
    

   
        stage('image') {
            steps {
                echo "TEST EXEC"

                sh 'make image'

            }
        }
    

    
        stage('push') {
            steps {
                script {
                    docker.withRegistry('', 'dockerhub'){
                    sh 'make push'
                    }
                }

            }
        }
    

    
        stage('Example') {
            steps {
                echo "Build for platform ${params.OS}"

                echo "Build for arch: ${params.ARCH}"

            }
        }
    }
}