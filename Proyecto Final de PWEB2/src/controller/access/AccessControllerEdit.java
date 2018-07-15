package controller.access;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ACL_Controller;
import controller.PMF;
import model.entity.Access;
import model.entity.Resource;
import model.entity.Role;

@SuppressWarnings("serial")
public class AccessControllerEdit extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (ACL_Controller.isAvalible(req, resp)) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			String query4 = "SELECT FROM " + Role.class.getName();
			String query5 = "SELECT FROM " + Resource.class.getName();
			List<Role> roles = (List<Role>) pm.newQuery(query4).execute();
			List<Resource> resources = (List<Resource>) pm.newQuery(query5).execute();
			Access access2 = pm.getObjectById(Access.class, Long.parseLong(req.getParameter("id")));
			pm.close();
			req.setAttribute("access", access2);
			req.setAttribute("roles", roles);
			req.setAttribute("resources", resources);
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/View/Access/edit.jsp");
			rd.forward(req, resp);
		}
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Access access = pm.getObjectById(Access.class, Long.parseLong(req.getParameter("id")));
		boolean status = Boolean.parseBoolean(req.getParameter("status"));
		Long idRole = Long.parseLong(req.getParameter("idrole"));
		Long idResource = Long.parseLong(req.getParameter("idresource"));
		String query = "SELECT FROM " + Access.class.getName();
		List<Access> access2 = (List<Access>) pm.newQuery(query).execute();
		boolean duplicado = false;

		for (Access acc : access2) {
			if (acc.getIdRole().equals(idRole) && acc.getIdUrl().equals(idResource)
					&& (!acc.getId().equals(access.getId()))) {
				duplicado = true;
			}
		}
		if (duplicado) {
			pm.close();
			String error = "ELEMENTO EDITADO DUPLICADO";
			req.setAttribute("error", error);
			req.getRequestDispatcher("/WEB-INF/View/Access/error2.jsp").forward(req, resp);
		} else {
			access.setIdRole(idRole);
			access.setIdUrl(idResource);
			access.setStatus(status);
			pm.close();
			resp.sendRedirect("/access/view?id=" + req.getParameter("id"));
		}

	}
}