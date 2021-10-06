package com.docom.pingenerator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

//@Configuration
//public class MyJob implements SchedulingConfigurer {
//
//@Override
//public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//taskRegistrar.addTriggerTask(new Runnable() {
//@Override
//public void run() {
//     // Do not put @Scheduled annotation above this method, we don't need it anymore.
//     System.out.println("Running Schedular..."+ Calendar.getInstance().getTime());
//    }
//   }, new Trigger() {
//@Override
//public Date nextExecutionTime(TriggerContext triggerContext) {
//      Calendar nextExecutionTime = new GregorianCalendar();
//      Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
//      nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
//      nextExecutionTime.add(Calendar.MILLISECOND, getNewExecutionTime());
//      return nextExecutionTime.getTime();
//     }
//   });
//}
//
//   private int getNewExecutionTime() {
//     //Load Your execution time from database or property file
//     return 1000;
//   }
//}
@Component
@Scope(value = "prototype")
public class MyJob {
    @Scheduled(fixedRate=1000)
    public void work() {
        System.out.println("Executed");
    }
}