package project;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ending implements Runnable,KeyListener{
	private int width,height;
	private JFrame frame;
	private Canvas ending;
	private JPanel screenEnding=null;
	
	private Thread thread;
	private boolean running=false;
	
	private BufferStrategy bs;
	private Graphics g;
	private BufferedImage screen,snow;
	private BufferedImage seeYouLeft,seeYouMid,seeYouRight;
	private BufferedImage A;
	private int x=0,xx=0,y=0,yy=0,z=0,zz=0,mem=0;
	private int s=0,ss=0,c=0,cc=0;
	
	private boolean status = false;
	public Ending(String title,int width,int height) {
		this.width=width;
		this.height=height;
	
		ending=new Canvas();
		ending.setPreferredSize(new Dimension(width,height));
		ending.setMaximumSize(new Dimension(width,height));
		ending.setMinimumSize(new Dimension(width,height));
		ending.setFocusable(false);
		
		screenEnding = new JPanel();
		screenEnding.add(ending);
		screenEnding.setVisible(true);

	}
	
	private void init(){
		
		try {
			screen = ImageIO.read(new File("res/textures/ending/Ending.png"));
			snow = ImageIO.read(new File("res/textures/ending/snow2.png"));
			
			seeYouLeft = ImageIO.read(new File("res/textures/ending/seeYouLeft.png"));
			seeYouMid = ImageIO.read(new File("res/textures/ending/seeYouMid.png"));
			seeYouRight = ImageIO.read(new File("res/textures/ending/seeYouRight.png"));
			
			A = ImageIO.read(new File("res/textures/ending/A.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		
		init();
		while(running) {
			
			tick();
			render();
		}
		stop();
		
	}
	private void tick() {
		x += 1;
		if(x%4 == 0)
			xx+=1;
		if(y==0 && x==2500)
			mem=1;
		if(x == 5500) {
			xx=0;x=0;
		}
		
		if(mem == 1) {
			y += 1;
			if(y%4 == 0)
				yy+=1;
			if(y == 7000) {
				yy=0;y=0;mem=0;
			}
		}
		z+=1;
		if(z%6==0)
			zz+=1;
		if(z == 30000) {
			zz=0;z=0;
		}
		
		s += 1;
		if(s%4 == 0)
			ss+=1;
		
		c+=1;
		if(c == 320) {
			c=0;
		}		
		
	}
	private void render() {
		bs = ending.getBufferStrategy();
		if(bs==null) {
			ending.createBufferStrategy(3);
			return;
		}
		g=bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		
		g.drawImage(screen,0,0,width,height,null);
		g.drawImage(snow,-1100,xx-1100,null);

			if(s < 900)
				g.drawImage(seeYouMid,650-ss*2,550-ss*2,s,s, null);
			else {
				//g.drawImage(seeYouMid,400-222,200-222,900,900, null);
				// 900/4 = 222*2
				if(c <= 80 && c >= 0)
					g.drawImage(seeYouLeft,650-444,550-444,900,900,null); // x = 40 to left
				if(c <= 160 && c >= 80)
					g.drawImage(seeYouMid,650-444,550-444,900,900, null); // x = 960 to right
				if(c <= 240 && c >= 160)
					g.drawImage(seeYouRight,650-444,550-444,900,900,null);
				if(c <= 320 && c >= 240)
					g.drawImage(seeYouMid,650-444,550-444,900,900, null);
			}
		

		g.drawImage(snow,0,xx-1100,null);
		g.drawImage(snow,-300,yy-2200,null);
		g.drawImage(snow,100,zz-2400,null);
		
		g.dispose();
		bs.show();
		
	}
	
	public synchronized void start() {System.out.println(20);
		if(running)
			return;
		running=true;
		thread=new Thread(this);
		thread.start();
		
	}
	public synchronized void stop() {
		if(!running)
			return;
		running=false;
	}

	public JPanel getJPanel() {
		System.out.println(1);
		return screenEnding;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		stop();
		status=true;
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	public boolean nextState() {
		return status;
	}
}