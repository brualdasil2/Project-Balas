
public class Main {
	public static int frameWidth = 1280;
 	public static int frameHeight = 720;

 	public static void main (String[] args) {
	
		//Launcher
		
		Game game = new Game("Project Balas", frameWidth, frameHeight);	
		game.start(); //Start thread
	
 	}
}
