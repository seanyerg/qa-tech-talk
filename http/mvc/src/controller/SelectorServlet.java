package controller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Coworker;

@WebServlet(name = "SelectorServlet", value = "/select.do", initParams = @WebInitParam(name = "view", value = "/conversations.jsp"))
public class SelectorServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<Integer, Coworker> coworkers = (Map<Integer, Coworker>) request.getServletContext().getAttribute("coworkerMap");
		request.setAttribute("coworker", coworkers.get(Integer.parseInt(request.getParameter("coworker"))));
		request.getRequestDispatcher(getServletConfig().getInitParameter("view")).forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Coworker> coworkers = (Map<String, Coworker>) request.getServletContext().getAttribute("coworkerMap");
		request.getSession().setAttribute("coworker", coworkers.get(Integer.parseInt(request.getParameter("coworker"))));
		response.sendRedirect(request.getServletContext().getContextPath() + getServletConfig().getInitParameter("view"));
	}
}
