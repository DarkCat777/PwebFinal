package controller.billing;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import controller.ACL_Controller;
import controller.PMF;
import model.entity.Billing;

@SuppressWarnings("serial")
public class BillingControllerDelete extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if (ACL_Controller.isAvalible(req, resp)) {
			// Esto utlimo es el servlet Original
			PersistenceManager pm=PMF.get().getPersistenceManager();
			Billing billing = pm.getObjectById(Billing.class, new Long(req.getParameter("id")).longValue());
			if (billing != null) {
				pm.deletePersistent(billing);
			}
			pm.close();
			resp.sendRedirect("/billing");
		}
	}
}