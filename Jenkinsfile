def appName = 'api-novo'
def imageVersion = "$BUILD_NUMBER"
def namespace = 'api-novo'
def deploymentpath = '~/deployments/api-novo'
def imageTag = "srv-docker.sfa.local:5000/${appName}:${imageVersion}"

pipeline {
	agent any
	//Checkout Code from Git
    //checkout scm
	
	stages {
	  stage('Build') {
	    steps {
		   sh ("mvn install:install-file -Dfile=/libjenkins/sqljdbc4.jar -DgroupId=com.microsoft.sqlserver -DartifactId=sqljdbc4 -Dversion=4.0 -Dpackaging=jar")
		   sh ("mvn install:install-file -Dfile=/libjenkins/primefaces-6.1.3.jar -DgroupId=org.primefaces -DartifactId=primefaces -Dversion=6.1.3 -Dpackaging=jar")
		   sh ("mvn install:install-file -Dfile=/libjenkins/atlas-1.1.jar -DgroupId=org.primefaces.themes -DartifactId=atlas -Dversion=1.1 -Dpackaging=jar")
		   sh ("mvn -B -DskipTests clean package")
      }
    }
	stage('Deploy Teste') {
	  steps {
	     sh ("docker build -t ${appName}:${imageVersion} .")
		 sh ("docker run -e TZ=America/Sao_Paulo --name ${appName}-${imageVersion} -d -p 8080 ${appName}:${imageVersion}")
		 sh ("docker inspect --format=\'{{.NetworkSettings.Ports}}\' ${appName}-${imageVersion}")

		 input(message: 'Efetuar deploy no ambiente de producao?', submitter: 'DevOps')
      }
    }
    
	stage('Deploy Producao') {
      steps {
        sh ("docker tag ${appName}:${imageVersion} ${imageTag}")
		sh ("docker push ${imageTag}")
		sh ("sed -i 's/development/${imageVersion}/' ./deployments/deployment.yaml")
		sh ("scp ./deployments/deployment.yaml rancher@172.29.10.161:${deploymentpath}")
		sh ("ssh rancher@172.29.10.161 \"kubectl  --namespace=${namespace} apply -f ${deploymentpath}/deployment.yaml\"")
		sh ("ssh rancher@172.29.10.161 \"kubectl  --namespace=${namespace} apply -f ${deploymentpath}/service.yaml\"")
		sh ("ssh rancher@172.29.10.161 \"kubectl  --namespace=${namespace} apply -f ${deploymentpath}/ingress.yaml\"")
			}
    }
  }
}

	  
	
	