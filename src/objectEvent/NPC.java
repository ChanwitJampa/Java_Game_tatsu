package objectEvent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class NPC implements KeyListener {
	private int x;
	private int y;
	private int width;
	private int height;
	private int index=0;
	private ArrayList<BufferedImage> im;
	
	public NPC(ArrayList<BufferedImage> im,int x,int y , int width, int height) {
		this.setIm(im);
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public ArrayList<BufferedImage> getIm() {
		return im;
	}

	public void setIm(ArrayList<BufferedImage> im) {
		this.im = im;
	}
	
	public void update() {
		index++;
		
	}
	
	public void addFrame(BufferedImage	 frame) {
		im.add(frame);
	}
	public void addTalk(ArrayList<BufferedImage> im) {
		this.im = im;
	}
	
	public BufferedImage getFrame() {
		if(im.size() > 0&&index<im.size()) {
			return im.get(index);
		}
		return null;
	}
	public int getSizeImage() {
		return im.size();
	}
	
	

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		index++;
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
