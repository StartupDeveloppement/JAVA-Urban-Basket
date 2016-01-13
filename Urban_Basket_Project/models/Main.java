package models;

import java.sql.SQLException;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;

import persistances.DAO;

public class Main {

	public static void main(String[] args) {
		try {
			TableUtils.createTable(new JdbcConnectionSource("jdbc:h2:mem:bdd"), Adresse.class);
			TableUtils.createTable(new JdbcConnectionSource("jdbc:h2:mem:bdd"), Salle.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Adresse a = new Adresse(1,"rue",75000,"ville","pays");
		Salle s = new Salle("proprietaire", 2, a);
		DAO dao = new DAO();
		dao.createorUpdateItem(s);
		dao.getAllItems(s).forEach(System.out::println);

	}

}
