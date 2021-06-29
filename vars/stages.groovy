def call(string stageName){
	if ("${stageName}" == "build"){
		sh "mvn clean build"
		}
	else if("${stageName}" == "SonarQube report")
	{
		sh "mvn clean sonar:sonar"
	}
	else if("${stageName}" == "UploadArtifactIntoNexus")
	{
		sh "mvn clean deploy"
	}
	else if("${stageName}" == "DeployIntoTomcat")
	{
		sshagent(['45932c08-4117-438a-bfe7-4700629c2b50']) {
		sh "scp -o StrictHostKeyChecking=no /target/maven-web-application.war ec2-user@65.0.91.17:/opt/apache-tomcat-9.0.46/webapps"
		}
	}
	else if("${stageName}" == "success")
	{
		emailext body: '''Dear Team,

		Build is completed using shared library concept using Pipeline Declarative Method is successfully completed.


		Best Regatds
		Purushotham A
		9494412397
		''', subject: 'Build is over using Pipeline Declarative Script using shared Libraries', to: 'apurushotham19@gmail.com'
	}
	else if("${stageName}" == "failure")
	{
	emailext body: '''Dear Team,

		Build is completed using shared library concept using Pipeline Declarative Method is failure and completed.


		Best Regatds
		Purushotham A
		9494412397
		''', subject: 'Build is over using Pipeline Declarative Script using shared Libraries', to: 'apurushotham19@gmail.com'	
	}
}
