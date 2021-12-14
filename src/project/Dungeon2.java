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

import Etc.playmusic;
import objectEvent.NPC;

public class Dungeon2 extends JPanel implements Runnable,KeyListener{

	private Thread thread;

	private Character character;
	
	private int xem =0;
	private int yem =0;
	private int start=0;
	private int x=0;
	private int y=0;
	private BufferedImage im=null;
	private BufferedImage item;
	private ArrayList<Object> object;
	private boolean setLoop=false;
	private ArrayList<BufferedImage> allTalk;
	private NPC npc;
	private boolean isItem=true;
	private boolean talk=false;
	private playmusic p;
	public boolean isItem() {
		return isItem;
	}



	public void setItem(boolean isItem) {
		this.isItem = isItem;
	}



	public Dungeon2(Character character) {
		object = new ArrayList<Object>();
		this.character = character;
		 p = new playmusic();
		allTalk= new ArrayList<BufferedImage>();
		
		try {
			allTalk.add(ImageIO.read(new File("book/talkBook.png")));	
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		npc= new NPC(allTalk,560,280,115,215);
		
		try {
			 im = ImageIO.read(new File("Background/mapDungeon2.png"));
			 item = ImageIO.read(new File("item/book.png"));
			 
		} catch (IOException e) {
			System.out.println("NOTFOUND");
		}
		
		object.add(new Object(320,300,55,420)); // ob1
		object.add(new Object(320,300,650,65)); // ob2
		object.add(new Object(905,300,100,142)); // ob3
		object.add(new Object(905,518,90,200)); // ob4
		object.add(new Object(352,700,600,50)); // ob5
		
		
		object.add(new Object(602,360,75,82)); // ob6
		object.add(new Object(602,665,75,82)); // ob7
		object.add(new Object(458,445,57,60)); // ob8
		object.add(new Object(980,400,100,200));
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
		if(isItem)
			g.drawImage(item,0,0,1280,960,null);
		g.setColor(Color.black);
		//g.drawRect(408,405,145,125);
		character.draw(g);
		
//		g.setColor(Color.white);
//		for(int i=0;i<object.size();i++) {
//			g.drawRect(object.get(i).getX(), object.get(i).getY(),object.get(i).getWidth() , object.get(i).getHeight());
//		}
		if(talkNpc()) {
			g.drawImage(npc.getFrame(), 0, 0,1280,960, null);
		}
	
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
		if(character.getX()<0)
			return true;
		return false;
	}
	public boolean previousMap() {
		if(character.getX()>910) {
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
		if(character.getXFoot()>408&&character.getXFoot()<553&&character.getYFoot()>405&&character.getYFoot()<530) {
			if(isItem) {
				
				try {
					p.playMusic("res/music/paper.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
	public ArrayList<Object> getObject() {
		return object;
	}
	
}
