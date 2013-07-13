package ir.hamedz.jscp;

import org.apache.sshd.server.PasswordAuthenticator;
import org.apache.sshd.server.session.ServerSession;

public class SshPasswordAuthenticator implements PasswordAuthenticator {
	private String username;
	private String password;

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public boolean authenticate(String username, String password,
			ServerSession session) {
		return username.equals(this.username) && password.equals(this.password);
	}
}