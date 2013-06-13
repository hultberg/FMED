/**
 * Følg med eller drikk! by Edvin Hultberg is licensed under a Creative Commons Attribution 3.0 Norway License.
 * Read the license here:
 * http://creativecommons.org/licenses/by/3.0/no/
 * 
 * Enjoy, its in the public domain with the exception of the attribution part.
 * 
 * @author Hultberg
 * @since 0.1
 * @version 1.3.2
 */

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.Random;

public class c extends Applet implements Runnable, KeyListener {

	private static final long serialVersionUID = -8483261713399778287L;
	public boolean running = true;
	public boolean gameover = false;
	public boolean stop = false;	
	private Thread t;
	
	private int score;
	private int tmp = 0;
	
	private d pl1;
	private d com1;
	private String lookingInfo;
	private String playerStats;
	private int multi;
	
	private String tecStat = "LÆRERN";	
	private String msg = "Trykk 'SPACE'/mellomrom knappen for å samle poeng, ikke trykk inn mellomrom når læreren ser på deg!";
	
	private boolean autopass;
	
	public Image offscreen;
	public Graphics d;
	private int waitTime;
	public e diffi;
	
	public void init(){
		setSize(700, 500);
		addKeyListener(this);
		requestFocus();
		
		t = new Thread(this);
		t.start();		
		
		pl1 = new d(b.PLAYER);
		pl1.setAction(a.NORMAL);
			
		com1 = new d(b.VILLIAN);
		com1.setAction(a.NORMAL);
			
		offscreen = createImage(750, 500);
		d = offscreen.getGraphics();
			
		score = 0;
		multi = 0;
		lookingInfo = "Underviser";
		playerStats = "Følger med";
	}
	
