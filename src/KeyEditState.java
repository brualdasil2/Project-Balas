import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFileChooser;

public class KeyEditState extends State {
	
	private Button backButton, p1saveButton, p1loadButton, p2saveButton, p2loadButton;
	
	private static boolean rendered;
	
	private static KeyEditButton p1leftButton, p1rightButton, p1upButton, p1shieldButton, p1jumpButton, p1attackButton, p1specialButton, p1airdashButton,
	p2leftButton, p2rightButton, p2upButton, p2shieldButton, p2jumpButton, p2attackButton, p2specialButton, p2airdashButton,
	pauseButton;
	

	
	public KeyEditState(Game game) {
		
		super(game);	
		p1leftButton = new KeyEditButton(game, 300, 170, 100, 40, "A", KeyEvent.VK_A);
		p1rightButton = new KeyEditButton(game, 300, 220, 100, 40, "D", KeyEvent.VK_D);
		p1upButton = new KeyEditButton(game, 300, 270, 100, 40, "W", KeyEvent.VK_W);
		p1shieldButton = new KeyEditButton(game, 300, 320, 100, 40, "S", KeyEvent.VK_S);
		p1jumpButton = new KeyEditButton(game, 300, 470, 100, 40, "I", KeyEvent.VK_I);
		p1attackButton = new KeyEditButton(game, 300, 520, 100, 40, "J", KeyEvent.VK_J);
		p1specialButton = new KeyEditButton(game, 300, 570, 100, 40, "K", KeyEvent.VK_K);
		p1airdashButton = new KeyEditButton(game, 300, 620, 100, 40, "L", KeyEvent.VK_L);

		p2leftButton = new KeyEditButton(game, 890, 170, 100, 40, "<", KeyEvent.VK_LEFT);
		p2rightButton = new KeyEditButton(game, 890, 220, 100, 40, ">", KeyEvent.VK_RIGHT);
		p2upButton = new KeyEditButton(game, 890, 270, 100, 40, "^", KeyEvent.VK_UP);
		p2shieldButton = new KeyEditButton(game, 890, 320, 100, 40, "v", KeyEvent.VK_DOWN);
		p2jumpButton = new KeyEditButton(game, 890, 470, 100, 40, "5", KeyEvent.VK_NUMPAD5);
		p2attackButton = new KeyEditButton(game, 890, 520, 100, 40, "1", KeyEvent.VK_NUMPAD1);
		p2specialButton = new KeyEditButton(game, 890, 570, 100, 40, "2", KeyEvent.VK_NUMPAD2);
		p2airdashButton = new KeyEditButton(game, 890, 620, 100, 40, "3", KeyEvent.VK_NUMPAD3);
		
		pauseButton = new KeyEditButton(game, 590, 370, 100, 40, "P", KeyEvent.VK_P);

	}

	public void init() {
		
		rendered = false;


		backButton = new Button(game, 0, 0, 100, 50, Color.black, "<- VOLTAR", Assets.font15, null, false);
		p1saveButton = new Button(game, 265, 100, 75, 30, Color.black, "SALVAR", Assets.font10, null, false);
		p1loadButton = new Button(game, 360, 100, 75, 30, Color.black, "CARREGAR", Assets.font10, null, false);
		p2saveButton = new Button(game, 855, 100, 75, 30, Color.black, "SALVAR", Assets.font10, null, false);
		p2loadButton = new Button(game, 855+75+20, 100, 75, 30, Color.black, "CARREGAR", Assets.font10, null, false);
	}
	
	
	private void saveP1Controls(String name) {
		int[] controls = {
				 p1leftButton.getKeyCode(),
				 p1rightButton.getKeyCode(),
				 p1upButton.getKeyCode(),
				 p1shieldButton.getKeyCode(),
				 p1jumpButton.getKeyCode(),
				 p1attackButton.getKeyCode(),
				 p1specialButton.getKeyCode(),
				 p1airdashButton.getKeyCode()
		};
		String fileName = KeyFilesManager.nameToFilename(name);
		KeyFilesManager.saveControls(controls, fileName);
	}
	private void saveP2Controls(String name) {
		int[] controls = {
				 p2leftButton.getKeyCode(),
				 p2rightButton.getKeyCode(),
				 p2upButton.getKeyCode(),
				 p2shieldButton.getKeyCode(),
				 p2jumpButton.getKeyCode(),
				 p2attackButton.getKeyCode(),
				 p2specialButton.getKeyCode(),
				 p2airdashButton.getKeyCode()
		};
		String fileName = KeyFilesManager.nameToFilename(name);
		KeyFilesManager.saveControls(controls, fileName);
	}
	
