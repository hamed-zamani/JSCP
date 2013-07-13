import ir.hamedz.jscp.ScpServer;

public class Main{
	public static void main(String []args){
		try{
			ScpServer.passwordAuth.setUsername("test");
			ScpServer.passwordAuth.setPassword("test");
			ScpServer.start(8022);
		} catch (Exception ex){
			System.err.println(ex.toString());
		}
	}
}