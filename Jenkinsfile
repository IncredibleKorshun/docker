pipeline{

    agent any

    stages{

        stage('Build Jar'){
            steps{
                sh "mvn clean package -DskipTests"
              }
        }

         stage('Build Image'){
            steps{
                sh "docker build -t nikitosnegritos/selenium ."
            }
        }

          stage('Push Image'){
            steps{
                sh "docker push nikitosnegritos/selenium"
            }
        }
    }

    post{
        always{
           echo "doing clean up"
        }
    }

}