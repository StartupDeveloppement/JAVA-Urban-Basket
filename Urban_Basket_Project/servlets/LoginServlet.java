package servlets;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import enums.PagesList;
import forms.ConnexionForm;
import models.Adresse;
import models.Reservation;
import models.Salle;
import models.User;
import persistances.DAO;

public class LoginServlet extends HttpServlet {
	
    public static final String ATT_USER = "user";
    public static final String ATT_FORM = "form";
    public static final String ATT_SESSION_USER = "userSession";
    
    private static boolean init = false;
    
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	
    	//A supprimer quand une BDD sera prête
		initBDD();
    	
        this.getServletContext().getRequestDispatcher(PagesList.VUE_LOGIN.getValue()).forward( request, response );
    }

	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		ConnexionForm form = new ConnexionForm();
        User user = form.connectUser(request);
        HttpSession session = request.getSession();
        if (form.getErreurs().isEmpty()){
        	session.setAttribute(ATT_USER, user);
        	response.sendRedirect(PagesList.REDIRECT_HOME.getValue());
        }else{
        	session.setAttribute(ATT_USER, null);
        	this.getServletContext().getRequestDispatcher(PagesList.VUE_LOGIN.getValue()).forward( request, response );
        }
    }
	
	private void initBDD() {
		if(!init){
			init = true;
			
//			User u = new User("root", "root", "Paul", "POLO", "01/01/2000", "mail@polo.fr");
//			User u2 = new User("nico", "nico", "Nico", "NICO", "01/01/2000", "mail@nico.fr");
			Adresse a = new Adresse(1,"rue",75000,"ville","pays");
			Salle s = new Salle("proprietaire", 2, a, 40.714224,-73.961452);
			Salle s2 = new Salle("proprietaire", 2, a, 30,30);
//			Reservation r = new Reservation(LocalDateTime.now(), LocalDateTime.now(), u, s);
//			Reservation r2 = new Reservation(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1), u2, s);
			
			DAO dao = DAO.getInstance();
//			dao.createorUpdateItem(u);
//			dao.createorUpdateItem(u2);
//			dao.createorUpdateItem(r);
//			dao.createorUpdateItem(r2);
			dao.createorUpdateItem(s);
			dao.createorUpdateItem(s2);
//			dao.getAllItems(u).forEach(System.out::println);
			dao.getAllItems(a).forEach(System.out::println);
			dao.getAllItems(s).forEach(System.out::println);
//			dao.getAllItems(r).forEach(System.out::println);
		}
	}
}
