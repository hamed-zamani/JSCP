package ir.hamedz.jscp;

import ir.hamedz.jscp.exception.ScpServerException;

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


public class ScpServer {
	private enum Status {
		STOPPED, STARTING, STARTED, STOPPING
	}

	private static final SshServer sshd = SshServer.setUpDefaultServer();
	public static final SshPasswordAuthenticator passwordAuth = new SshPasswordAuthenticator();
	public static final SshPublicKeyAuthenticator publicKeyAuth = new SshPublicKeyAuthenticator();

	private static Status status = Status.STOPPED;

	public static void start(int port) throws ScpServerException{
		try {
			if (status == Status.STOPPED) {
				status = Status.STARTING;

				sshd.setPort(port);
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

				System.err.println("SCP Server has been started successfully.");
				status = Status.STARTED;
			}
			else{
				throw new ScpServerException("The SCP Server is not STOPPED!");
			}
			
		} catch (Exception ex) {
			throw new ScpServerException(ex.toString());
		}
	}

	public static void stop() throws ScpServerException{
		try{
			if (status == Status.STARTED) {
				status = Status.STOPPING;
				sshd.stop();
				System.err.println("SCP Server has been stopped successfully.");
					
				status = Status.STOPPED;
			}
			else {
				throw new ScpServerException("The SCP Server is not STARTED!");
			}
		} catch (Exception ex){
			throw new ScpServerException(ex.toString());
		}
	}
}
