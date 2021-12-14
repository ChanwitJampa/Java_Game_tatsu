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

import objectEvent.NPC;
import objectEvent.ObjectEvent;

public class Map4 extends JPanel implements Runnable,KeyListener{

	private Thread thread;

	private Character character;
	private NPC npc;
	private int xem =0;
	private int yem =0;
	private int start=0;
	private int x=0;
	private int y=0;
	private BufferedImage im=null,hole=null;
	private ArrayList<Object> object;
	private boolean setLoop=false;
	private ArrayList<BufferedImage> allTalk;
	private int i=0;
	
	
	public Map4(Character character) {
		object = new ArrayList<Object>();
		
		this.character = character;
		

		allTalk= new ArrayList<BufferedImage>();
		
		try {
				allTalk.add(ImageIO.read(new File("kirito/kirito5.png")));	
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		
		
		npc= new NPC(allTalk,560,280,115,215);
	
		try {
			 im = ImageIO.read(new File("Background/Map3.png"));
		} catch (IOException e) {
			System.out.println("NOTFOUND");
		}
		object.add(new Object(0,310,620,50)); // ob1
		object.add(new Object(80,350,70,69)); // ob2
		object.add(new Object(258,350,190,69)); // ob3
		object.add(new Object(608,280,65,165)); // ob4
		object.add(new Object(650,245,130,50)); // ob5
		object.add(new Object(752,280,260,82)); // ob6
		object.add(new Object(994,280,50,430)); // ob7
		object.add(new Object(0,669,524,50)); // ob8
		object.add(new Object(475,669,50,225)); // ob9
		object.add(new Object(475,885,310,30)); // ob 10
		object.add(new Object(748,667,50,225)); // ob 11
		object.add(new Object(748,667,280,50)); // ob 12
		
		
		character.setAllObject(object);
	}



	public void run() {
		int i=0;
		while(true) {
			System.out.println("Map4 "+i++);
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
		character.walk(button);
		if(talkNpc()&&npc.getIndex()<npc.getSizeImage()) {
			character.setWalk(false);
		}
		
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
		if(character.getX()<0)
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
	public boolean talkNpc() {
		if(character.getX()>560&&character.getX()<675&&character.getY()>280&&character.getY()<495) {
			return true;
		}
		return false;
	}
	public ArrayList<Object> getObject() {
		return object;
	}
}
