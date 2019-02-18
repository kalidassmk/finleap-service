--Scheduler data
insert into scheduler (id,name,description,frequency,time,status,frequency_value,delay,next_run)
  values('1','dailyScheduler','Subscribed News Letter','daily','23:03','NEW',1,0,0);

insert into scheduler (id,name,description,frequency,time,status,frequency_value,delay,next_run)
  values('2','minuteScheduler','New User Welcome Notification','minute','23:00','NEW',5,0,0);

-- Job
insert into job (ID,JOB_NAME,TRIGGERED_BY,JOB_TYPE,STATUS,LAST_RUN_ON,SCHEDULE_ID)
  values('1','SubscribedNotificationJob','System','SubscribedNotificationJob','Active','1549992883352','1');

insert into job (ID,JOB_NAME,TRIGGERED_BY,JOB_TYPE,STATUS,LAST_RUN_ON,SCHEDULE_ID)
  values('2','WelcomeNotificationJob','System','WelcomeNotificationJob','Active','1549992883352','2');

