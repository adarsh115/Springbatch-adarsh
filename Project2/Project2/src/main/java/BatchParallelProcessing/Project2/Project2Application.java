package BatchParallelProcessing.Project2;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.core.task.TaskExecutor;

import BatchParallelProcessing.Project2.Entity.DataOfUsers;
import BatchParallelProcessing.Project2.Util.Util;
import BatchParallelProcessing.Project2.service.UserRowMapper;

import org.springframework.batch.item.ItemProcessor;
import javax.sql.DataSource;




@SpringBootApplication
@EnableBatchProcessing
public class Project2Application {
	//public static String[] tokens = new String[] { "id", "Firstname", "Lastname", "Email" };
	
	@Autowired
	public JobBuilderFactory jobBuilder;
	@Autowired
	public StepBuilderFactory stepBuilder;
	
	@Autowired
	public ItemReader<DataOfUsers> itemReadercsv;

	@Autowired
	public ItemReader<DataOfUsers> itemReaderdb;

	@Autowired
	public ItemWriter<DataOfUsers> itemWriter;

	@Autowired
	public ItemProcessor<DataOfUsers, DataOfUsers> itemProcessor;

	@Autowired
	public TaskExecutor taskExecutor;


	
	@Bean
	public Job ParallelExectionJob() {
		return this.jobBuilder
				.get("parallelExectutionJob")
				.start(step1())
				.build();
	}
	
	
	/*
	This is first and the only step we need to take
	to perform the job.
	*/
	@Bean
	public Step step1() {
		return this.stepBuilder
				.get("chunkStep1").<DataOfUsers, DataOfUsers>chunk(100)
				.reader(itemReaderdb)
				.processor(itemProcessor)
				.writer(	 
					new ItemWriter<DataOfUsers>() {
						public void write(java.util.List<? extends DataOfUsers> items) throws Exception {
							System.out.println(String.format("Received list of size: %s", items.size()));
						
							for(DataOfUsers item : items) {
								System.out.println(item.getId() + " - " + item.getFirstName());
							}
							// System.out.println();
						};
					}
				)
				.taskExecutor(taskExecutor)
				.build();
	}
	

	
	/*
	Reading csv file using Item Reader
	*/
	/*
	@Bean
	public ItemReader<DataOfUsers> itemReader() {
		FlatFileItemReader<DataOfUsers> itemReader = new FlatFileItemReader<>();
		itemReader.setLinesToSkip(1);
		itemReader.setResource(new FileSystemResource("Files/million user Records - 100000 Records.csv"));

		DefaultLineMapper<DataOfUsers> lineMapper = new DefaultLineMapper<DataOfUsers>();
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();

		tokenizer.setNames(tokens);
		lineMapper.setLineTokenizer(tokenizer);
		lineMapper.setFieldSetMapper(new DataOfUserFieldSetMapper());

		itemReader.setLineMapper(lineMapper);

		return itemReader;
	}
	*/
	

	/*
	Writing a csv file using Item Writer
	*/

	/* 
	@Bean
	public ItemWriter<DataOfUsers> itemWriter() {
		FlatFileItemWriter<DataOfUsers> itemWriter = new FlatFileItemWriter<>();
		itemWriter.setResource(new FileSystemResource("Files/userInMeeting"));

		DelimitedLineAggregator<DataOfUsers> aggregator = new DelimitedLineAggregator<>();
		aggregator.setDelimiter(",");

		BeanWrapperFieldExtractor<DataOfUsers> fieldExtractor = new BeanWrapperFieldExtractor<>();
		fieldExtractor.setNames(tokens);
		aggregator.setFieldExtractor(fieldExtractor);

		itemWriter.setLineAggregator(aggregator);

		return itemWriter;
	}
	*/
	
	/*
	 Task Executor is used for implementing multi-processing step
	*/
	
	
	// @Bean
	// public TaskExecutor taskExecutor() {
	// 	var executor = new ThreadPoolTaskExecutor();

	// 	executor.setCorePoolSize(5);
	// 	executor.setMaxPoolSize(5);
	// 	executor.setQueueCapacity(10);
	// 	executor.setThreadNamePrefix("Thread No -> :");

	// 	return executor;

	// 	/*
	//     SimpleAsyncTaskExecutor asyncTaskExecutor=new SimpleAsyncTaskExecutor("spring_batch");
	//     asyncTaskExecutor.setConcurrencyLimit(5);
	    
    //     long threadId = Thread.currentThread().getId();
    //     System.out.println("I am thread " + threadId + " of " + 5);
	    
    //     return asyncTaskExecutor;
    //     */
	// }

	
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(Project2Application.class, args);
	}

}
