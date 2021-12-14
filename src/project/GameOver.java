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

public class GameOver extends JPanel implements Runnable,KeyListener{

	private Thread thread;

	private Character character;
	
	private int xem =0;
	private int yem =0;
	private int start=0;
	private int x=0;
	private int y=0;
	private BufferedImage im=null,hole=null;
	private ArrayList<BufferedImage> allImage;
	private ArrayList<Object> object;
	private boolean setLoop=false;
	private ArrayList<ObjectEvent> allObjectDead;
	private int index=0;
	private int i=0;
	private boolean next=false;
	
	public GameOver() {
		String fileName;
		this.character=  character;
		object = new ArrayList<Object>();
		allObjectDead = new ArrayList<ObjectEvent>();
		allImage = new ArrayList<BufferedImage>();
		
		for(int i=1;i<51;i++) {
			
			fileName="res/GameOver/Over"+i+".png";
			try {
				 allImage.add(ImageIO.read(new File(fileName)));
			} catch (IOException e) {
				System.out.println("NOTFOUND");
			}
		}
		
	}



	public void run() {
	
		while(true) {
		System.out.println("state dead :"+i++);
			repaint();
			if(i>5)
			{
				index++;
				i=0;
			}
			try {
				thread.sleep(20);
			} catch (InterruptedException e) {}
			if(setLoop)
				break;
			
		}
	}
	
	
	public void paint(Graphics g) {	
		if(index<allImage.size())
			im = allImage.get(index);
		g.drawImage(im, 0, 0, 1280,960,null);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if( !(e.getKeyChar()=='w'||e.getKeyChar()=='s'||e.getKeyChar()=='a'||e.getKeyChar()=='d') )
			next=true;
	}



	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}
	public void keyTyped(KeyEvent e) {
		
		
	}
	
	public boolean nextMap() {
		return next;
	}
	public void setNext(boolean next) {
		this.next=next;
		index=0;
	}



	public boolean isSetLoop() {
		return setLoop;
	}



	public void setSetLoop(boolean setLoop) {
		this.setLoop = setLoop;
	}
	
	
	
}
