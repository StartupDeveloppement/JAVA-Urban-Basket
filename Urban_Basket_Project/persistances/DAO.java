package persistances;

import java.sql.SQLException;
import java.util.Collections;
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
	
	public synchronized List<Object> getAllItems(DBClass item){
		try {
			Dao dao = this.createDAO(item);
			return (List<Object>)dao.queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
	@SuppressWarnings("unchecked")
	public synchronized void createorUpdateItem(DBClass item){
		try {
			this.createDAO(item).createOrUpdate(item);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void deleteItem(DBClass item){
		try {
			Dao dao = this.createDAO(item);
			dao.delete(item);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
}
