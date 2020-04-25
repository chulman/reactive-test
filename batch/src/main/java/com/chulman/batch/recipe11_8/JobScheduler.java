package com.chulman.batch.recipe11_8;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JobScheduler {

    private final JobLauncher jobLauncher;
    private final Job job;

    public JobScheduler(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    public void runRegistrationsJob(Date date) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParametersBuilder builder = new JobParametersBuilder();
        builder.addDate("date", date);

        JobParameters parameters = builder.toJobParameters();
        JobExecution execution = jobLauncher.run(job, parameters);

        System.err.println(execution.getExitStatus().getExitCode());
    }

    @Scheduled(fixedDelay = 1000 * 10)
    public void runRegistrationJobOnAsSchedule() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        runRegistrationsJob(new Date());
    }
}
