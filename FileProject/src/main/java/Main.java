import java.io.IOException;

import views.Welcome;

public class Main {
	public static void main(String args[]) throws IOException {
		Welcome w = new Welcome();
		do {
			w.welcomeScreen();
		}
		while(true);
	}
}
