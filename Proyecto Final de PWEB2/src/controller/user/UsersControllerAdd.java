package controller.user;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.*;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;

import controller.ACL_Controller;
import controller.PMF;
import model.entity.Role;
import model.entity.Users;

@SuppressWarnings("serial")
public class UsersControllerAdd extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if (ACL_Controller.isAvalible(req, resp)) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			String query4 = "SELECT FROM " + Role.class.getName();
			List<Role> roles = (List<Role>) pm.newQuery(query4).execute();
			pm.close();
			req.setAttribute("roles", roles);
			req.getRequestDispatcher("/WEB-INF/View/Users/add.jsp").forward(req, resp);
		}
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");// dd/MM/yyyy
		String birthReq = req.getParameter("birth");
		String email = req.getParameter("email");
		Date birth = null;
		boolean status = Boolean.parseBoolean(req.getParameter("status"));
		boolean gender = Boolean.parseBoolean(req.getParameter("gender"));
		Long idRole = Long.parseLong(req.getParameter("idrole"));
		try {
			birth = formatoDelTexto.parse(birthReq);
		} catch (ParseException e) {
			resp.getWriter().println(e.getMessage());
		}
		try {
			Users user = new Users(email, birth, status, gender, idRole);
			String query = "SELECT FROM " + Users.class.getName();
			List<Users> users = (List<Users>) pm.newQuery(query).execute();
			boolean duplicado = false;
			for (Users us : users) {
				if (us.getEmail().equals(user.getEmail())) {
					duplicado = true;
				}
			}
			if (duplicado) {
				pm.close();
				String error = "ELEMENTO DUPLICADO";
				req.setAttribute("error", error);
				req.getRequestDispatcher("/WEB-INF/View/Users/error2.jsp").forward(req, resp);
			} else {
				pm.makePersistent(user);
				pm.close();
				resp.sendRedirect("/user");// ControllerIndex.java
			}

		} catch (Exception e) {
			resp.getWriter().println(e.getMessage());
		}
	}
}