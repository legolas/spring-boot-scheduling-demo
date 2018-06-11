package nl.dulsoft.demo.schedulingjobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

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
