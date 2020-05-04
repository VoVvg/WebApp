package app.servlets;

import app.entities.User;
import app.model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "login", urlPatterns = "/login")
public class Login extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getServletContext().getRequestDispatcher("/login.jsp").forward(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String	name 		= req.getParameter("name");
		String	pass	= req.getParameter("pass");
		Model	model 		= Model.getInstance();
		if(model.isExists(name, pass)){
				HttpSession session = req.getSession();
				session.setAttribute("user", name);
				resp.sendRedirect("home");
		}
		else {
			req.setAttribute("Fail", "fail");
			req.getServletContext().getRequestDispatcher("/login.jsp").forward(req,resp);
		}
	}
}
