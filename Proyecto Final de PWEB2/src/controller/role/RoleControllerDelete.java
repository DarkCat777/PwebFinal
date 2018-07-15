package controller.role;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import controller.ACL_Controller;
import controller.PMF;
import controller.access.AccessControllerDelete;
import controller.user.UsersControllerEdit;
import model.entity.Access;
import model.entity.Role;
import model.entity.Users;

@SuppressWarnings("serial")
public class RoleControllerDelete extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if (ACL_Controller.isAvalible(req, resp)) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Long idRole = Long.parseLong(req.getParameter("id"));
			Role role = pm.getObjectById(Role.class, idRole);
			if (role != null) {
				if (!role.getName().equals("Administrador")) {
					// Seleccionando y borrando Accesos
					String query4 = "SELECT FROM " + Access.class.getName() + " WHERE idRole==" + idRole;
					List<Access> access = (List<Access>) pm.newQuery(query4).execute();
					if (!access.isEmpty()) {
						for (Access acce : access) {
							AccessControllerDelete.delete(acce.getId());
						}
					}
					// Selecionando y cambiando idUser=null en los
					// Usuarios Afectados
					String query5 = "SELECT FROM " + Users.class.getName() + " WHERE idRole==" + idRole;
					List<Users> users = (List<Users>) pm.newQuery(query5).execute();
					if (!users.isEmpty()) {
						for (Users us : users) {
							UsersControllerEdit.changeIdRole(us.getId());
						}
					}
					pm.deletePersistent(role);
				}
			}
			pm.close();
			resp.sendRedirect("/role");
		}
	}
}