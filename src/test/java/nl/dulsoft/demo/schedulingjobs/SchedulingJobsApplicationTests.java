package nl.dulsoft.demo.schedulingjobs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchedulingJobsApplicationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulingJobsApplicationTests.class);

    @Test
    public void contextLoads() throws InterruptedException {
        // Sleep for 2 minutes allowing the job to be executed twice
//        Thread.sleep(60 * 1000 * 2);
    }
}
