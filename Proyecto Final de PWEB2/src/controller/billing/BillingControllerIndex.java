package controller.billing;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.Access;
import model.entity.Billing;
import model.entity.Resource;
import model.entity.Users;
//envia el paramentro billing a /WEB-INF/Views/Billing/index.jsp
@SuppressWarnings("serial")
public class BillingControllerIndex extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		User user=UserServiceFactory.getUserService().getCurrentUser();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		if(user==null){
			req.getRequestDispatcher("/user/login").forward(req, resp);
		}else{
			String query="SELECT FROM "+Users.class.getName()+" WHERE email=='"+user.getEmail()+"' && status==true";
			List<Users> users=(List<Users>)pm.newQuery(query).execute();
			if(users.isEmpty()){
				//ERROR NO EXISTE UN USUARIO O NO ESTA ACTIVO
				String codigoError="ERROR NO ES UN USUARIO REGRISTRADO O NO ESTA ACTIVO";
				req.setAttribute("error", codigoError);
				req.getRequestDispatcher("/WEB-INF/View/Users/error.jsp").forward(req, resp);
			}else{
				String query2="SELECT FROM "+Resource.class.getName()+" WHERE url=='"+req.getServletPath()+"' && status==true";
				List<Resource> resource=(List<Resource>)pm.newQuery(query2).execute();
				if(resource.isEmpty()){
					//ERROR NO EXISTE UN RECURSO O NO ESTA ACTIVO
					String codigoError="ERROR NO EXISTE UN RECURSO O NO ESTA ACTIVO";
					req.setAttribute("error", codigoError);
					req.getRequestDispatcher("/WEB-INF/View/Users/error.jsp").forward(req, resp);
				}else{
					String query3="SELECT FROM "+Access.class.getName()+" WHERE idRole=="+users.get(0).getIdRole()+" && idUrl=="+resource.get(0).getId()+" && status==true";
					List<Access> access=(List<Access>)pm.newQuery(query3).execute();
					if(access.isEmpty()){
						//ERROR NO EXISTE UN ACCESO O NO ESTA ACTIVO
						String codigoError="ERROR NO EXISTE UN ACCESO O NO ESTA ACTIVO";
						req.setAttribute("error", codigoError);
						req.getRequestDispatcher("/WEB-INF/View/Users/error.jsp").forward(req, resp);
					}else{
						//Esto utlimo es el servlet Original
						String query4 = "SELECT FROM "+ Billing.class.getName()+" WHERE idUser=="+users.get(0).getId();
						List<Billing> billings = (List<Billing>)pm.newQuery(query4).execute();
						pm.close();
						req.setAttribute("billings", billings);
						req.getRequestDispatcher("/WEB-INF/View/Billing/index.jsp").forward(req, resp);
					}
				}
			}
		}
	}
}
