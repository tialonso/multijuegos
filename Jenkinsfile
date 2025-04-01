pipeline {
    agent any

    triggers {
        githubPush()
    }

    environment {
        REMOTE_HOST = "ec2-user@3.92.203.154"
        PEM_PATH = "~/.ssh-keys/docker-vm1-key.pem"
        REPO_URL = "https://github.com/jcarocota/multijuegos.git"
        PROJECT_DIR = "~/github-repos/multijuegos"
    }

    stages {
        stage('Inicio') {
            steps {
                echo 'Conexión a docker-vm1'
            }
        }
        stage('Deploy en Docker VM') {
            steps {
                sshagent(['docker-vm1-key']) {
                    sh """
                    sudo ssh -o StrictHostKeyChecking=no \$REMOTE_HOST << 'EOF'
                        echo "Paso 1: Borrando carpeta previa (si existe)..."
                        sudo rm -rf \$PROJECT_DIR

                        echo "Paso 2: Asegurando carpeta github-repos..."
                        sudo mkdir -p ~/github-repos

                        echo "Paso 3: Clonando repositorio..."
                        cd ~/github-repos
                        sudo git clone \$REPO_URL

                        echo "Paso 4: Entrando al proyecto..."
                        cd multijuegos

                        echo "Paso 5: Construyendo imagen Docker..."
                        sudo docker build -t multi-image .

                        echo "Paso 6: Eliminando contenedor multi-1 (si existe)..."
                        sudo docker rm -f multi-1 || true

                        echo "Paso 7: Creando contenedor multi-1..."
                        sudo docker run --name multi-1 -d -p 8090:8080 multi-image

                        echo "Paso 8: Eliminando el código fuente del servidor..."
                        sudo rm -rf \$PROJECT_DIR

                        echo "Todo listo en Docker VM."
                    EOF
                    """
                }
            }
        }
        stage('Gatito') {
            steps {
                echo 'Fin!'
            }
        }
    }
}
