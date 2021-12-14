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

public class Dungeon0 extends JPanel implements Runnable,KeyListener{

	private Thread thread;

	private Character character;
	
	private int xem =0;
	private int yem =0;
	private int start=0;
	private int x=0;
	private int y=0;
	private BufferedImage im=null;
	private ArrayList<Object> object;
	private boolean setLoop=false;
	
	public Dungeon0(Character character) {
		object = new ArrayList<Object>();
		this.character = character;
		

		
		try {
			 im = ImageIO.read(new File("Background/mapDungeon0.png"));
		} catch (IOException e) {
			System.out.println("NOTFOUND");
		}
		object.add(new Object(320,300,55,420)); // ob1
		object.add(new Object(1030,350,55,420)); 
		
		object.add(new Object(320,300,500,65)); // ob2
		object.add(new Object(320,750,500,65));
		object.add(new Object(750,300,240,142)); // ob3
		object.add(new Object(905,518,90,200)); // ob4
		object.add(new Object(679,700,250,50)); // ob5
		object.add(new Object(352,700,250,50)); // ob6
		
		character.setAllObject(object);
	}



	public void run() {
		while(true) {
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
		g.setColor(Color.black);
		//g.drawRect(xem,yem,100,100);
		character.draw(g);
		
//		g.setColor(Color.white);
//		for(int i=0;i<object.size();i++) {
//			g.drawRect(object.get(i).getX(), object.get(i).getY(),object.get(i).getWidth() , object.get(i).getHeight());
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
		if(character.getX()>905)
			return true;
		return false;
	}
	public boolean previousMap() {
		if(character.getYFoot()>710) {
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
}
