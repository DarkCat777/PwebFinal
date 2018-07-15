package controller.resource;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.*;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;

import controller.ACL_Controller;
import controller.PMF;
import model.entity.Resource;

@SuppressWarnings("serial")
public class ResourceControllerEdit extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if (ACL_Controller.isAvalible(req, resp)) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Resource resource2 = pm.getObjectById(Resource.class, Long.parseLong(req.getParameter("id")));
			pm.close();
			req.setAttribute("resource", resource2);
			req.getRequestDispatcher("/WEB-INF/View/Resource/edit.jsp").forward(req, resp);
		}
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Resource resource = pm.getObjectById(Resource.class, Long.parseLong(req.getParameter("id")));
		boolean status = Boolean.parseBoolean(req.getParameter("status"));
		String url = req.getParameter("url");

		String query = "SELECT FROM " + Resource.class.getName();
		List<Resource> resources = (List<Resource>) pm.newQuery(query).execute();
		boolean duplicado = false;
		for (Resource res : resources) {
			if (res.getUrl().equals(url) && (!res.getId().equals(resource.getId()))) {
				duplicado = true;
			}
		}
		if (duplicado) {
			pm.close();
			String error = "ELEMENTO EDITADO DUPLICADO";
			req.setAttribute("error", error);
			req.getRequestDispatcher("/WEB-INF/View/Resource/error2.jsp").forward(req, resp);
		} else {
			resource.setStatus(status);
			resource.setUrl(url);
			pm.close();
			resp.sendRedirect("/resource/view?id=" + req.getParameter("id"));
		}

	}
}