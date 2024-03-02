pipeline{
    
    agent any
    
    tools{
        maven 'M3'
    }
    
    stages{
        stage('git clone'){
            steps{
              git 'https://github.com/ashokitschool/maven-web-app.git'  
            }
        }
        stage('mvn build'){
            steps{
               sh 'mvn clean package' 
            }
        }
        stage('Docker image'){
            steps{
                sh 'docker build -t ashokit .'
            }
        }
        stage('docker deployment'){
            steps{
                sh 'docker stop charan'
                sh 'docker rm charan'
                 sh 'docker run -d -p 9090:8080 --name charan ashokit'
            }
        }
    }
    
}
