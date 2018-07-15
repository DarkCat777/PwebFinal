package controller.billing;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import controller.ACL_Controller;
import controller.PMF;
import model.entity.Billing;

@SuppressWarnings("serial")
public class BillingControllerView extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (ACL_Controller.isAvalible(req, resp)) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Billing billing = pm.getObjectById(Billing.class, Long.parseLong(req.getParameter("id")));
			pm.close();
			req.setAttribute("billing", billing);
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/View/Billing/view.jsp");
			rd.forward(req, resp);
		}
	}
}