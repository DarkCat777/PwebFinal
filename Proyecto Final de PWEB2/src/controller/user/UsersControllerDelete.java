package controller.user;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import controller.ACL_Controller;
import controller.PMF;
import model.entity.Users;

@SuppressWarnings("serial")
public class UsersControllerDelete extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if(ACL_Controller.isAvalible(req, resp)){
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Users user2 = pm.getObjectById(Users.class, new Long(req.getParameter("id")).longValue());
			if (user2 != null) {
				if ((!user2.getEmail().equalsIgnoreCase("nekitoedmh@gmail.com"))) {
					pm.deletePersistent(user2);
				}
			}
			pm.close();
			resp.sendRedirect("/user");
		}
	}
}