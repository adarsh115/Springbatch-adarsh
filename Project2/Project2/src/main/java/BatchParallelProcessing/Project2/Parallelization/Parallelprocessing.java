package BatchParallelProcessing.Project2.Parallelization;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
// import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@ComponentScan
public class Parallelprocessing {
    
    @Bean
	public TaskExecutor taskExecutor() {
		// var executor = new ThreadPoolTaskExecutor();

		// executor.setCorePoolSize(5);
		// executor.setMaxPoolSize(5);
		// executor.setQueueCapacity(10);
		// executor.setThreadNamePrefix("Thread No -> :");
        

		// return executor;

		
	    SimpleAsyncTaskExecutor asyncTaskExecutor=new SimpleAsyncTaskExecutor("spring_batch");
	    asyncTaskExecutor.setConcurrencyLimit(5);
	    
        // long threadId = Thread.currentThread().getId();
        // System.out.println("I am thread " + threadId +"  ");
	    
        return asyncTaskExecutor;
        
	}
}
