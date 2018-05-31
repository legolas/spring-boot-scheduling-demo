package nl.dulsoft.demo.schedulingjobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowJobBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.JobFlowBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableBatchProcessing
public class SchedulingJobsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchedulingJobsApplication.class, args);
    }

    @Bean
    ItemWriter<String> itemWriter() {
        return new DemoItemWriter();
    }

    @Bean
    ItemReader<String> itemReader() {
        return new DemoItemReader();
    }

    @Bean
    ItemProcessor<String, String> itemProcessor() {
        return new DemoItemProcessor();
    }

    @Bean
    Step demoBatchStep(ItemReader<String> itemReader,
                       ItemProcessor<String, String> itemProcessor,
                       ItemWriter<String> itemWriter,
                       StepBuilderFactory factory) {
        return factory.get("demoStep")
                .<String, String>chunk(1)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }

    @Bean
    Job demoBatchJob(JobBuilderFactory jobBuilderFactory, Step demoBatchStep) {
        JobBuilder demoBatchJob = jobBuilderFactory.get("demoBatchJob");
        JobBuilder incrementer = demoBatchJob.incrementer(new RunIdIncrementer());
        JobFlowBuilder flow = incrementer.flow(demoBatchStep);
        FlowJobBuilder end = flow.end();
        Job build = end.build();

        return build;
    }
}
