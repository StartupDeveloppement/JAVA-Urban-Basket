package persistances;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

import interfaces.DBClass;

public class DAO{

	private Dao<DBClass, Object> createDAO(DBClass object){
		try {
			return (Dao<DBClass, Object>) DaoManager.createDao(new JdbcConnectionSource("jdbc:h2:mem:bdd"), object.getClass());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Object> getAllItems(DBClass object) throws SQLException{
		Dao dao = this.createDAO(object);
		return (List<Object>)dao.queryForAll();
	}
}
