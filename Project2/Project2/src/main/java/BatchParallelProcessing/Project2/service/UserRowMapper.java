package BatchParallelProcessing.Project2.service;

import java.sql.SQLException;
import java.sql.ResultSet;
import org.springframework.jdbc.core.RowMapper;
import BatchParallelProcessing.Project2.Entity.DataOfUsers;

public class UserRowMapper implements RowMapper<DataOfUsers> {

	@Override
	public DataOfUsers mapRow(ResultSet rs, int rowNum) throws SQLException {
		DataOfUsers user = new DataOfUsers();
		
		user.setId(rs.getLong("id"));
		user.setFirstName(rs.getString("Firstname"));
		user.setLastName(rs.getString("Lastname"));
		user.setEmail(rs.getString("Email"));

		
		//order.setDate(fieldSet.readDate("date"));
		
		return user;
	}

}