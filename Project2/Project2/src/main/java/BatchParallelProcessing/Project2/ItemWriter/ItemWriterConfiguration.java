package BatchParallelProcessing.Project2.ItemWriter;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import BatchParallelProcessing.Project2.Entity.DataOfUsers;

@Configuration
@ComponentScan
public class ItemWriterConfiguration implements ItemWriter<DataOfUsers>{
    
    // @Bean
	// public ItemWriter<DataOfUsers> itemWriter() {
	// 	FlatFileItemWriter<DataOfUsers> itemWriter = new FlatFileItemWriter<>();
	// 	itemWriter.setResource(new FileSystemResource("Files/userInMeeting.csv"));

	// 	DelimitedLineAggregator<DataOfUsers> aggregator = new DelimitedLineAggregator<>();
	// 	aggregator.setDelimiter(",");

	// 	BeanWrapperFieldExtractor<DataOfUsers> fieldExtractor = new BeanWrapperFieldExtractor<>();
	// 	fieldExtractor.setNames(Util.tokens);
	// 	aggregator.setFieldExtractor(fieldExtractor);

	// 	itemWriter.setLineAggregator(aggregator);

	// 	return itemWriter;
	// }

    // @Bean
    @Override
    public void write(java.util.List<? extends DataOfUsers> items) throws Exception {
        System.out.println(String.format("Received list of size: %s", items.size()));
						
        for(DataOfUsers item : items) {
            System.out.println(item.getId());
        }
        System.out.println();
        
    }
}
