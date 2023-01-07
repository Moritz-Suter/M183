package ch.bbzbl.bean;


import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import ch.bbzbl.entity.Role;
import ch.bbzbl.entity.User;
import ch.bbzbl.facade.UserFacade;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

@ManagedBean
@RequestScoped
public class LoginBean extends AbstractBean {
	public static final String ATTR_USER = "user";

	@ManagedProperty(value = UserBean.DI_NAME)
	private UserBean userBean;

	private String username;
	private String password;
	
	UserFacade userFacade = new UserFacade();

	public String login() {
	byte[] hash = null;
	String hashedPwd = null;
		try {
			User user = userFacade.getUserIfExistsByName(username);

			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			password = user.getSalt()+password;
			// Add password bytes to digest
			md.update(password.getBytes());
			// Get the hash's bytes
			byte[] bytes = md.digest();
			// This bytes[] has bytes in decimal format. Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			// Get complete hashed password in hex format
			hashedPwd = sb.toString();
		}catch (Exception e){
			displayErrorMessageToUser("Failure with hashing! Try again!");
		}
		User user = userFacade.getUserIfExists(this.username, hashedPwd);


		if (user != null) {
			userBean.setLoggedInUser(user);
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
			req.getSession().setAttribute(ATTR_USER, user);
			return "/pages/protected/index.xhtml?faces-redirect=true";
			
		} else {
			keepDialogOpen();
			displayErrorMessageToUser("Wrong Username/Password. Try again");
			
		}
		return null;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

}
