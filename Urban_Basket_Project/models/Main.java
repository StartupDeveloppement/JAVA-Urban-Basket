package models;

import java.time.LocalDateTime;

import persistances.DAO;

public class Main {

	public static void main(String[] args) {
		User u = new User("root", "root", "Paul", "POLO", "01/01/2000", "mail@polo.fr");
		Adresse a = new Adresse(1,"rue",75000,"ville","pays");
		Salle s = new Salle("proprietaire", 2, a);
		Reservation r = new Reservation(LocalDateTime.now(), LocalDateTime.now(), u, s);
		
		DAO dao = new DAO();
		dao.init();
		
		dao.createorUpdateItem(u);
		dao.createorUpdateItem(r);
		dao.getAllItems(u).forEach(System.out::println);
		dao.getAllItems(a).forEach(System.out::println);
		dao.getAllItems(s).forEach(System.out::println);
		dao.getAllItems(r).forEach(System.out::println);

	}
}