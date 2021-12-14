package objectEvent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ObjectPicture {
	private int x;
	private int y;
	private int width;
	private int height;
	private BufferedImage im;
	
	public ObjectPicture(BufferedImage im,int x,int y , int width, int height) {
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

	public BufferedImage getIm() {
		return im;
	}

	public void setIm(BufferedImage im) {
		this.im = im;
	}
	

}
