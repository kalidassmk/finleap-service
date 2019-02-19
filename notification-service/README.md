# Multi API notification service challenge:
   ### Notification-Service can be triggered in two use-cases:
      # use-case one: assume a new user is created
        * user data is loaded by user-service
        * welcome template is loaded by template service and enriched with user-data
        * once template is enriched with data it is sent directly to imaginary mail-service

      # use-case two: asume newsletter hast to be sent:
        * newsletter is loaded by template service and enriched with user-data of all subscribed users
        * enriched newsletter messages are sent as a batch of at least 10 to imaginary mail service

---------------------------------------------------------------------------------------------
  ### Database File :
    Folder **src/resources/** contains data.sql files for Database tables details and information .

----------------------------------------------------------------------------------------------------
  # Database Tables :
    ##  scheduler
       # insert into scheduler (id,name,description,frequency,time,status,frequency_value,delay,next_run)
              values('2','minuteScheduler','New User Welcome Notification','minute','23:00','NEW',5,0,0);
                 # Scheduler name : minuteScheduler
                    * It will run every five minutes and will trigger the WelcomeNotificationJob
                    (based on the frequency_value= "5",  we can adjust the frequency )

             # insert into scheduler (id,name,description,frequency,time,status,frequency_value,delay,next_run)
               values('1','dailyScheduler','Subscribed News Letter','daily','23:03','NEW',1,0,0);

                 # Scheduler name : dailyScheduler
                    * It will run every day based on the schedule "time" = 23:03 (hour and minutes) and run the SubscribedNotificationJob




### Technical Implementation :
      ## com.finleap.notification.worker.WelcomeNotificationJob .java



## How to run?




## Configuration

### Configuration Files

Folder **src/resources/** contains config files for **notification-service** Spring Boot application.

* **src/resources/application.properties** - main configuration file. Here it is possible to change the port number.

## How to run

There are several ways to run the application. You can run it from the command line with included Maven Wrapper, Maven or Docker.

Once the app starts, go to the web browser and visit `http://localhost:9004/`

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

It is possible to run **notification-service** using Docker:

1) Build Docker image: `notification-service> docker build -t notification-service .`

2) Run Docker container: `notification-service> docker run -p 9004:9004 notification-service`

-----------------------------------------------------------------

### Service Configuration details

* notification-service
    * hostname: notification-service
    * Ports: 9004:9004
    * URL: http://localhost:9004

--------------------------------------------------------------


### notification-service DataBase Configuration details


* H2 notification-service DB:
     * dbName: notificationdb
     * Ports: 9004
     * Admin UI: http://localhost:9004/console
     * Username/password: sa/

* Browser console

-----------------------------


![notification-db-setup](https://github.com/kalidassmk/finleap-service/blob/master/setup/notification/notification-local-db-connection.PNG)
![notification-db-setup-2](https://github.com/kalidassmk/finleap-service/blob/master/setup/notification/notification-local-db-connection-2.PNG)


## Tests
Tests can be run by executing following command from the root of the project:

$ mvn test

Maven Quick start
------------------
**Local:** `notification-service>mvnw spring-boot:run`


Job Scheduler
------------------


