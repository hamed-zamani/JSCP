
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import org.apache.sshd.SshServer;
import org.apache.sshd.common.NamedFactory;
import org.apache.sshd.server.Command;
import org.apache.sshd.server.UserAuth;
import org.apache.sshd.server.auth.UserAuthNone;
import org.apache.sshd.server.command.ScpCommandFactory;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.sftp.SftpSubsystem;
import org.slf4j.Logger;

import com.github.stepinto.asshd.SimplePasswordAuthenticator;
import com.github.stepinto.asshd.SimplePublicKeyAuthenticator;

public class SCPConnection {
  
	private enum Status {
		STOPPED, STARTING, STARTED, STOPPING
	}

	private static final int PORT = 22; //You can change the port. Port 22 is default port for SSH connections.

	private static final SshServer sshd = SshServer.setUpDefaultServer();
	public static final SimplePasswordAuthenticator passwordAuth = new SimplePasswordAuthenticator();
	public static final SimplePublicKeyAuthenticator publicKeyAuth = new SimplePublicKeyAuthenticator();

	private static Status status = Status.STOPPED;
		
	public static void onStartButtonClicked(Logger log) {
		try {
			if (status == Status.STOPPED) {
				status = Status.STARTING;

				sshd.setPort(PORT);
				sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider("hostkey.ser"));
				List<NamedFactory<UserAuth>> userAuthFactories = new ArrayList<NamedFactory<UserAuth>>();
			  userAuthFactories.add(new UserAuthNone.Factory());
			  sshd.setUserAuthFactories(userAuthFactories);
				sshd.setPasswordAuthenticator(passwordAuth);
				sshd.setCommandFactory(new ScpCommandFactory());
				List<NamedFactory<Command>> namedFactoryList = new ArrayList<NamedFactory<Command>>();
			  namedFactoryList.add(new SftpSubsystem.Factory());
			  sshd.setSubsystemFactories(namedFactoryList);

				sshd.start();
				log.info("SSHD is started.");
				status = Status.STARTED;
			}
			else if (status == Status.STARTED) {
				status = Status.STOPPING;
				
				sshd.stop();
				log.info("SSHD is stopped.");
				
				status = Status.STOPPED;
			}
		} catch (IOException e) {
			log.error(e.toString());
		} catch (InterruptedException e) {
			log.error(e.toString());
		}
	}
}
