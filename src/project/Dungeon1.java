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

public class Dungeon1 extends JPanel implements Runnable,KeyListener{

	private Thread thread;

	private Character character;
	
	private int x=0;
	private int y=0;
	private BufferedImage im=null;
	private ArrayList<Object> object;
	private boolean setLoop=false;
	private ArrayList<BufferedImage> allTalk,allTalk2;
	private int state=0;
	private NPC npc;
	private boolean talk2=false;
	private boolean talk=false;
	private playmusic p;
	
	
	public Dungeon1(Character character) {
		object = new ArrayList<Object>();
		this.character = character;
		p = new playmusic();

		allTalk= new ArrayList<BufferedImage>();
		allTalk2=new ArrayList<BufferedImage>();
		try {
			allTalk.add(ImageIO.read(new File("grandaf/gandalf1.png")));	
			allTalk.add(ImageIO.read(new File("grandaf/gandalf2.png")));
			allTalk.add(ImageIO.read(new File("grandaf/gandalf3.png")));
			allTalk.add(ImageIO.read(new File("grandaf/gandalf4.png")));
			
			allTalk2.add(ImageIO.read(new File("grandaf/gandalf5.png")));	
			allTalk2.add(ImageIO.read(new File("grandaf/gandalf6.png")));
			allTalk2.add(ImageIO.read(new File("grandaf/gandalf7.png")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		npc= new NPC(allTalk,560,280,115,215);
		
		try {
			 im = ImageIO.read(new File("Background/mapDungeon1.png"));
		} catch (IOException e) {
			System.out.println("NOTFOUND");
		}
		object.add(new Object(320,200,55,316)); // ob1
		object.add(new Object(220,300,55,316));
		object.add(new Object(320,180,206,110)); // ob2
		object.add(new Object(750,180,206,110)); // ob2-1
		
		
		object.add(new Object(906,200,50,450)); // ob3
		object.add(new Object(599,593,340,200)); // ob4
		object.add(new Object(320,778,400,50)); // ob5
		object.add(new Object(320,588,50,220)); // ob6
		object.add(new Object(500,195,300,95)); // ob2-2  close door
		
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
		//g.drawRect(550,543,100,100);
		
		
		
		
		character.draw(g);
		
		if(talkNpc()) {
			g.drawImage(npc.getFrame(), 0, 0,1280,960, null);
		}
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
		
		if(talkNpc()&&npc.getIndex()<npc.getSizeImage()) {
			character.setWalk(false);
		}
		
		character.walk(button);
		
		if(talkNpc()&&button!='d'&&button !='s'&&button!='a'&&button!='w'&&npc.getIndex()<npc.getSizeImage()) {
			System.out.println(npc.getIndex()+" "+npc.getSizeImage());
			
			npc.update();
			
			if(npc.getIndex() == npc.getSizeImage()) {
				character.setWalk(true);
				setTalk();
			}
		}
	}



	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}
	public void keyTyped(KeyEvent e) {
		
		
	}
	
	public boolean nextMap() {
		if(character.getY()<120)
			return true;
		return false;
	}
	public boolean previousMap() {
		if(character.getX()<300) {
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
		if(character.getX()>450&&character.getX()<650&&character.getY()>543&&character.getY()<643) {
			return true;
		}
		return false;
	}
	public boolean getTalk() {
		return talk;
	}
	public boolean getTalk2() {
		return talk2;
	}
	public void setTalk() {
		if(npc.getIndex()>=npc.getSizeImage()) {
			System.out.println("Finish");
			if(state==2) {
				try {
					p.playMusic("res/music/openDungeonDoor.wav");
				} catch (UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					im = ImageIO.read(new File("Background/mapDungeon3.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(object.size()==9)
					object.remove(8);
				talk2=true;
			}
			talk=true;
		}
	}
	public void setTalk(boolean s) {
		talk =s;
	}


	public ArrayList<BufferedImage> getAllTalk() {
		return allTalk;
	}



	public void setAllTalk(ArrayList<BufferedImage> allTalk) {
		this.allTalk = allTalk;
	}
	
	public void setState2() {
		npc= new NPC(allTalk2,560,280,115,215);
		state =2;
	}
	public ArrayList<Object> getObject() {
		return object;
	}


}
