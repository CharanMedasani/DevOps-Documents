pipeline {
  
    agent any 
    
    tools{
        maven "Maven-3.9.6"
    }

    stages {
        stage('Clone') {
            steps {
               git 'https://github.com/ashokitschool/maven-web-app.git'
            }
        }
        stage('Build') {
            steps {
               sh 'mvn clean package'
            }
        }
		
        stage('Docker image'){
            steps{
                sh 'docker build -t devops .'
            }
        }
        stage('docker deployment'){
            steps{
                sh 'docker stop rani'
                sh 'docker rm rani'
                sh 'docker run -d -p 9090:8080 --name rani devops'
            }
        }
        
    }
 }