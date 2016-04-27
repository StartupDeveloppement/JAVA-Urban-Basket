package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PlanningServlet
 */
@WebServlet("/PlanningServlet")
public class PlanningServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final String VUE_PLANNING = "/planning.jsp";
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("idSalle", request.getParameter("idSalle"));
		this.getServletContext().getRequestDispatcher(VUE_PLANNING).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
