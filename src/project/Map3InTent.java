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
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import Etc.playmusic;

public class Map3InTent extends JPanel implements Runnable,KeyListener,MouseListener{

	private Thread thread;
	private JButton bt1;
	private Character character;
	private boolean clean = false;
	private JButton bt;
	private int xem =0;
	private int yem =0;
	private int start=0;
	private int x=0;
	private int y=0;
	private BufferedImage im=null,item;
	private ArrayList<Object> object;
	private boolean setLoop=false;
	private playmusic p;
	
	public Map3InTent(Character character) {
		object = new ArrayList<Object>();
		this.character = character;
		p = new playmusic();
		
		bt = new JButton();
		bt.setPreferredSize(new Dimension(100, 100));
		bt.setBackground(Color.black);
		bt.setBorderPainted(false);
		bt.addMouseListener(this);
		add(bt);
		
	
		try {
			 im = ImageIO.read(new File("Background/Map3InTent.png"));
			 item= ImageIO.read(new File("item/cleanup.png"));
		} catch (IOException e) {
			System.out.println("NOTFOUND");
		}
		object.add(new Object(425,580,100,280)); // ob1
		object.add(new Object(450,560,200,50)); // ob2
		object.add(new Object(604,580,100,280)); // ob3
		character.setAllObject(object);
		
	}



	public void run() {

		
		int i=0;
		while(true) {
		System.out.println("state 3InTent :"+i++);
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
		g.drawImage(item, 500,-50, 200,200,null);
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
		if( (character.getX()>490&&character.getX()<613&&character.getY()>680&&character.getY()<812) ) {
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



	public JButton getBt1() {
		return bt1;
	}



	public void setBt1(JButton bt1) {
		this.bt1 = bt1;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			p.playMusic("res/music/brooming.wav");
		} catch (UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(e);
		setClean(true);
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public boolean isClean() {
		return clean;
	}



	public void setClean(boolean clean) {
		this.clean = clean;
	}
	public ArrayList<Object> getObject() {
		return object;
	}
	
}
