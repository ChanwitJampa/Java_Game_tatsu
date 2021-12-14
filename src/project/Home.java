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
import objectEvent.ObjectPicture;

public class Home extends JPanel implements Runnable,KeyListener{

	private Thread thread;

	
	private Character character;
	
	private int i=0;
	private int start=0;
	private int x=0;
	private int y=0;
	private int now=0;;
	private BufferedImage im=null,event=null;
	private ArrayList<Object> object;
	private boolean setLoop=false;
	private ArrayList<ObjectPicture> allObjectPicture;
	private ArrayList<BufferedImage> screenEnding;
	private playmusic p;
	private int index=0;
	private int state=0;
	private boolean next =false;
	private int first=0;
	public Home(Character character) {
		
	    String nameFile;
		screenEnding = new ArrayList<BufferedImage>();
		p= new playmusic();
		
		object = new ArrayList<Object>();
		this.character =character;
		allObjectPicture = new ArrayList<ObjectPicture>();
		try {
			 im = ImageIO.read(new File("Background/mapHome.png"));
		} catch (IOException e) {
			System.out.println("NOTFOUND");
		}
		object.add(new Object(0,0,1200,150)); // ob1
		object.add(new Object(450,140,1200,150)); // ob2
		object.add(new Object(840,220,400,150)); // ob3
		object.add(new Object(1130,220,50,550)); // ob4
		object.add(new Object(1050,735,150,150)); // ob5
		object.add(new Object(50,805,1200,50)); // ob6
		object.add(new Object(100,390,50,500)); // ob7
		object.add(new Object(100,140,50,150)); // ob8
		object.add(new Object(50,265,50,150)); // ob9
		object.add(new Object(50,265,50,150)); // ob10
		object.add(new Object(150,460,490,130)); // ob11
		object.add(new Object(600,460,40,268)); // ob12
		
		character.setAllObject(object);
		
		
		try {
			
			allObjectPicture.add(new ObjectPicture(ImageIO.read(new File("HomeEvent/bone.png")),1000,685,150,170));
			allObjectPicture.add(new ObjectPicture(ImageIO.read(new File("HomeEvent/paper.png")),1040,370,100,50));
			allObjectPicture.add(new ObjectPicture(ImageIO.read(new File("HomeEvent/key.png")),520,590,75,75));
			
			for(int i=1;i<78;i++) {
				
				nameFile="ScreenEnding/end"+i+".png";
				System.out.println(nameFile);
				try {
					screenEnding.add(ImageIO.read(new File(nameFile)));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}



	public void run() {
	
		
		while(true) {
		System.out.println("state home :"+i++);
			
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
			if(event!=null)
			g.drawImage(event, 0, 0,1280, 960,null);
		}
		if(state==3) {
			g.drawImage(im, x, y,1280,960,null);
			
			g.drawImage(screenEnding.get(index),0, 0, 1280, 960, null);
			
			if(i>5&&index<screenEnding.size()-1) {
				index++;
				i=0;
			}
			if(index==screenEnding.size()-1) {
				next=true;
				
			}
		}
//		for(int i=0;i<object.size();i++) {
//			g.drawRect(object.get(i).getX(), object.get(i).getY(),object.get(i).getWidth() , object.get(i).getHeight());
//		}
//		g.setColor(Color.green);
//		for(int i=0;i<allObjectPicture.size();i++) {
//			g.drawRect(allObjectPicture.get(i).getX(), allObjectPicture.get(i).getY(),allObjectPicture.get(i).getWidth(), allObjectPicture.get(i).getHeight());
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
		return next;
	}
	public boolean previousMap() {
		if(character.getX()<120) {
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
			if(character.getXFoot() > allObjectPicture.get(i).getX() && character.getXFoot() <allObjectPicture.get(i).getX()+allObjectPicture.get(i).getWidth()&&
				character.getYFoot() > allObjectPicture.get(i).getY() && character.getYFoot() <allObjectPicture.get(i).getY()+allObjectPicture.get(i).getHeight()) {
				event = allObjectPicture .get(i).getIm();
				if(allObjectPicture.size()==3) {
					if(event == allObjectPicture.get(2).getIm()&&state!=1) {
						try {
							p.playMusic("res/music/keySound.wav");
						} catch (UnsupportedAudioFileException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (LineUnavailableException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						state=1;
					}
				}
				if(state==2)
					if(event==allObjectPicture.get(1).getIm()) {
						System.out.println("Start "+start);
						if(character.isWalk()) {
							start=this.i+200;
							character.setWalk(false);
						}
						if(start<this.i) {
							if(first==0) {
								try {
									 im = ImageIO.read(new File("Background/End.png"));
								} catch (IOException e) {
									System.out.println("NOTFOUND");
								}
								character.setSideCharacter('w');
								try {
									p.playMusic("res/music/openDoorHome.wav");
								} catch (UnsupportedAudioFileException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (LineUnavailableException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								first=1;
							}
							if(now==0)
								now=this.i;
							if(now+70<this.i) {
								state=3;
								this.i=0;
							}
						}
					}
						
				return true;
			}
		}
		if(state==1&&allObjectPicture.size()==3) {
			
			System.out.println("remove");
			allObjectPicture.remove(1);
			allObjectPicture.remove(1);
			try {
				allObjectPicture.add(new ObjectPicture(ImageIO.read(new File("HomeEvent/end.png")),1040,370,100,50));
			} catch (IOException e) {
				e.printStackTrace();
			}
			state=2;
		}
		event = null;
		return false;
	}
	public ArrayList<Object> getObject() {
		return object;
	}
	public ArrayList<BufferedImage> getScreenEnding() {
		return screenEnding;
	}



	public void setScreenEnding(ArrayList<BufferedImage> screenEnding) {
		this.screenEnding = screenEnding;
	}
	
}