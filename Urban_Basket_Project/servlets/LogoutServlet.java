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

public class LogoutServlet extends HttpServlet {
	
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect(PagesList.REDIRECT_HOME.getValue());
    }
}
