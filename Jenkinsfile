pipeline {
    agent any

    environment {
        REMOTE_HOST = "ec2-user@13.218.122.91"
        PEM_PATH = "/.ssh-keys/docker-vm1-key.pem"
        REPO_URL = "https://github.com/jcarocota/multijuegos"
        PROJECT_DIR = "/home/ec2-user/github-repos/multijuegos"
        REPOS_DIR = "/home/ec2-user/github-repos"
    }

    stages {
        stage('limpieza') {
            steps {
                sh """
                    sudo ssh -i $PEM_PATH $REMOTE_HOST << EOF
                        echo "Inicio limpieza"
                        echo "Paso 1: Vamos a borrar la capeta existente relacionada a multijuegos"

                        sudo rm -rf $PROJECT_DIR

                        echo "Paso 2: Aseguramos que la carpeta de los repos de git exista"
                        sudo mkdir -p $REPOS_DIR

                        exit
                    EOF
                """
            }
        }
        stage('clonado') {
            steps {
                sh """
                    sudo ssh -i $PEM_PATH $REMOTE_HOST << EOF
                        echo "Inicio clonado de proyecto"
                        echo "Paso 3: Clonar repo"

                        cd  $REPOS_DIR
                        sudo git clone $REPO_URL

                        exit
                    EOF
                """
            }
        }
        stage('contruir rest-api') {
            steps {
                sh """
                    sudo ssh -i $PEM_PATH $REMOTE_HOST << EOF
                        echo "Inicio buid"
                        echo "Paso 4: Contruir el docker build/imagen"

                        cd $PROJECT_DIR
                        sudo docker build -t multi-image .

                        exit
                    EOF
                """
            }
        }
        stage('correr contenedor') {
            steps {
                sh """
                    sudo ssh -i $PEM_PATH $REMOTE_HOST << EOF
                        echo "Inicio correr contenedor"

                        echo "Paso 5: Crear y correr contenedor"
                        sudo docker rm -f multi-1 || true

                        sudo docker run --name multi-1 -d -p 8090:8080 multi-image

                        exit
                    EOF
                """
            }
        }
        stage('borrar temporales') {
            steps {
                sh """
                    sudo ssh -i $PEM_PATH $REMOTE_HOST << EOF
                        echo "Inicio borrar temporales"

                        Paso 6: Borrar fuentes del proyecto de Java
                        sudo rm -rf $PROJECT_DIR

                        exit
                    EOF
                """
            }
        }
    }

}