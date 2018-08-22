package com.example.spring.batch.sqlserver;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.Marshaller;

@SpringBootApplication
@Import({SpringBatchConfig.class})
public class SpringBatchSqlserverApplication {

	@Bean
	org.springframework.batch.item.xml.StaxEventItemWriter  itemWriter(Marshaller marshaller){
		org.springframework.core.io.Resource  resource = new FileSystemResource("xml/output.xml");
		StaxEventItemWriter staxEventItemWriter = new StaxEventItemWriter();
		staxEventItemWriter.setResource(resource);
		staxEventItemWriter.setMarshaller(marshaller);
		staxEventItemWriter.setRootTagName("transactionRecord");
		return staxEventItemWriter;

	}
	public static void main(String[] args) {
		final ConfigurableApplicationContext context = SpringApplication.run(SpringBatchSqlserverApplication.class
				, args);
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("firstBatchJob");
		System.out.println("Starting the batch job");
		try {
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("Job Status : " + execution.getStatus());
			System.out.println("Job completed");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Job failed");
		}

	}
}
