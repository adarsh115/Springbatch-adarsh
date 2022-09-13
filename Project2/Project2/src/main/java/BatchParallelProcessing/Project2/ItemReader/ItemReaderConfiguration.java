package BatchParallelProcessing.Project2.ItemReader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import BatchParallelProcessing.Project2.Entity.DataOfUsers;
import BatchParallelProcessing.Project2.Util.Util ;
import BatchParallelProcessing.Project2.service.DataOfUserFieldSetMapper;
import BatchParallelProcessing.Project2.service.UserRowMapper;

import javax.sql.DataSource;


@Configuration
@ComponentScan
public class ItemReaderConfiguration {
	@Autowired
	public DataSource dataSource;

	
	@Bean
	public ItemReader<DataOfUsers> itemReadercsv() {
		FlatFileItemReader<DataOfUsers> itemReader = new FlatFileItemReader<>();
		itemReader.setLinesToSkip(1);
		itemReader.setResource(new FileSystemResource("Files/million user Records - 100000 Records.csv"));
		
		DefaultLineMapper<DataOfUsers> lineMapper = new DefaultLineMapper<DataOfUsers>();
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		

		tokenizer.setNames(Util.tokens);
		lineMapper.setLineTokenizer(tokenizer);
		lineMapper.setFieldSetMapper(new DataOfUserFieldSetMapper());
		
		itemReader.setLineMapper(lineMapper);
		
		return itemReader;
	}

	@Bean
	public ItemReader<DataOfUsers> itemReaderdb() {
		return new JdbcCursorItemReaderBuilder<DataOfUsers>()
				.dataSource(dataSource)
				.name("JdbcCursorItemReader")
				.sql(Util.USER_SQL)
				.rowMapper(new UserRowMapper())
				.build();
	}

}
