# FinLeap Service  :
    The objective is to build a set of different standalone services which are able to create a notification by a template
        and sent the generated notification to an imaginary mail-service. We assume that the usage of that mail service is paid by request.
        Therefore, in case of sending newsletters, the service should only be called with a batch of at least 10 messages.
        There are 3 services that has to be implemented:

        * User-Service is able to return [user-data](users.json) by:
            * user-id
            * all users

        * Template-Service is able to:
            * return a specifc template by id
            * return a specifc template by key

        * Notification-Service can be triggered in two use-cases:

            * use-case one: assume a new user is created
                * user data is loaded by user-service
                * [welcome template](templateWelcome.txt) is loaded by template service and enriched with user-data
                * once template is enriched with data it is sent directly to imaginary mail-service

            * use-case two: asume newsletter hast to be sent:
                * [newsletter](templateNewsletter.txt) is loaded by template service and enriched with user-data of all subscribed users
                * enriched newsletter messages are sent as a batch of at least 10 to imaginary mail service


----------------------------------------------------------------------------------------
# How to run the FinLeap services

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

  Step 4: Start the Scheduler
      Triggered URL  : http://localhost:9004/startScheduler
      Job Queue - It will start automatically while application startup

-----------------------------------------------------------------------------------------

### Build all modules:


`fineleap-service> mvnw clean package -DskipTests=true`

### Start each microservice either in local or in docker:**

### user-service

    * Go to  `D:\finleap-service>cd user-service`
    * Maven Quick start Local: `finleap-service/user-service>mvnw spring-boot:run`

    * Build Docker image: `user-service> docker build -t user-service .`
    * Start the service in docker :   `user-service> docker run -p 9002:9002 user-service`


### template-service
    * Go to  `D:\finleap-service> cd template-service`
    * Maven Quick start Local :  `template-service> mvnw spring-boot:run`

    * Build Docker image : `template-service> docker build -t template-service .`
    * Start the service in docker : `template-service> docker run -p 9003:9003 template-service`


### notification-service
    * Go to  `D:\finleap-service>cd notification-service`
    * Maven Quick start Local : `notification-service>mvnw spring-boot:run`

    * Build Docker image : `notification-service> docker build -t notification-service .`
    * Start the service in docker :  `notification-service> docker run -p 9004:9004 notification-service`


### Service Configuration details

* user-service:
    * hostname: user-service
    * Ports: 9002:9002
    * URL: http://localhost:9002

* template-service
    * hostname: template-service
    * Ports: 9003:9003
    * URL: http://localhost:9003

* notification-service
    * hostname: notification-service
    * Ports: 9004:9004
    * URL: http://localhost:9004

--------------------------------------------------------------------------------

### user-service DataBase Configuration details

* H2 user-service DB:
     * dbName: userdb
     * Ports: 9002
     * Admin UI: http://localhost:9002/userConsole
     * Username/password: sa/

* Browser console
------------------------------------------------------

![user-db](https://github.com/kalidassmk/finleap-service/blob/master/setup/user/user-service-local-db-connection-1.PNG)
![user-db](https://github.com/kalidassmk/finleap-service/blob/master/setup/user/user-service-local-db-connection-2.PNG)

### template-service DataBase Configuration details


* H2 template-service DB:
     * dbName: templatedb
     * Ports: 9003
     * Admin UI: http://localhost:9003/templateConsole
     * Username/password: sa/

* Browser console
------------------------------------------------------


![template-db](https://github.com/kalidassmk/finleap-service/blob/master/setup/template/template-service-local-db-connection-1.PNG)
![template-db](https://github.com/kalidassmk/finleap-service/blob/master/setup/template/template-service-local-db-connection-2)


### notification-service DataBase Configuration details


* H2 notification-service DB:
     * dbName: notificationdb
     * Ports: 9004
     * Admin UI: http://localhost:9004/console
     * Username/password: sa/

* Browser console
------------------------------------------------------

![notification-db](https://github.com/kalidassmk/finleap-service/blob/master/setup/notification/notification-local-db-connection.PNG)
![notification-db](https://github.com/kalidassmk/finleap-service/blob/master/setup/notification/notification-local-db-connection-2.PNG)

