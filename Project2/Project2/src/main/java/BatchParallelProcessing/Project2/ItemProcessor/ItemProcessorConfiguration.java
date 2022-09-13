package BatchParallelProcessing.Project2.ItemProcessor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import BatchParallelProcessing.Project2.Entity.DataOfUsers;

@Configuration
@ComponentScan
public class ItemProcessorConfiguration {
    
    @Bean
	public ItemProcessor<DataOfUsers, DataOfUsers> itemProcessor() {
		return new UserItemProcessor();
	}
}
