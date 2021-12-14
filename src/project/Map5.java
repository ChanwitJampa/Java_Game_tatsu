package project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import objectEvent.NPC;
import objectEvent.ObjectEvent;

public class Map5 extends JPanel implements Runnable,KeyListener{

	private Thread thread;

	private Character character;
	

	private int xem =0;
	private int yem =0;
	private int start=0;
	private int x=0;
	private int y=0;
	private BufferedImage im=null,hole=null,item=null,bear=null,water=null;
	private ArrayList<Object> object;
	private boolean setLoop=false;
	private ArrayList<ObjectEvent> allObjectDead;
	private boolean dead =false;
	private int i=0;
	private NPC npc;
	private boolean talk=false;
	private ArrayList<BufferedImage> allTalk;
	
	private boolean isItem=true;
	
	
	
	public Map5(Character character) {
		object = new ArrayList<Object>();
		this.character = character;
		allTalk= new ArrayList<BufferedImage>();
		allObjectDead = new ArrayList<ObjectEvent>();
		
		try {
			allTalk.add(ImageIO.read(new File("map5/getTouch.png")));	
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		npc= new NPC(allTalk,300,800,100,200);
		
		try {
			 im = ImageIO.read(new File("Background/Map4.png"));
			 item = ImageIO.read(new File("map5/touch.png"));
			 bear=ImageIO.read(new File("map5/bear.png"));
		} catch (IOException e) {
			System.out.println("NOTFOUND");
		}
		object.add(new Object(0,339,105,50)); // ob1
		object.add(new Object(90,350,240,40)); // ob2
		object.add(new Object(308,385,520,55)); // ob3
		object.add(new Object(820,320,85,100)); // ob4
		object.add(new Object(900,338,79,30)); // ob5
		object.add(new Object(1052,338,95,30)); // ob5-1
		object.add(new Object(930,260,50,100)); // ob5-2
		object.add(new Object(1052,260,50,100)); // ob5-3
		object.add(new Object(930,240,100,50)); // ob5-4
		object.add(new Object(1140,360,150,62)); // ob6
		object.add(new Object(0,742,225,62)); // ob7
		object.add(new Object(176,742,50,80)); // ob8
		object.add(new Object(207,815,855,50)); // ob9
		object.add(new Object(915,670,50,170)); // ob10
		object.add(new Object(915,670,369,50)); // ob11
		object.add(new Object(0,380,60,230)); // ob12
		object.add(new Object(-40,600,50,200)); // ob13
		
		allObjectDead.add(new ObjectEvent(500,500,100,100));
		allObjectDead.add(new ObjectEvent(930,380,200,100));
		character.setAllObject(object);
	}



	public void run() {
		while(true) {
			System.out.println("Map 5 :"+i++);
			repaint();
			try {
				checkDead();
				thread.sleep(20);
			} catch (InterruptedException e) {}
			if(setLoop)
				break;
		}
		
	}
	
	
	public void paint(Graphics g) {	
		g.drawImage(im, x, y,1280,960,null);
		if(isItem)
			g.drawImage(item,0,0,1280,960,null);
		g.drawImage(hole, 0, 0, 1280,960,null);
		g.setColor(Color.black);
		//g.drawRect(70, 650, 120, 120);
		if(isItem) {
			g.drawImage(bear, 0, 0, 1260, 960, null);
		}
		g.drawImage(water, 0,0, 1280, 960, null);
		
		
		character.draw(g);
		
		
		
		
		g.setColor(Color.red);
//		for(int i=0;i<allObjectDead.size();i++) {
//			g.drawRect(allObjectDead.get(i).getX(), allObjectDead.get(i).getY(), allObjectDead.get(i).getWidth(), allObjectDead.get(i).getHeight());
//		}
		if(talkNpc()) {
			g.drawImage(npc.getFrame(), 0, 0,1280,960, null);
		}
//		for(int i=0;i<object.size();i++) {
//			g.drawRect(object.get(i).getX(), object.get(i).getY(),object.get(i).getWidth() , object.get(i).getHeight());
//		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Pressed");
		char button = e.getKeyChar();
		System.out.println(button);
		
	
		if(talkNpc()&&npc.getIndex()<npc.getSizeImage()) {
			character.setWalk(false);
		}
		
		character.walk(button);
		
		if(talkNpc()&&button!='d'&&button !='s'&&button!='a'&&button!='w'&&npc.getIndex()<npc.getSizeImage()) {
			System.out.println(npc.getIndex()+" "+npc.getSizeImage());
			
			npc.update();
			
			if(npc.getIndex() == npc.getSizeImage()) {
				character.setWalk(true);
			
			}
		}
	
	}



	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}
	public void keyTyped(KeyEvent e) {
		
		
	}
	
	public boolean nextMap() {
		if(character.getY()<200)
			return true;
		return false;
	}
	public boolean previousMap() {
		if(character.getX()>1200) {
			return true;
		}
		return false;
	}


	public Thread getThread() {
		return thread;
	}



	public void setThread(Thread thread) {
		this.thread = thread;
	}
	public void setLoop(boolean set) {
		setLoop = set;
	}
	public Character getCharacter() {
		return character;
	}
	
	public void setCharacter(Character character) {
		this.character = character;
	}
	public ArrayList<Object> getObject() {
		return object;
	}
	
	public void checkDead() {
		for(int i=0;i<allObjectDead.size();i++) {
			if(character.getXFoot() > allObjectDead.get(i).getX() && character.getXFoot() <allObjectDead.get(i).getX()+allObjectDead.get(i).getWidth()&&
				character.getYFoot() > allObjectDead.get(i).getY() && character.getYFoot() <allObjectDead.get(i).getY()+allObjectDead.get(i).getHeight()) {
					character.setWalk(false);
					if(i==0&&hole==null) {	
						this.i=0;
						try {
							hole = ImageIO.read(new File("map2/dead.png"));
							water = ImageIO.read(new File("map5/water.png"));
						
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					else if(i==1&&hole==null) {
						this.i=0;
							try {
								hole=ImageIO.read(new File("map5/beartalk.png"));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
					}
					if(character.isWalk())
						this.i=0;
					if(this.i>30) {
						dead=true;
					}
				
			}
		}
	}



	public boolean Dead() {
		if(this.i>30)
			return dead;
		return false;
	}



	public void setDead(boolean dead) {
		this.dead = dead;
	}
	public boolean talkNpc() {
		if(character.getXFoot()>70&&character.getXFoot()<190&&character.getYFoot()>650&character.getYFoot()<770) {
			if(isItem) {
				allObjectDead.remove(1);
			}
			isItem=false;
			return true;
		}
		return false;
	}
	public boolean getTalk() {
		return talk;
	}
	public void setTalk() {
		if(npc.getIndex()>=npc.getSizeImage()) {
			talk=true;
		}
	}
	
}
