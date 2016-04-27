package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import enums.PagesList;
import servlets.LoginServlet;
import servlets.LogoutServlet;

/**
 * Servlet Filter implementation class ConnectedFilter
 */
@WebFilter("/ConnectedFilter")
public class ConnectedFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        if (null == session.getAttribute(LoginServlet.ATT_USER)) {
        	 request.getRequestDispatcher(PagesList.VUE_LOGIN.getValue()).forward(request, response);
        } else {
            chain.doFilter(req, res);
        }
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
