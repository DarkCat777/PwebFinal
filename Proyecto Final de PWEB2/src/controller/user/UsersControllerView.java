package controller.user;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import controller.ACL_Controller;
import controller.PMF;
import model.entity.Role;
import model.entity.Users;

@SuppressWarnings("serial")
public class UsersControllerView extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(ACL_Controller.isAvalible(req, resp)){
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Users user2 = pm.getObjectById(Users.class, Long.parseLong(req.getParameter("id")));
			Role role = null;
			if (user2.getIdRole() != null) {
				role = pm.getObjectById(Role.class, user2.getIdRole());
			}
			pm.close();
			req.setAttribute("user", user2);
			req.setAttribute("role", role);
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/View/Users/view.jsp");
			rd.forward(req, resp);
			// req.getRequestDispatcher("/view.jsp").forward(req,
			// resp);
		}else{
			String codigoError = "ALGO OCURRIO [ERROR INESPERADO]";
			req.setAttribute("error", codigoError);
			req.getRequestDispatcher("/WEB-INF/View/Users/error.jsp").forward(req, resp);
		}
	}
}