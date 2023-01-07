package ch.bbzbl.dao;

import ch.bbzbl.entity.GeneralEntity;
import ch.bbzbl.entity.Role;
import ch.bbzbl.entity.User;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityDAO extends GenericDAO<GeneralEntity> {

	private static final long serialVersionUID = 1L;

	public EntityDAO() {
		super(GeneralEntity.class);
	}

	public void delete(GeneralEntity entity) {
        	super.delete(entity.getId());
	}

	public List<GeneralEntity> findUserIfExists( User user) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("user", user);

		if (user.getRole() == Role.USER){
		return super.findAll(GeneralEntity.FIND_ENTITY_BY_User, parameters);
		}else {
			return super.findAll();
		}
	}
}
