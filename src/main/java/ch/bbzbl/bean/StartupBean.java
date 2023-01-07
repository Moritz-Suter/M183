package ch.bbzbl.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import ch.bbzbl.dao.EntityManagerHelper;
import ch.bbzbl.dao.UserDAO;
import ch.bbzbl.entity.Role;
import ch.bbzbl.entity.User;
import ch.bbzbl.facade.UserFacade;

@ApplicationScoped
@ManagedBean(eager = true)
public class StartupBean {

	/**
	 * initialize EntityManagerFactory at application startup
	 */
	@PostConstruct
	public void init() {
		EntityManagerHelper.init();

		// Create dummy ADMIN user if not exist
		UserFacade facade = new UserFacade();
		User u = facade.getUserIfExists("admin", "50317b958ee25a1e14449aeb95db5245");
		if (u == null) {
			UserDAO dao = new UserDAO();
			EntityManagerHelper.beginTransaction();
			dao.save(new User("admin", "50317b958ee25a1e14449aeb95db5245","abcd" , Role.ADMIN ));
			EntityManagerHelper.commitAndCloseTransaction();
		}
		u = facade.getUserIfExists("a84f0fd08cfa73f99ac75f9b35561117", "user");
		if (u == null) {
			UserDAO dao = new UserDAO();
			EntityManagerHelper.beginTransaction();
			dao.save(new User("user", "a84f0fd08cfa73f99ac75f9b35561117", "dcba", Role.USER));
			EntityManagerHelper.commitAndCloseTransaction();
		}
	}
}
