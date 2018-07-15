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
public class BillingControllerEdit extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (ACL_Controller.isAvalible(req, resp)) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			// Esto utlimo es el servlet Original
			Billing billing = pm.getObjectById(Billing.class, Long.parseLong(req.getParameter("id")));
			pm.close();
			req.setAttribute("billing", billing);
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/View/Billing/edit.jsp");
			rd.forward(req, resp);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Billing billing = pm.getObjectById(Billing.class, Long.parseLong(req.getParameter("id")));
		billing.setDateIn();
		billing.setCustomer(req.getParameter("customer"));
		billing.setAddress(req.getParameter("address"));
		billing.setDescriptionProduct(req.getParameter("descriptionproduct"));
		billing.setUnitPriceProduct(Double.parseDouble(req.getParameter("unitpriceproduct")));
		billing.setMountProduct(Double.parseDouble(req.getParameter("mountProduct")));
		billing.setTotal();
		pm.close();
		resp.sendRedirect("/billing/view?id=" + req.getParameter("id"));// Enviar
																		// al
																		// ViewController
	}
}