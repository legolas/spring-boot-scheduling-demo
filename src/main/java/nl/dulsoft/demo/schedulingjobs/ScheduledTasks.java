package nl.dulsoft.demo.schedulingjobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
public class ScheduledTasks {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private JobLauncher jobLauncher;
    private Job job;

    @Autowired
    public ScheduledTasks(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    // Schedule with a cron expression configured using parameters
    @Scheduled(cron = "${scheduled.cron}")
    public void scheduledTask() {
        LOGGER.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        runJob();
    }

    private void runJob() {
        try {
            jobLauncher.run(job, newExecution());
        } catch (JobExecutionAlreadyRunningException
                | JobRestartException
                | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException jex) {
            jex.printStackTrace();
        }
    }

    private JobParameters newExecution() {
        Map<String, JobParameter> parameters = new HashMap<>();

        LocalDateTime now = LocalDateTime.now();

        JobParameter parameter = new JobParameter(now.toString());
        parameters.put("currentTime", parameter);

        return new JobParameters(parameters);
    }

}
