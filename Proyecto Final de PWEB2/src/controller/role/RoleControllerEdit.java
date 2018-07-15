package controller.role;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import controller.ACL_Controller;
import controller.PMF;
import model.entity.Role;

@SuppressWarnings("serial")
public class RoleControllerEdit extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (ACL_Controller.isAvalible(req, resp)) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Role role = pm.getObjectById(Role.class, Long.parseLong(req.getParameter("id")));
			pm.close();
			req.setAttribute("role", role);
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/View/Role/edit.jsp");
			rd.forward(req, resp);
		}
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Role role = pm.getObjectById(Role.class, Long.parseLong(req.getParameter("id")));
		String name = req.getParameter("name");
		boolean status = Boolean.parseBoolean(req.getParameter("status"));


		String query = "SELECT FROM " + Role.class.getName();
		List<Role> roles = (List<Role>) pm.newQuery(query).execute();
		boolean duplicado = false;
		for (Role rol : roles) {
			if (rol.getName().equalsIgnoreCase(name) && (!rol.getId().equals(role.getId()))) {
				duplicado = true;
			}
		}
		if (duplicado) {
			pm.close();
			String error = "ELEMENTO EDITADO DUPLICADO";
			req.setAttribute("error", error);
			req.getRequestDispatcher("/WEB-INF/View/Role/error2.jsp").forward(req, resp);
		} else {
			role.setName(name);
			role.setStatus(status);
			pm.close();
			resp.sendRedirect("/role/view?id=" + req.getParameter("id"));// Enviar
																			// al
																			// ViewController
		}
	}
}