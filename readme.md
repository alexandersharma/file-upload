## Spring Boot: File upload and notifications with web socket ##

This example will shows how to upload file and send notifications, via web socket.

Could be useful, for example, if you are trying to implement a file upload and real-time notification system.

### Build and run

#### Configurations

Open the `application.properties` file and set your own configurations.

#### Prerequisites

- Java 8
- Maven > 3.0

#### From terminal

Build project using maven

 Go on the project's root folder, then type:
	
		$ mvn clean
		
		$ mvn -Dwtpversion=2.0 eclipse:eclipse
		
		$ mvn -Dmaven.test.skip=true clean install

Run project using terminal

 Go on the project's root folder, then type:
	
	    $ mvn spring-boot:run

#### From Eclipse (Spring Tool Suite)

Import as *Existing Maven Project* and run it as *Spring Boot App*.


### Usage

#### From Browser

#### Notifications with web socket

- Open browser and open link:- http://localhost:8080/notifications

- Launch the application and login into it with one of the following credentials (Username / Password):
    * firstuser / firstuser
    * seconduser / seconduser
    * admin / admin
    
    
    
File Size should be less tan 10 mb

- Open browser new tab and open link:- http://localhost:8080

- Choose file and click on submit button
    
    

#### Form Terminal

   Single file upload
	 	
		$ curl -F file=@"F://temp//source_code.java.txt"  http://localhost:8080/api/upload/
 	
 	
 	Multiple file upload
	 	
	 	$ curl -F files=@"F://temp//source_code.java.txt" -F files=@"F://temp//source_code.java.txt"  http://localhost:8080/api/upload/multi/

### Web socket details

	Url:- http://localhost:8080

	End point :- ws
	
	Message queue :- /queue/notify
