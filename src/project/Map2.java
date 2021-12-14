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
import javax.swing.*;

import objectEvent.ObjectEvent;

public class Map2 extends JPanel implements Runnable,KeyListener{

	private Thread thread;

	private Character character;
	
	private int xem =0;
	private int yem =0;
	private int start=0;
	private int x=0;
	private int y=0;
	private BufferedImage im=null,hole=null,detail=null;
	private ArrayList<Object> object;
	private boolean setLoop=false;
	private ArrayList<ObjectEvent> allObjectDead;
	private boolean dead =false;
	private int i=0;
	
	public Map2(Character character) {
		this.character=  character;
		object = new ArrayList<Object>();
		allObjectDead = new ArrayList<ObjectEvent>();
		
		try {
			 im = ImageIO.read(new File("Background/Map2.png"));
		} catch (IOException e) {
			System.out.println("NOTFOUND");
		}
		object.add(new Object(0,339,105,50)); // ob1
		object.add(new Object(90,350,240,40)); // ob2
		object.add(new Object(308,385,520,55)); // ob3
		object.add(new Object(820,320,85,100)); // ob4
		object.add(new Object(900,338,250,30)); // ob5
		object.add(new Object(1140,360,150,62)); // ob6
		object.add(new Object(0,742,225,62)); // ob7
		object.add(new Object(176,742,50,80)); // ob8
		object.add(new Object(207,815,855,20)); // ob9
		object.add(new Object(1055,670,50,170)); // ob10
		object.add(new Object(1055,670,235,50)); // ob11
		object.add(new Object(915,670,50,170)); // ob10
		object.add(new Object(915,670,369,50)); // ob11
		
		allObjectDead.add(new ObjectEvent(500,500,100,100));
		character.setAllObject(object);
	}



	public void run() {
	
		while(true) {
		System.out.println("state 2 :"+i++);
			checkDead();
			repaint();
			try {
				thread.sleep(20);
			} catch (InterruptedException e) {}
			if(setLoop)
				break;
			
		}
	}
	
	
	public void paint(Graphics g) {	
		g.drawImage(im, x, y,1280,960,null);
		g.drawImage(hole, 0, 0, 1280,960,null);
		g.setColor(Color.black);
		//g.drawRect(xem,yem,100,100);
		character.draw(g);
		g.drawImage(detail, 0, 0, 1260, 960,null);
//		for(int i=0;i<object.size();i++) {
//			g.drawRect(object.get(i).getX(), object.get(i).getY(),object.get(i).getWidth() , object.get(i).getHeight());
//		}
		g.setColor(Color.red);
//		for(int i=0;i<allObjectDead.size();i++) {
//			g.drawRect(allObjectDead.get(i).getX(), allObjectDead.get(i).getY(), allObjectDead.get(i).getWidth(), allObjectDead.get(i).getHeight());
//		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Pressed");
		char button = e.getKeyChar();
		System.out.println(button);
		character.walk(button);
	
	
	}



	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}
	public void keyTyped(KeyEvent e) {
		
		
	}
	
	public boolean nextMap() {
		if(character.getX()>1250)
			return true;
		return false;
	}
	public boolean previousMap() {
		if(character.getX()<0) {
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
					if(hole==null) {
						this.i=0;
						try {
							hole = ImageIO.read(new File("map2/dead.png"));
							detail = ImageIO.read(new File("map5/water.png"));
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
	public BufferedImage getHole() {
		return hole;
	}



	public void setHole(BufferedImage hole) {
		this.hole = hole;
	}



	public boolean isDead() {
		if(i>30)
			return dead;
		return false;
	}



	public void setDead(boolean dead) {
		this.dead = dead;
	}



	public boolean dead() {
		return dead;
	}
}