	private void readP1Controls(String name) {
		String fileName = name;
		int controls[] = KeyFilesManager.readControls(fileName);
		p1leftButton.setKeyCode(controls[0]);
		p1rightButton.setKeyCode(controls[1]);
		p1upButton.setKeyCode(controls[2]);
		p1shieldButton.setKeyCode(controls[3]);
		p1jumpButton.setKeyCode(controls[4]);
		p1attackButton.setKeyCode(controls[5]);
		p1specialButton.setKeyCode(controls[6]);
		p1airdashButton.setKeyCode(controls[7]);
		rerender();
	}
	
	private void readP2Controls(String name) {
		String fileName = name;
		int controls[] = KeyFilesManager.readControls(fileName);
		p2leftButton.setKeyCode(controls[0]);
		p2rightButton.setKeyCode(controls[1]);
		p2upButton.setKeyCode(controls[2]);
		p2shieldButton.setKeyCode(controls[3]);
		p2jumpButton.setKeyCode(controls[4]);
		p2attackButton.setKeyCode(controls[5]);
		p2specialButton.setKeyCode(controls[6]);
		p2airdashButton.setKeyCode(controls[7]);
		rerender();
	}
	
	@Override
	public void tick() {
		
		if (backButton.buttonPressed()) {
			
			State.setState(game.getMenuState());
			((MenuState)(game.getMenuState())).init();
		}
		if (p1saveButton.buttonPressed()) {
			String filePath = FileChooserManager.chooseFile();
			if (filePath != null) {
				saveP1Controls(filePath);
			}
		}
		if (p1loadButton.buttonPressed()) {
			String filePath = FileChooserManager.chooseFile();
			if (filePath != null) {
				readP1Controls(filePath);
			}
		}
		if (p2saveButton.buttonPressed()) {
			String filePath = FileChooserManager.chooseFile();
			if (filePath != null) {
				saveP2Controls(filePath);
			}
		}
		if (p2loadButton.buttonPressed()) {
			String filePath = FileChooserManager.chooseFile();
			if (filePath != null) {
				readP2Controls(filePath);
			}
		}
		
		
		
		p1leftButton.tick();
		p1rightButton.tick();
		p1upButton.tick();
		p1shieldButton.tick();
		p1jumpButton.tick();
		p1attackButton.tick();
		p1specialButton.tick();
		p1airdashButton.tick();
		
		p2leftButton.tick();
		p2rightButton.tick();
		p2upButton.tick();
		p2shieldButton.tick();
		p2jumpButton.tick();
		p2attackButton.tick();
		p2specialButton.tick();
		p2airdashButton.tick();
		
		pauseButton.tick();
		
	}

