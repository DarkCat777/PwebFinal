package controller.billing;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;

import controller.ACL_Controller;
import controller.PMF;
import model.entity.Billing;
import model.entity.Users;

@SuppressWarnings("serial")
public class BillingControllerAdd extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if (ACL_Controller.isAvalible(req, resp)) {
			// Servlet Original
			req.getRequestDispatcher("/WEB-INF/View/Billing/add.jsp").forward(req, resp);
		}
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		User userInLine = UserServiceFactory.getUserService().getCurrentUser();
		String query = "SELECT FROM " + Users.class.getName() + " WHERE email=='" + userInLine.getEmail()
				+ "' && status==true";
		List<Users> user = (List<Users>) pm.newQuery(query).execute();
		Long idUser = null;
		if (!user.isEmpty()) {
			idUser = user.get(0).getId();
		}
		String customer = req.getParameter("customer");
		String address = req.getParameter("address");
		boolean status = true;
		String descriptionProduct = req.getParameter("descriptionproduct");
		double unitPriceProduct = Double.parseDouble(req.getParameter("unitpriceproduct"));
		double mountProduct = Double.parseDouble(req.getParameter("mount"));
		// Falta pensar como hacerlo
		try {
			Billing billing = new Billing(customer, address, status, descriptionProduct, unitPriceProduct, mountProduct,
					idUser);
			pm.makePersistent(billing);
			pm.close();
			resp.sendRedirect("/billing");// ControllerIndex.java
		} catch (Exception e) {
			resp.getWriter().println(e.getMessage());
		}
	}
}