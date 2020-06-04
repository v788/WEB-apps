package salary.bean;

import java.io.Serializable;

import javax.servlet.annotation.WebServlet;

@WebServlet("/loginInfo")
public class LoginInfo implements Serializable {

	private String email;
	private String password;
	private String user_name;


	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
