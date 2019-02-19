## Configuration

### Configuration Files

Folder **src/resources/** contains config files for **user-service** Spring Boot application.

* **src/resources/application.properties** - main configuration file. Here it is possible to change the port number.

## How to run

There are several ways to run the application. You can run it from the command line with included Maven Wrapper, Maven or Docker.

Once the app starts, go to the web browser and visit `http://localhost:9002/`

### Maven

Open a terminal and run the following commands to ensure that you have valid versions of Java and Maven installed:

```bash
$ java -version
java version "1.8.0_102"
Java(TM) SE Runtime Environment (build 1.8.0_102-b14)
Java HotSpot(TM) 64-Bit Server VM (build 25.102-b14, mixed mode)
```

```bash
$ mvn -v
Apache Maven 3.3.9 (bb52d8502b132ec0a5a3f4c09453c07478323dc5; 2015-11-10T16:41:47+00:00)
Maven home: /usr/local/Cellar/maven/3.3.9/libexec
Java version: 1.8.0_102, vendor: Oracle Corporation
```

```bash
$ docker -v
Docker version 18.06.1-ce, build e68fc7a
```
### Docker

It is possible to run **user-service** using Docker:

1) Build Docker image:
**Docker:** `user-service> docker build -t user-service .`

```

2) Run Docker container:
**Start the service in docker : ** `user-service> docker run -p 9002:9002 user-service`

* user-service
    * hostname: user-service
    * Ports: 9002:9002
    * URL: http://localhost:9002

* H2 user-service DB:
     * dbName: userdb
     * Ports: 9002
     * Admin UI: http://localhost:9002/userConsole
     * Username/password: sa/
![createUserSession](https://github.com/kalidassmk/finleap-service/blob/master/setup/user/user-service-local-db-connection-1.PNG)
![createUserSession](https://github.com/kalidassmk/finleap-service/blob/master/setup/user/user-service-local-db-connection-2.PNG)


## Tests
Tests can be run by executing following command from the root of the project:

```bash
$ mvn test
```

In `/src/main/resources/application.properties` file it is possible to change both
web interface url path, as well as the datasource url.

Maven Quick start
------------------
**Local:** `user-service>mvnw spring-boot:run`
