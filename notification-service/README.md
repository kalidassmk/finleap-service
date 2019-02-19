# Multi API notification service challenge:
   ### Notification-Service can be triggered in two use-cases:
      # use-case one: assume a new user is created
        * user data is loaded by user-service
        * welcome template is loaded by template service and enriched with user-data
        * once template is enriched with data it is sent directly to imaginary mail-service

      # use-case two: asume newsletter hast to be sent:
        * newsletter is loaded by template service and enriched with user-data of all subscribed users
        * enriched newsletter messages are sent as a batch of at least 10 to imaginary mail service

----------------------------------------------------------------------------------------------------
# How to run the Notification service

  Step 1: start the user service and template service.

  Step 2: Configure the schedulers in the src/resource/data.sql file

    -- dailyScheduler for Subscribed News Letter notification, it will run based on the frequency=daily  and time= 23:05 GMT time stamp
       it will run on daily 11.05 pm in GMT, we can adjust scheduler based on time column in scheduler table.
       Note : For local test you can adjust the time into current time, example current time is 12:00 pm GMT,
               you add additional 2 minutes for application and scheduler start up. so finally you can set the time 12:02.
               it will run on 12.02 pm GMT
    insert into scheduler (id,name,description,frequency,time,status,frequency_value,delay,next_run)
      values('1','dailyScheduler','Subscribed News Letter','daily','23:00','NEW',1,0,0);

    --minuteScheduler for New User Welcome Notification, it will run on every 5 minutes ,frequency=minute, frequency_value=5
    insert into scheduler (id,name,description,frequency,time,status,frequency_value,delay,next_run)
      values('2','minuteScheduler','New User Welcome Notification','minute','23:00','NEW',5,0,0)

  Step 3: Start the notification service

  Step 4: Start the Scheduler, through this URL : http://localhost:9004/startScheduler

----------------------------------------------------------------------------------------------
# Hibernate will create the table while application start up besed the below configuration
    Folder ***src/resources/application.properties contains the configuration.
      # spring.jpa.hibernate.ddl-auto = create-drop

      create table job (
           id varchar(255) not null,
            job_name varchar(255),
            job_type varchar(255),
            last_run_on varchar(255),
            schedule_id varchar(255),
            status varchar(255),
            triggered_by varchar(255),
            primary key (id)
        )

        create table job_queue (
           id varchar(255) not null,
            completed_on date,
            created_on date,
            instance_name varchar(255),
            job_id varchar(255),
            job_name varchar(255),
            job_type varchar(255),
            status varchar(255),
            primary key (id)
        )

        create table scheduler (
           id varchar(255) not null,
            delay varchar(255),
            description varchar(255),
            frequency varchar(255),
            frequency_value integer,
            last_modified_on timestamp,
            last_triggered_on timestamp,
            name varchar(255),
            next_run varchar(255),
            start_date date,
            start_day varchar(255),
            status varchar(255),
            time varchar(255),
            primary key (id)
        )


---------------------------------------------------------------------------------------------

# Hibernate will insert data into created table while application start up based the below configuration
    Folder ***src/resources/application.properties contains the configuration.
      # spring.jpa.hibernate.ddl-auto = create-drop
  Database data File :
    Folder **src/resources/data.sql contains table data.

-------------------------------------------------------------------------------------------------
  # Database Tables :
    1) scheduler
       # insert into scheduler (id,name,description,frequency,time,status,frequency_value,delay,next_run)
              values('2','minuteScheduler','New User Welcome Notification','minute','23:00','NEW',5,0,0);
                 # Scheduler name : minuteScheduler
                    * It will run every five minutes and get the Job data by mapped with schedule id in the 'JOB' table
                      then  will create the Job Queue to trigger the WelcomeNotificationJob
                      (based on the frequency_value= "5",  we can adjust the frequency 5 minutes to any value )

             # insert into scheduler (id,name,description,frequency,time,status,frequency_value,delay,next_run)
               values('1','dailyScheduler','Subscribed News Letter','daily','23:03','NEW',1,0,0);
                 # Scheduler name : dailyScheduler
                     * It will run daily and get the Job data by mapped with schedule id in the 'JOB' table
                       then  will create the Job Queue to trigger the SubscribedNotificationJob
                      (based on the schedule "time" = 23:03 (hour and minutes) we can schedule the time in the day it will be 24 hours format)

     2) Job
           # insert into job (ID,JOB_NAME,TRIGGERED_BY,JOB_TYPE,STATUS,LAST_RUN_ON,SCHEDULE_ID)
               values('1','SubscribedNotificationJob','System','SubscribedNotificationJob','Active','1549992883352','1');
                     # Job name : SubscribedNotificationJob , mapped with dailyScheduler (SCHEDULE_ID=1)
                        * It will get the Subscribed user data from user service and get Subscribed User Template(newsletter template)
                            from template service then  enrich the both data and send to mail service

                 # insert into job (ID,JOB_NAME,TRIGGERED_BY,JOB_TYPE,STATUS,LAST_RUN_ON,SCHEDULE_ID)
                     values('2','WelcomeNotificationJob','System','WelcomeNotificationJob','Active','1549992883352','2');
                     # Job name : WelcomeNotificationJob, mapped with minuteScheduler(SCHEDULE_ID=2)
                        * It will get the new user data from user service and get Welcome User Template(welcome template)
                             from template service then  enrich the both data and send to mail service

     3) Job_Queue - SchedulerActor will create the job queue based on the job which has mapped
             # JobQueue Actor will lookup every 10 seconds in the "JOB_QUEUE" TABLE
                if any NEW job queue has created, it will trigger the job actor to execute
                the appropriate job ex.. SubscribedNotificationJob , WelcomeNotificationJob.
             # Job Actor will assign the job worker to execute the task.

### Technical Implementation :
      ## com.finleap.notification.worker.WelcomeNotificationJob .java




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

--------------------------------------------------

#Technical Implementation
------------------------

# Actor
---------
### com.finleap.notification.actor.SchedulerActor
 --- Get the Job details based on the Schedule id and create the job queue

### com.finleap.notification.actor.JobQueueActor
--- Get the job queue and trigger the Job Actor.

### com.finleap.notification.actor.JobActor
--- Get the Job Queue details and assign the job to job worker


-----------------------------------------------

# Job Worker
-------------

### com.finleap.notification.worker.SubscribedNotificationJob
--- It will get the Subscribed user data from user service and get Subscribed User Template(newsletter template)
     from template service then  enrich the both data and send to mail service

### com.finleap.notification.worker.WelcomeNotificationJob
--- It will get the new user data from user service and get Welcome User Template(welcome template)
     from template service then  enrich the both data and send to mail service

---------------------------------------------------------