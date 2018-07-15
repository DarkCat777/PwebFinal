package controller.resource;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;

import controller.ACL_Controller;
import controller.PMF;
import model.entity.Resource;

@SuppressWarnings("serial")
public class ResourceControllerView extends HttpServlet {
	@SuppressWarnings("unused")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (ACL_Controller.isAvalible(req, resp)) {
			User user = UserServiceFactory.getUserService().getCurrentUser();
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Resource resource2 = pm.getObjectById(Resource.class, Long.parseLong(req.getParameter("id")));
			pm.close();
			req.setAttribute("resource", resource2);
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/View/Resource/view.jsp");
			rd.forward(req, resp);
			// req.getRequestDispatcher("/view.jsp").forward(req,
			// resp);
		}
	}
}