	@Override
	public void run() {
		
		while(running){
			if(diffi == null){
				getGraphics().drawString("Laget av Edvin Hultberg 2013. v1.3.3", 100, 230);
				getGraphics().drawString("F1 = Lett, " + System.getProperty("line.separator")
						+ "F2 = Middels, " + System.getProperty("line.separator")
						+ "F3 = Vansklig, " + System.getProperty("line.separator")
						+ "F4 = Ekspert, " + System.getProperty("line.separator")
						+ "F5 = Mareritt.", 100, 250);
			} else {
				if(!gameover){
					repaint();
				} else {
					gameover();
				}
			}
			
			long time = System.currentTimeMillis();
			time = (1000 / 40) - (System.currentTimeMillis() - time);
			
			if(time > 0){
				try {
					Thread.sleep(time);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	public void update(Graphics g) {
		if(diffi != null){
			Random rand = new Random();
			int randed = rand.nextInt(2000);
			if(com1.getAction() == a.LOOKING){
				if(randed < diffi.getRandStop()){
					lookingInfo = "Underviser";
					tmp = 0;
					com1.setAction(a.NORMAL);
					autopass = false;
				}
			} else if(com1.getAction() == a.NORMAL){
				if(autopass){
					tmp = tmp + 1;
					if(tmp >= waitTime && tmp != 0){
						lookingInfo = "Ser på deg";
						com1.setAction(a.LOOKING);
						autopass = false;
						waitTime = 0;
						
						checkIfGameOver();
					} else {
						lookingInfo = "Snur seg | " + tmp + "/" + waitTime;
						autopass = true;
					}
				} else {
					if(randed >= diffi.getFromRandom() && randed <= diffi.getToRandom()){
						waitTime = getRandomNumber(diffi.getFromWait(), diffi.getToWait());
						tmp = tmp + 1;
						autopass = true;
						lookingInfo = "Snur seg | " + tmp + "/" + waitTime;
					}
				}
			}
			if(pl1.getAction() == a.DRINKING){
				if(score > diffi.getMulti1() && score < diffi.getMulti2()){
					multi = 2;
				} else if(score > diffi.getMulti2() && score < diffi.getMulti3()){
					multi = 4;
				} else if(score > diffi.getMulti3()){
					multi = 8;
				}
				if(multi != 0)
					score = score + multi;
				else
					score = score + 1;
			}
			
			paint(g);
		}
	}
	
	private void checkIfGameOver() {
		if(pl1.getAction() == a.DRINKING && com1.getAction() == a.LOOKING){
			gameover();
		}
	}

	private void gameover() {
		d.clearRect(0, 0, 700, 500);		
		Font font = new Font("Dialog", Font.BOLD, 80);
		Font font2 = new Font("Dialog", Font.PLAIN, 20);
		d.setFont(font);
		d.drawString("Game over!", 85, 250);
		
		d.setFont(font2);
		d.drawString("Score: " + score + " på " + diffi.getName(), 85, 290);
		d.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		d.setFont(new Font("Dialog", Font.PLAIN, 12));
		d.drawString("Trykk 'ESC'/escape for å starte på nytt!", 85, 330);
		this.getGraphics().drawImage(offscreen, 0, 0, this);
		gameover = true;
	}

	@Override
	public void paint(Graphics g){
		if(diffi != null){
			Font font1 = new Font("Dialog", Font.PLAIN, 40);
			Font font2 = new Font("Times New Roman", Font.BOLD, 75);
			Font font3 = new Font("Arial", Font.BOLD, 45);
			Font font4 = new Font("Dialog", Font.PLAIN, 12);
			
			d.clearRect(0, 0, 700, 500);
			d.drawString("v1.3.3", 5, 15);		
			d.drawString("Score: " + score + (multi != 0 ? "x" + multi : "") + " | " + diffi.getName(), 5, 27);
			
			AttributedString as1 = new AttributedString(tecStat);
			as1.addAttribute(TextAttribute.FONT, font1);
			
			AttributedString as3 = new AttributedString("DU");
			as3.addAttribute(TextAttribute.FONT, font1);
			
			AttributedString as = new AttributedString(lookingInfo);
			as.addAttribute(TextAttribute.FONT, font2);
			if(lookingInfo.equalsIgnoreCase("Underviser")){
				as.addAttribute(TextAttribute.FOREGROUND, Color.green, 0, 10);
			} else if(lookingInfo.contains("Snur seg | ")){
				as.addAttribute(TextAttribute.FOREGROUND, Color.cyan, 0, lookingInfo.length());
			} else if(lookingInfo.equalsIgnoreCase("Ser på deg")){
				as.addAttribute(TextAttribute.FOREGROUND, Color.red, 0, 10);
			}	
			
			AttributedString as2 = new AttributedString(playerStats);
			as2.addAttribute(TextAttribute.FONT, font3);
			if(playerStats.equalsIgnoreCase("Følger med")){
				as2.addAttribute(TextAttribute.FOREGROUND, Color.darkGray, 0, 10);
			} else if(playerStats.equalsIgnoreCase("Drikker")){
				as2.addAttribute(TextAttribute.FOREGROUND, Color.cyan, 0, 7);
			}
			
			d.drawString(as1.getIterator(), getCenterValueToStringX(d, font1, tecStat), 100);
			d.drawString(as.getIterator(), getCenterValueToStringX(d, font2, lookingInfo), 160);
			d.drawString(as3.getIterator(), getCenterValueToStringX(d, font1, "DU"), 350);
			d.drawString(as2.getIterator(), getCenterValueToStringX(d, font3, playerStats), 380);
			d.drawString(msg, getCenterValueToStringX(g, font4, msg), 490);
			g.drawImage(offscreen, 0, 0, this);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		/* Set */
		int key = arg0.getKeyCode();
		if(key == KeyEvent.VK_SPACE){
			if(!gameover){
				pl1.setAction(a.DRINKING);
				playerStats = "Drikker";
				checkIfGameOver();
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		/* Reset */
		int key = arg0.getKeyCode();
		if(key == KeyEvent.VK_SPACE){
			pl1.setAction(a.NORMAL);
			playerStats = "Følger med";
		} else if(key == KeyEvent.VK_ESCAPE){
			if(gameover){
				gameover = false;
				score = 0;
				tmp = 0;
				waitTime = 0;
				multi = 0;
				playerStats = "Følger med";
				lookingInfo = "Underviser";
				com1.setAction(a.NORMAL);
				pl1.setAction(a.NORMAL);
			}
		} else if(key == KeyEvent.VK_F1){
			startGame(e.EASY);
		} else if(key == KeyEvent.VK_F2){
			startGame(e.MIDDLE);
		} else if(key == KeyEvent.VK_F3){
			startGame(e.HARD);
		} else if(key == KeyEvent.VK_F4){
			startGame(e.EXPERT);
		} else if(key == KeyEvent.VK_F5){
			startGame(e.INSANE);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) { }
	
	private int getRandomNumber(int min, int max){  
	    return (int) (min + Math.random() * (max - min));  
	} 
	
	public int getCenterValueToStringX(Graphics g, Font f, String s){
		FontMetrics fm   = g.getFontMetrics(f);
		java.awt.geom.Rectangle2D rect = fm.getStringBounds(s, g);
		
		int textWidth  = (int)(rect.getWidth());
		int panelWidth = this.getWidth();

		return (panelWidth  - textWidth)  / 2;
	}
	
	public int getCenterValueToStringY(Graphics g, Font f, String s){
		FontMetrics fm   = g.getFontMetrics(f);
		java.awt.geom.Rectangle2D rect = fm.getStringBounds(s, g);
		
		int textHeight  = (int)(rect.getHeight());
		int panelHeight = this.getHeight();

		return (panelHeight  - textHeight)  / 2;
	}
	
	private void startGame(e diff){
		diffi = diff;
		gameover = false;
		removeAll();
		running = true;
		repaint();
	}

}
