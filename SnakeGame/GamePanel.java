import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener{
	
	static final int W = 600;
	static final int H = 600;
	static final int SIZE = 25;
	static final int UNITS = (W * H)/SIZE;
	static final int DELAY = 75;
	final int x[] = new int[UNITS];
	final int y[] = new int[UNITS];
	int bodyParts = 6;
	int applesEaten, appleX, appleY = 0;
	char direction = 'R';
	boolean running = false;
	Timer timer;
	Random random;
	
	GamePanel(){
		random = new Random();
		this.setPreferredSize(new Dimension(W,H));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	
	public void startGame() {
		newApple();
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		
		if(running) {
			for(int i=0;i<H/SIZE;i++) {
				g.drawLine(i*SIZE, 0, i*SIZE,H);
				g.drawLine(0, i*SIZE, W, i*SIZE);
			}
			g.setColor(Color.red);
			g.fillOval(appleX, appleY, SIZE, SIZE);
		
			for(int i = 0; i<bodyParts;i++) {
				if(i == 0) {
					g.setColor(Color.green);
					g.fillRect(x[i], y[i], SIZE, SIZE);
				}else {
					g.setColor(new Color(45,180,0));
					g.fillRect(x[i], y[i], SIZE, SIZE);
				}
			}
			g.setColor(Color.red);
			g.setFont(new Font("Ink Free",Font.BOLD,25));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: "+applesEaten, (W - metrics.stringWidth("Score: "+applesEaten))/2,g.getFont().getSize());
		}else {
			if(applesEaten == 570) 
				winScreen(g);
			else
			gameOver(g);
		}
	}
	
	public void newApple() {
		if(applesEaten == 570) {
			timer.stop();
			running = false;
		}
		appleX = random.nextInt((int)(W/SIZE))*SIZE;
		appleY = random.nextInt((int)(H/SIZE))*SIZE;
	}
	
	public void move() {
		for(int i=bodyParts;i>0;i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		switch(direction) {
		case 'U':
			y[0] = y[0] - SIZE;
			break;
		case 'D':
			y[0] = y[0] + SIZE;
			break;
		case 'L':
			x[0] = x[0] - SIZE;
			break;
		case 'R':
			x[0] = x[0] + SIZE;
			break;
		}
		
	}
	public void checkApple() {
		if(applesEaten == 570) {
			timer.stop();
			running = false;
		}
		
		if((x[0] == appleX) && (y[0] == appleY)){
			bodyParts++;
			applesEaten++;
			newApple();
		}
		
	}
	public void checkCol() {
		//check if head collides with body
		for(int i=bodyParts;i>0;i--) {
			if((x[0]==x[i]) && (y[0]==y[i])) {
				running = false;
			}
		}
		//check if head collides with border
		if(x[0]<0) 
			running = false;
		
		if(x[0]>W) 
			running = false;
		
		if(y[0]<0) 
			running  = false;
		
		if(y[0]>H) 
			running = false;
		
		if(running == false)
			timer.stop();
	}
	public void gameOver(Graphics g) {
		
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free",Font.BOLD,25));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score: "+applesEaten, (W - metrics1.stringWidth("Score: "+applesEaten))/2,g.getFont().getSize());	
		
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free",Font.BOLD,75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (W - metrics2.stringWidth("Game Over"))/2,H/2);
	}
	public void winScreen(Graphics g) {
		g.setColor(Color.GREEN);
		g.setFont(new Font("Ink Free",Font.BOLD,75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("You Won!", (W - metrics2.stringWidth("You Won!"))/2,H/2);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(running) {
			move();
			checkApple();
			checkCol();
		}
		repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R') 
					direction = 'L';
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') 
					direction = 'R';
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U') 
					direction = 'D';
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D')
					direction = 'U';
				break;
			}
		}
	}
}
