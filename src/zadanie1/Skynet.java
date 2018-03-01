package zadanie1;

public class Skynet {
	
	private String login = "Reese";
	private String password = "#Terminate";
		
	public static void main(String args[]) {
		Skynet skynet = new Skynet();
		skynet.run();
	}
	
	public void run() {
		readCredentials();
		printSkyNetLogo();
		runLoginPanel();
	}
	
	private void runLoginPanel() {
		String readLogin;
		String readPassword;
		boolean credentialsOK;
		do {
			readLogin = getTextFromInput("Login");
			readPassword = getTextFromInput("Password");
			credentialsOK = verifyCredentials(readLogin, readPassword); 
			if (!credentialsOK) {
				System.out.println("Wrong login or password. Try again.");
			}
		} while (!credentialsOK);
		System.out.println();
		System.out.println("Welcome to SKYNET...");
	}
	
	private String getTextFromInput(String label) {
		// TODO print label and return the user input 
		return null;
	}
	
	private boolean verifyCredentials(String login, String password) {
		return this.login.equals(login) && this.password.equals(password);
	}
	 
	private void readCredentials() {
		// TODO read credentials from the file "res/zadanie1/skynet.conf"
		// TODO print "INFO System reconfigured..." if correctly read
		// TODO print "INFO No valid conf data" if could not read credentials
	}
	
	private void printSkyNetLogo() {
		// TODO print logo from file "res/zadanie1/logo.txt"
	}
}
