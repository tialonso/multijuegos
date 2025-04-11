pipeline {
    agent any
    
   environment {
        REMOTE_HOST = "ec2-user@54.211.139.127"
        PEM_PATH = "/.ssh-keys/docker-vm1-key.pm"
        REPO_URL = "https://github.com/tialonso/multijuegos"
        REPOS_DIR = "/home/ec2-user/github-repos"
        MULTIJUEGOS_DIR = "/home/ec2-user/github-repos/multijuegos"
    }
    
    stages{
        stage('limpieza'){
            steps{
                sh"""
                     sudo ssh -i $PEM_PATH $REMOTE_HOST << EOF
                            echo "Paso 1:Limpieza"
                            sudo rm -rf $MULTIJUEGOS_DIR
                            sudo mkdir -p $REPOS_DIR
                            exit
                     EOF
                """
            }
        }
        
        stage('clonado'){
            steps{
                sh"""
                     sudo ssh -i $PEM_PATH $REMOTE_HOST << EOF
                            echo "Paso 2: Clonado de repo"
                            cd $REPOS_DIR
                            sudo git clone $REPO_URL
                            exit
                     EOF
                """
            }
        }
        
        stage('build'){
            steps{
                sh"""
                     sudo ssh -i $PEM_PATH $REMOTE_HOST << EOF
                            echo "Paso 3: Creación de imagen"
                            cd $MULTIJUEGOS_DIR
                            sudo docker build -t multi-image .
                            exit
                     EOF
                """
            }
        }
        
        stage('ejecución') {
            steps {
                sh """
                    sudo ssh -i $PEM_PATH $REMOTE_HOST << EOF
                        echo "Paso 4: Ejecución de imagen... correr contenedor"
                        sudo docker rm -f multi-1 || true  
                        sudo docker run --name multi-1 -d -p 8090:8080 multi-image
                        exit
                    EOF
                """
            }
        }
        
    }
}
