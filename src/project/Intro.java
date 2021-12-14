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

public class Intro implements Runnable,KeyListener{
	private int width,height;
	private JFrame frame;
	private Canvas intro;
	private JPanel screenIntro=null;
	
	private Thread thread;
	private boolean running=false;
	
	private BufferStrategy bs;
	private Graphics g;
	private BufferedImage screen,PressAnyKey,snow,rePressAnyKey;
	private BufferedImage tatsuAdventure;
	private BufferedImage Olaf,OlafHandUp,OlafHandMid,OlafHandDown;
	private int x=0,xx=0,y=0,yy=0,z=0,zz=0,mem=0;
	private int o=0,oo=0;
	
	private boolean status = false;
	public Intro(String title,int width,int height) {
		this.width=width;
		this.height=height;
		
//		frame=new JFrame(title);
//		frame.setSize(width,height);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setResizable(false);
//		frame.setLocale(null);
//		frame.setVisible(true);
		
		intro=new Canvas();
		
		intro.setPreferredSize(new Dimension(width,height));
		intro.setMaximumSize(new Dimension(width,height));
		intro.setMinimumSize(new Dimension(width,height));
		intro.setFocusable(false);
		
		screenIntro = new JPanel();
		screenIntro.add(intro);
		screenIntro.setVisible(true);
		//frame.add(screenIntro);
		//frame.pack();
	}
	
	private void init(){
		
		try {
			screen = ImageIO.read(new File("res/textures/snowScreenNew2.png"));
			PressAnyKey = ImageIO.read(new File("res/textures/pressKey.png"));
			snow = ImageIO.read(new File("res/textures/snow2.png"));
			
			rePressAnyKey = ImageIO.read(new File("res/textures/pressKeyDark.png"));

			tatsuAdventure = ImageIO.read(new File("res/textures/tatsuAdventure.png"));
			
			Olaf = ImageIO.read(new File("res/textures/Olaf1.png"));
			OlafHandUp = ImageIO.read(new File("res/textures/Olaf1hand_up.png"));
			OlafHandMid = ImageIO.read(new File("res/textures/Olaf1hand_mid.png"));
			OlafHandDown = ImageIO.read(new File("res/textures/Olaf1hand_down.png"));
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
		
		o+=1;
		if(o == 180) {
			o=0;
		}		
		
	}
	private void render() {
		bs = intro.getBufferStrategy();
		if(bs==null) {
			intro.createBufferStrategy(3);
			return;
		}
		g=bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		
		g.drawImage(screen,0,0,width,height,null);
		g.drawImage(tatsuAdventure,0,0,null);
		
		g.drawImage(snow,-1100,xx-1100,null);
	
		if(o <= 45 && o >= 0)
			g.drawImage(OlafHandUp,40,500,250,480,null); // x = 40 to left
		if(o <= 90 && o >= 45)
			g.drawImage(OlafHandMid,40,500,250,480,null); // x = 960 to right
		if(o <= 135 && o >= 90)
			g.drawImage(OlafHandDown,40,500,250,480,null);
		if(o <= 180 && o >= 135)
			g.drawImage(OlafHandMid,40,500,250,480,null);
		
		
		g.drawImage(snow,0,xx-1100,null);
		g.drawImage(snow,-300,yy-2200,null);
		g.drawImage(snow,100,zz-2400,null);
		
		
		if(x%300 <= 150)
			g.drawImage(rePressAnyKey,435,560,450,350,null);
		else
			g.drawImage(PressAnyKey,410,540,490,390,null);
		
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
//		try {
//			thread.join();
//		}catch(InterruptedException e) {
//			e.printStackTrace();
//		};
	}

	public JPanel getJPanel() {
		System.out.println(1);
		return screenIntro;
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
		// TODO Auto-generated method stub
		
	}
	public boolean nextState() {
		return status;
	}
}
