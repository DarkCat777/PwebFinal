package controller.resource;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import controller.access.AccessControllerDelete;
import controller.ACL_Controller;
import controller.PMF;
import model.entity.Access;
import model.entity.Resource;

@SuppressWarnings("serial")
public class ResourceControllerDelete extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if (ACL_Controller.isAvalible(req, resp)) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Long idResource = Long.parseLong(req.getParameter("id"));
			Resource resource2 = pm.getObjectById(Resource.class, idResource);
			if (resource2 != null) {
				// Selecionando y Borrando Acces implicados con los
				// recursos
				String query4 = "SELECT FROM " + Access.class.getName() + " WHERE idUrl==" + idResource;
				List<Access> access2 = (List<Access>) pm.newQuery(query4).execute();
				for (Access acce : access2) {
					AccessControllerDelete.delete(acce.getId());
				}
				pm.deletePersistent(resource2);
			}
			pm.close();
			resp.sendRedirect("/resource");
		}
	}
}