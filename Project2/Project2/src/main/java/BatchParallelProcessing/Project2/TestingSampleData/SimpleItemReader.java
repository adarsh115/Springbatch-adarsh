package BatchParallelProcessing.Project2.TestingSampleData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class SimpleItemReader implements ItemReader<String> {

	private List<String> dataset = new ArrayList<String>();
	private Iterator<String> iterator;
	
	public SimpleItemReader() {
		
		for(int i=1; i<=50000; i++) {
			this.dataset.add(i+"");
		}
		

		
		this.iterator = this.dataset.iterator();
	}
	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return iterator.hasNext()? iterator.next() : null;
	}

}
