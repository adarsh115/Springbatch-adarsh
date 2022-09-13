package BatchParallelProcessing.Project2.service;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import BatchParallelProcessing.Project2.Entity.DataOfUsers;

public class DataOfUserFieldSetMapper implements FieldSetMapper<DataOfUsers> {

	@Override
	public DataOfUsers mapFieldSet(FieldSet fieldSet) throws BindException {
		
		DataOfUsers dataUser = new DataOfUsers();
		dataUser.setId(fieldSet.readLong("id"));
		dataUser.setFirstName(fieldSet.readString("Firstname"));
		dataUser.setLastName(fieldSet.readString("Lastname"));
		dataUser.setEmail(fieldSet.readString("Email"));
		
		return dataUser;
	}

}
