def appName = 'api-example'
def appNameHml = 'api-example-hml'
def imageVersion = "$BUILD_NUMBER"
def namespace = 'api-example'
def namespaceHml = 'api-example-hml'
def deploymentpath = '~/deployments/api-example'
def imageTag = "srv-docker.sfa.local:5000/${appName}:${imageVersion}"
def imageTagHml = "srv-docker.sfa.local:5000/${appNameHml}:${imageVersion}"

pipeline {
	agent any
	//Checkout Code from Git
    //checkout scm
	
	stages {
	  stage('Build/Deploy Homologacao') {
	    when {
                branch "hml"
      }	  
	    steps {
			echo 'Build HML'
			echo 'Preparando modulos...'
			sh ("mvn -B -DskipTests clean package")
			echo 'Deploy HML'
		        sh ("cp -f Dockerfile-hml Dockerfile")
			sh ("docker build -t ${appNameHml}:${imageVersion} .")
			sh ("docker tag ${appNameHml}:${imageVersion} ${imageTagHml}")
			sh ("docker push ${imageTagHml}")
			sh ("sed -i 's/development/${imageVersion}/' ./deployments/deployment-hml.yaml")
			sh ("scp ./deployments/*.yaml rancher@172.29.10.161:${deploymentpath}")
			sh ("scp ./deployments/service-hml.yaml rancher@172.29.10.161:${deploymentpath}")
			sh ("scp ./deployments/ingress-hml.yaml rancher@172.29.10.161:${deploymentpath}")
			sh ("ssh rancher@172.29.10.161 \"kubectl  --namespace=${namespaceHml} apply -f ${deploymentpath}/deployment-hml.yaml\"")
			sh ("ssh rancher@172.29.10.161 \"kubectl  --namespace=${namespaceHml} apply -f ${deploymentpath}/service-hml.yaml\"")
			sh ("ssh rancher@172.29.10.161 \"kubectl  --namespace=${namespaceHml} apply -f ${deploymentpath}/ingress-hml.yaml\"")
      }
    }
	stage('Build/Deploy Producao') {
	  when {
                branch "master"
      }
	  options {
        timeout(time: 4, unit: 'HOURS') 
      }
	  steps {
		input(message: 'Efetuar build da branch master e deploy em Produção?', submitter: 'DevOps')
		sh ("echo Build Prod")
		sh ("mvn -B -DskipTests clean package")
		sh ("echo Deploy Prod")
		sh ("docker build -t ${appName}:${imageVersion} .")
		sh ("docker tag ${appName}:${imageVersion} ${imageTag}")
		sh ("docker push ${imageTag}")
		sh ("sed -i 's/development/${imageVersion}/' ./deployments/deployment.yaml")
		sh ("scp ./deployments/deployment.yaml rancher@172.29.10.161:${deploymentpath}")
		sh ("scp ./deployments/service.yaml rancher@172.29.10.161:${deploymentpath}")
		sh ("scp ./deployments/ingress.yaml rancher@172.29.10.161:${deploymentpath}")
		sh ("ssh rancher@172.29.10.161 \"kubectl  --namespace=${namespace} apply -f ${deploymentpath}/deployment.yaml\"")
		sh ("ssh rancher@172.29.10.161 \"kubectl  --namespace=${namespace} apply -f ${deploymentpath}/service.yaml\"")
		sh ("ssh rancher@172.29.10.161 \"kubectl  --namespace=${namespace} apply -f ${deploymentpath}/ingress.yaml\"")
	  }
    }
  }
}
