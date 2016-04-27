package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Salle;
import persistances.DAO;

public class ReserverServlet extends HttpServlet {
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		request.setAttribute("listeSalles", DAO.getInstance().getAllItems(new Salle()));
        this.getServletContext().getRequestDispatcher( "/reserver.jsp" ).forward( request, response );
    }
}
