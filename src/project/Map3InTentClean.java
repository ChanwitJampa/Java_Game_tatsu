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

public class Map3InTentClean extends JPanel implements Runnable,KeyListener{

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
	
	public Map3InTentClean(Character character) {
		object = new ArrayList<Object>();
		this.character= character;
		
		try {
			 im = ImageIO.read(new File("Background/Map3InTentClean.png"));
		} catch (IOException e) {
			System.out.println("NOTFOUND");
		}
		object.add(new Object(399,390,50,350)); // ob1
		object.add(new Object(399,357,130,92)); // ob2
		object.add(new Object(399,320,300,50)); // ob3
		object.add(new Object(675,320,50,127)); // ob4
		object.add(new Object(675,397,100,50)); // ob5
		object.add(new Object(752,397,50,235)); // ob6
		object.add(new Object(678,590,80,150)); // ob7
		object.add(new Object(602,715,100,120)); // ob8
		object.add(new Object(425,715,100,120)); // ob9
		character.setAllObject(object);
	}



	public void run() {
	
		
		int i=0;
		while(true) {
		System.out.println("state 3 :"+i++);
	
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
		g.setColor(Color.white);
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
		//System.out.println("check");
		if(character.getY()>680)
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
}
