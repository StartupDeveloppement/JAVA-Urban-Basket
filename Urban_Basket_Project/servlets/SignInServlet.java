package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import enums.PagesList;
import forms.SignInForm;

@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(PagesList.VUE_SIGNIN.getValue()).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SignInForm form = new SignInForm();
        form.checkUserName(request);
        HttpSession session = request.getSession();
        if (form.getErreurs().isEmpty()){
        	response.sendRedirect(PagesList.REDIRECT_LOGIN.getValue());
        }else{
        	session.setAttribute(LoginServlet.ATT_USER, null);
        	request.setAttribute("form", form);
        	this.getServletContext().getRequestDispatcher(PagesList.VUE_SIGNIN.getValue()).forward(request, response);
        }
	}

}
