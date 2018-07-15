package controller.access;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import controller.ACL_Controller;
import controller.PMF;
import model.entity.Access;
import model.entity.Resource;
import model.entity.Role;

@SuppressWarnings("serial")
public class AccessControllerView extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		if (ACL_Controller.isAvalible(req, resp)) {
			Access access2 = pm.getObjectById(Access.class, Long.parseLong(req.getParameter("id")));
			String query4 = "SELECT FROM " + Role.class.getName();
			String query5 = "SELECT FROM " + Resource.class.getName();
			List<Role> roles = (List<Role>) pm.newQuery(query4).execute();
			List<Resource> resources = (List<Resource>) pm.newQuery(query5).execute();
			pm.close();
			req.setAttribute("roles", roles);
			req.setAttribute("resources", resources);
			req.setAttribute("access", access2);
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/View/Access/view.jsp");
			rd.forward(req, resp);
			// req.getRequestDispatcher("/view.jsp").forward(req,
			// resp);
		}
	}
}