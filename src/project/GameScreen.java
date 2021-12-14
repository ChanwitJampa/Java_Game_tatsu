package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

import objectEvent.*;

public class GameScreen extends JPanel implements Runnable,KeyListener{

	private Thread thread;
	
	private Character character;
	private ArrayList<ObjectPicture> objectPicture;
	
	
	private int xem =0;
	private int yem =0;
	private int start=0;
	private int x=0;
	private int y=0;
	private int i=0;
	
	private BufferedImage im=null,event=null,how=null;
	private ArrayList<Object> object;
	private boolean setLoop=false;
	private ArrayList<ObjectPicture> allObjectPicture;
	
	public GameScreen(Character character) {
		this.character = character;
		object = new ArrayList<Object>();
		allObjectPicture = new ArrayList<ObjectPicture>();
		
		
		try {
			 im = ImageIO.read(new File("Background/startgame.png"));
			 how = ImageIO.read(new File("gameScreen/howto.png"));
		} catch (IOException e) {
			System.out.println("NOTFOUND");
		}
		
		object.add(new Object(295,380,120,60)); // ob1
		object.add(new Object(405,380,200,110)); // ob2
		object.add(new Object(590,365,65,69)); // ob3
		object.add(new Object(650,341,635,30)); // ob4
		object.add(new Object(776,368,400,72)); //ob5
		object.add(new Object(995,438,42,55)); // ob6
		object.add(new Object(280,380,30,380)); // ob7
		object.add(new Object(300,740,985,60)); // ob8d
		
		
		try {
			allObjectPicture.add(new ObjectPicture(ImageIO.read(new File("gameScreen/missing.png")),955,438,95,105));
		} catch (IOException e) {
			e.printStackTrace();
		}
		character.setAllObject(object);
		
	}
	public Character getCharacter() {
		return character;
	}



	public void setCharacter(Character character) {
		this.character = character;
	}


	public void run() {
		int loop=0;
		Random rand = new Random();

		while(true) {
		System.out.println("state 0 :"+i++);
		int isWalk = rand.nextInt(100);
		int direction = rand.nextInt(4);
		int numWalk = rand.nextInt(3);
		
		if(isWalk<2) {
			if(direction==1) {
				if(xem<800)
					xem+=numWalk*50;
				else
					xem-=numWalk*50;
			}
			else if(direction==2) {
				if(xem>0)
					xem-=numWalk*50;
				else
					xem+=numWalk*50;
				
			}
			else if(direction==3) {
				if(yem<800)
					yem+=numWalk*50;
				else
					yem-=numWalk*50;
			}
			else if(direction==4) {
				if(yem>0)
					yem-=numWalk*50;
				else
					yem+=numWalk*50;
			}
		}
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
		if(i<80)
			g.drawImage(how, 0,0, 1280, 960, null);
		if(eventPicture()) {
			g.drawImage(event, 0,0,1280, 960,null);
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
		if(character.getX()>1200)
			return true;
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
	public boolean eventPicture() {
		for(int i=0;i<allObjectPicture.size();i++) {
			if(character.getXFoot() > allObjectPicture.get(i).getX() && character.getXFoot() <allObjectPicture.get(i).getX()+allObjectPicture.get(i).getWidth()&&
				character.getYFoot() > allObjectPicture.get(i).getY() && character.getYFoot() <allObjectPicture.get(i).getY()+allObjectPicture.get(i).getHeight()) {
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
	public void setObject(ArrayList<Object> object) {
		this.object = object;
	}


	



	


	
}
