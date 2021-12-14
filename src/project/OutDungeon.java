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

import objectEvent.ObjectPicture;

public class OutDungeon extends JPanel implements Runnable,KeyListener{

	private Thread thread;

	private Character character;
	
	private int xem =0;
	private int yem =0;
	private int start=0;
	private int x=0;
	private int y=0;
	private BufferedImage im=null,event=null;
	private ArrayList<Object> object;
	private boolean setLoop=false;
	private ArrayList<ObjectPicture> allObjectPicture;
	public OutDungeon(Character character) {
		object = new ArrayList<Object>();
		this.character = character;
		allObjectPicture = new ArrayList<ObjectPicture>();
		
		try {
			 im = ImageIO.read(new File("Background/mapOutDungeon.png"));
		} catch (IOException e) {
			System.out.println("NOTFOUND");
		}
		object.add(new Object(250,200,132,316)); // ob1
		object.add(new Object(280,200,132,290)); // ob2
		object.add(new Object(300,200,525,230)); // ob3
		object.add(new Object(300,200,750,160)); // ob4
		object.add(new Object(300,200,905,152)); // ob5
		object.add(new Object(300,200,985,75)); // ob6
		
		object.add(new Object(200,300,63,550)); // ob7
		object.add(new Object(200,765,99,90)); // ob8
		object.add(new Object(200,810,1100,50)); // ob9
		
		character.setAllObject(object);
		
		
		try {
			allObjectPicture.add(new ObjectPicture(ImageIO.read(new File("eventOutDungeon/bone.png")),200,665,200,190));
			allObjectPicture.add(new ObjectPicture(ImageIO.read(new File("eventOutDungeon/label.png")),720,300,150,170));
			allObjectPicture.add(new ObjectPicture(ImageIO.read(new File("eventOutDungeon/olaf.png")),1075,200,180,210));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}



	public void run() {
	
		
		int i=0;
		while(true) {
		System.out.println("state 2 :"+i++);
			
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
	
		
		character.draw(g);
		if(eventPicture()) {
			g.drawImage(event, 0, 0,1280, 960,null);
		}
		//g.setColor(Color.white);
//		for(int i=0;i<allObjectPicture.size();i++) {
//			g.drawRect(allObjectPicture.get(i).getX(), allObjectPicture.get(i).getY(),allObjectPicture.get(i).getWidth(), allObjectPicture.get(i).getHeight());
//		}
		
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
	public boolean eventPicture() {
		for(int i=0;i<allObjectPicture.size();i++) {
			if(character.getXFoot() > allObjectPicture.get(i).getX() && character.getXFoot() < allObjectPicture.get(i).getX()+allObjectPicture.get(i).getWidth()&&
				character.getYFoot() > allObjectPicture.get(i).getY() && character.getYFoot() < allObjectPicture.get(i).getY()+allObjectPicture.get(i).getHeight()) {
				event = allObjectPicture .get(i).getIm();
				return true;
			}
		}
		event = null;
		return false;
	}
	public ArrayList<Object> getObject() {
		return object;
	}
}