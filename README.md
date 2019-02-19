# fineleap-service
Code for SpringBoot Micro Services with akka, java 8, H2 db and Docker container

## How to run?

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

* H2 user-service DB:
     * dbName: userdb
     * Ports: 9002
     * Admin UI: http://localhost:9002/userConsole
     * Username/password: sa/

* Browser console
![user-db](https://github.com/kalidassmk/finleap-service/blob/master/setup/user/user-service-local-db-connection-1.PNG)
![user-db](https://github.com/kalidassmk/finleap-service/blob/master/setup/user/user-service-local-db-connection-2.PNG)


* H2 template-service DB:
     * dbName: templatedb
     * Ports: 9003
     * Admin UI: http://localhost:9003/templateConsole
     * Username/password: sa/

* Browser console
![template-db](https://github.com/kalidassmk/finleap-service/blob/master/setup/template/template-service-local-db-connection-1.PNG)
![template-db](https://github.com/kalidassmk/finleap-service/blob/master/setup/template/template-service-local-db-connection-2)


* H2 notification-service DB:
     * dbName: notificationdb
     * Ports: 9004
     * Admin UI: http://localhost:9004/console
     * Username/password: sa/
* Browser console
![notification-db](https://github.com/kalidassmk/finleap-service/blob/master/setup/notification/notification-local-db-connection.PNG)
![notification-db](https://github.com/kalidassmk/finleap-service/blob/master/setup/notification/notification-local-db-connection-2.PNG)

