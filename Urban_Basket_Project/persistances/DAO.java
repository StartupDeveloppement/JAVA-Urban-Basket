package persistances;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.Dao.CreateOrUpdateStatus;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.LocalLog;
import com.j256.ormlite.logger.Log;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.table.TableUtils;

import exceptions.UserNotFoundException;
import interfaces.DBClass;
import models.Adresse;
import models.Reservation;
import models.Salle;
import models.User;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class DAO {

	private static DAO instance;
	public static boolean initialized = false;

	public static DAO getInstance() {
		if (null == instance) {
			synchronized (DAO.class) {
				if(null == instance){
					instance = new DAO();
					instance.init();
				}
			}
		}
		return instance;
	}

	private void init() {
		if (!DAO.initialized) {
			System.setProperty(LocalLog.LOCAL_LOG_LEVEL_PROPERTY, Log.Level.WARNING.toString());
			try {
				TableUtils.createTable(new JdbcConnectionSource("jdbc:h2:mem:bdd"), Adresse.class);
				TableUtils.createTable(new JdbcConnectionSource("jdbc:h2:mem:bdd"), Salle.class);
				TableUtils.createTable(new JdbcConnectionSource("jdbc:h2:mem:bdd"), User.class);
				TableUtils.createTable(new JdbcConnectionSource("jdbc:h2:mem:bdd"), Reservation.class);

				DAO.initialized = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private <T extends DBClass> Dao<T, Object> createDAO(T object) {
		Dao<T,Object> res = null;
		try {
			res = (Dao<T, Object>) DaoManager.createDao(new JdbcConnectionSource("jdbc:h2:mem:bdd"),
					object.getClass());
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return res;
	}

	public <T extends DBClass> List<T> getItemsWhere(T item, String columnName, Object pkValue) {
		List<T> res = Collections.emptyList();
		try {
			Dao dao = this.createDAO(item);
			res = (List<T>) dao.queryForEq(columnName, pkValue);
			dao.getConnectionSource().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public <T extends DBClass> T getItemById(T item, Object pkValue) {
		return getItemsWhere(item, "ID", pkValue).get(0);
	}
	
	public <T extends DBClass> List<T> getAllItems(T item) {
		List res = Collections.emptyList();
		try {
			Dao dao = this.createDAO(item);
			res = (List<T>) dao.queryForAll();
			dao.getConnectionSource().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public synchronized <T extends DBClass> T create(T item) {
		T res = null;
		try {
			Dao dao = this.createDAO(item);
			dao.create(item);
			dao.getConnectionSource().close();
			res = item;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public synchronized <T extends DBClass> void createorUpdateItem(T item) {
		try {
			Dao dao = this.createDAO(item);
			dao.createOrUpdate(item);
			dao.getConnectionSource().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public synchronized <T extends DBClass> void deleteItem(T item) {
		try {
			Dao dao = this.createDAO(item);
			dao.delete(item);
			dao.getConnectionSource().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public User validate(String user, String password) throws Exception {
		Dao dao = this.createDAO(new User());
		QueryBuilder<User, String> q = dao.queryBuilder();
		Where<User, String> where = q.where();
		try {
			where.eq("pseudo", user).and().eq("mdp", password);
			List<User> list = dao.query(q.prepare());
			dao.getConnectionSource().close();
			if(null == list || list.isEmpty() || list.size() != 1){
				throw new UserNotFoundException("User not found");
			}
			return list.get(0);
		} catch (SQLException e) {
			throw new Exception("Problem occured while accessing database");
		}
	}
}
