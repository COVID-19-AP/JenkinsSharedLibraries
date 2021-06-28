def call(string stageName){
	if ("stringName" == "Build")
		sh "mvn clean package"
	else if("stringName" == "SonarQube Report")
		sh "mvn clean sonar:sonar"
	else if ("stringName" == "UploadArtifactIntoNexus")
		sh "mvm clean deploy"
	else if ("stringName" == "DeployIntoServer")
		sshagent(['3aeb95c5-254c-4c96-b3b6-0fffd385ece8']) {
		sh "scp -o StrictHostKeyChecking=no target/maven-web-application.war ec2-user@35.154.213.248:/opt/apache-tomcat-9.0.46/webapps"
	}
	else if ("stringName" == "post-success")
		emailext body: '''Dear Team,

	This build is over and Completed succes!!!!!
	
	Best Regards
	purushotham A
	9494412397''', subject: 'Build is over !!!', to: 'apurushotham19@gmail.com'
	else if("stringName" == "post-failure")
	This build is over and Completed failure!!!!!
	
	Best Regards
	purushotham A
	9494412397''', subject: 'Build is over !!!', to: 'apurushotham19@gmail.com'
}
