# Template Service  :
    This the user services which are able to return the following template data

         * Template-Service is able to:
                   * return a specifc template by id
                   * return a specifc template by key

 ----------------------------------------------------------
## Resources
  base path http://localhost:9003

  Method  | Path                                                |reqest Header                                      |     request payload
|-------- |-----------------------------------------------------|---------------------------------------------------|-------------------------  |
| GET     | /getTemplateById?id=1001                            | Content-Type:application/json                     |                           |
| GET     | /getTemplateByKey?templateKey=WelcomeUserTemplate   | Content-Type:application/json                     |                           |
| GET     | /getAllTemplate                                     | Content-Type:application/json                     |                           |



## Configuration

### Configuration Files

Folder **src/resources/** contains config files for **template-service** Spring Boot application.

* **src/resources/application.properties** - main configuration file. Here it is possible to change the port number.

## How to run

There are several ways to run the application. You can run it from the command line with included Maven Wrapper, Maven or Docker.

Once the app starts, go to the web browser and visit `http://localhost:9003/`

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

It is possible to run **template-service** using Docker:

1) Build Docker image:  `template-service> docker build -t template-service .`

2) Run Docker container: `template-service> docker run -p 9003:9003 template-service`

### Service Configuration details

* template-service
    * hostname: template-service
    * Ports: 9003:9003
    * URL: http://localhost:9003

--------------------------------------------------

### template-service DataBase Configuration details


* H2 template-service DB:
     * dbName: templatedb
     * Ports: 9003
     * Admin UI: http://localhost:9003/templateConsole
     * Username/password: sa/

* Browser console

------------------------

![template-db-1](https://github.com/kalidassmk/finleap-service/blob/master/setup/template/template-service-local-db-connection-1.PNG)
![template-db-2](https://github.com/kalidassmk/finleap-service/blob/master/setup/template/template-service-local-db-connection-2.PNG)

## Tests
Tests can be run by executing following command from the root of the project:

$ mvn test


Maven Quick start
------------------
**Local:** `template-service>mvnw spring-boot:run`

----------------------------------