	@Override
	public void render(Graphics g) {
		
		if (!rendered) { 
		
			rendered = true;
			g.clearRect(0, 0, 1280, 720);
			
			Text.drawString(g, "CUSTOMIZAR CONTROLES", 640, 50, true, Color.black, Assets.font30);
			Text.drawString(g, "JOGADOR 1", 145, 130, true, Color.black, Assets.font20);
			Text.drawString(g, "JOGADOR 2", 1135, 130, true, Color.black, Assets.font20);
			Text.drawString(g, "PAUSE", 640, 350, true, Color.black, Assets.font20);
			
			g.setColor(Color.gray);
			g.fillRoundRect(20, 150, 250, 230, 20, 20);
			g.fillRoundRect(20, 450, 250, 230, 20, 20);
			
			g.fillRoundRect(1010, 150, 250, 230, 20, 20);
			g.fillRoundRect(1010, 450, 250, 230, 20, 20);
			
			g.setColor(Color.white);
			g.fillRect(40, 170, 210, 40);
			g.fillRect(40, 220, 210, 40);
			g.fillRect(40, 270, 210, 40);
			g.fillRect(40, 320, 210, 40);
			
			g.fillRect(40, 470, 210, 40);
			g.fillRect(40, 520, 210, 40);
			g.fillRect(40, 570, 210, 40);
			g.fillRect(40, 620, 210, 40);
			
			
			g.fillRect(1030, 170, 210, 40);
			g.fillRect(1030, 220, 210, 40);
			g.fillRect(1030, 270, 210, 40);
			g.fillRect(1030, 320, 210, 40);
			
			g.fillRect(1030, 470, 210, 40);
			g.fillRect(1030, 520, 210, 40);
			g.fillRect(1030, 570, 210, 40);
			g.fillRect(1030, 620, 210, 40);
			
			
			Text.drawString(g, "ESQUERDA", 145, 190, true, Color.black, Assets.font13);
			Text.drawString(g, "DIREITA", 145, 240, true, Color.black, Assets.font13);
			Text.drawString(g, "CIMA", 145, 290, true, Color.black, Assets.font13);
			Text.drawString(g, "ESCUDO", 145, 340, true, Color.black, Assets.font13);
			Text.drawString(g, "PULO", 145, 490, true, Color.black, Assets.font13);
			Text.drawString(g, "ATAQUE", 145, 540, true, Color.black, Assets.font13);
			Text.drawString(g, "ESPECIAL", 145, 590, true, Color.black, Assets.font13);
			Text.drawString(g, "AIRDASH", 145, 640, true, Color.black, Assets.font13);
			
			Text.drawString(g, "ESQUERDA", 1135, 190, true, Color.black, Assets.font13);
			Text.drawString(g, "DIREITA", 1135, 240, true, Color.black, Assets.font13);
			Text.drawString(g, "CIMA", 1135, 290, true, Color.black, Assets.font13);
			Text.drawString(g, "ESCUDO", 1135, 340, true, Color.black, Assets.font13);
			Text.drawString(g, "PULO", 1135, 490, true, Color.black, Assets.font13);
			Text.drawString(g, "ATAQUE", 1135, 540, true, Color.black, Assets.font13);
			Text.drawString(g, "ESPECIAL", 1135, 590, true, Color.black, Assets.font13);
			Text.drawString(g, "AIRDASH", 1135, 640, true, Color.black, Assets.font13);
			

			
			backButton.drawButton(g);
			p1saveButton.drawButton(g);
			p1loadButton.drawButton(g);
			p2saveButton.drawButton(g);
			p2loadButton.drawButton(g);
			
			p1leftButton.drawButton(g);
			p1rightButton.drawButton(g);
			p1upButton.drawButton(g);
			p1shieldButton.drawButton(g);
			p1jumpButton.drawButton(g);
			p1attackButton.drawButton(g);
			p1specialButton.drawButton(g);
			p1airdashButton.drawButton(g);
			
			p2leftButton.drawButton(g);
			p2rightButton.drawButton(g);
			p2upButton.drawButton(g);
			p2shieldButton.drawButton(g);
			p2jumpButton.drawButton(g);
			p2attackButton.drawButton(g);
			p2specialButton.drawButton(g);
			p2airdashButton.drawButton(g);
			
			pauseButton.drawButton(g);
	
		}
	}
	
	
	public static int getp1Up() {
		
		return p1upButton.getKeyCode();
	}
	
	public static int getp1Left() {
		
		return p1leftButton.getKeyCode();
	}
	
	public static int getp1Shield() {
		
		return p1shieldButton.getKeyCode();
	}
	
	public static int getp1Right() {
		
		return p1rightButton.getKeyCode();
	}
	
	public static int getp1Jump() {
		
		return p1jumpButton.getKeyCode();
	}
	
	public static int getp1Attack() {
		
		return p1attackButton.getKeyCode();
	}
	
	
	public static int getp1Special() {
		
		return p1specialButton.getKeyCode();
	}
	
	public static int getp1Airdash() {
		
		return p1airdashButton.getKeyCode();
	}
	
	public static int getp2Up() {
		
		return p2upButton.getKeyCode();
	}
	
	public static int getp2Left() {
		
		return p2leftButton.getKeyCode();
	}
	
	public static int getp2Shield() {
		
		return p2shieldButton.getKeyCode();
	}
	
	public static int getp2Right() {
		
		return p2rightButton.getKeyCode();
	}
	
	public static int getp2Jump() {
		
		return p2jumpButton.getKeyCode();
	}
	
	public static int getp2Attack() {
		
		return p2attackButton.getKeyCode();
	}
	
	
	public static int getp2Special() {
		
		return p2specialButton.getKeyCode();
	}
	
	public static int getp2Airdash() {
		
		return p2airdashButton.getKeyCode();
	}
	
	public static int getPause() {
		return pauseButton.getKeyCode();
	}

	public static void rerender() {
		rendered = false;
	}

}
