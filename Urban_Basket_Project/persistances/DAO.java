package persistances;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.LocalLog;
import com.j256.ormlite.logger.Log;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.table.TableUtils;

import interfaces.DBClass;
import models.Adresse;
import models.Reservation;
import models.Salle;
import models.User;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class DAO {

	private static DAO dao;
	public static boolean initialized = false;

	public static DAO getInstance() {
		if (null == dao) {
			dao = new DAO();
			dao.init();
		}
		return dao;
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

	private Dao<DBClass, Object> createDAO(DBClass object) {
		try {
			return (Dao<DBClass, Object>) DaoManager.createDao(new JdbcConnectionSource("jdbc:h2:mem:bdd"),
					object.getClass());
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}

	public synchronized List<Object> getAllItems(DBClass item) {
		try {
			Dao dao = this.createDAO(item);
			List list = (List<Object>) dao.queryForAll();
			dao.getConnectionSource().close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public synchronized void createorUpdateItem(DBClass item) {
		try {
			Dao dao = this.createDAO(item);
			dao.createOrUpdate(item);
			dao.getConnectionSource().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public synchronized void deleteItem(DBClass item) {
		try {
			Dao dao = this.createDAO(item);
			dao.delete(item);
			dao.getConnectionSource().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean validate(String user, String password) {
		Dao dao = this.createDAO(new User());
		QueryBuilder<User, String> q = dao.queryBuilder();
		Where<User, String> where = q.where();
		try {
			where.eq("pseudo", user).and().eq("mdp", password);
			PreparedQuery<User> query = q.prepare();
			List<User> list = dao.query(query);
			dao.getConnectionSource().close();
			if(null == list || list.isEmpty() || list.size() > 1){
				return false;
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
