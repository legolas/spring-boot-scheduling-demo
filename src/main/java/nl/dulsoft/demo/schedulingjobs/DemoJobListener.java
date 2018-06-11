package nl.dulsoft.demo.schedulingjobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;

public class DemoJobListener implements StepExecutionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoJobListener.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {
        LOGGER.info("Starting job {}", stepExecution.getJobExecutionId());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        Long jobId = stepExecution.getJobExecutionId();
        BatchStatus status = stepExecution.getStatus();
        LOGGER.info("Starting job {} resulted in {}", jobId, status.toString());
        return null;
    }
}
