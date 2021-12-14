package project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import objectEvent.*;

public class Character {
	private Thread thread;
	private int i=0;
	private int x=320;
	private int y=500;
	private int  speedWalk =15;
	private BufferedImage ImageCharacter,event=null,camera;
	private boolean isCamera=false;
	private Animation character;
	private ArrayList<Object> allObject;
	private AllImage allImage;
	//private ArrayList<ObjectPicture> allObjectPicture;
	private boolean isWalk=true;
	
	private ArrayList<BufferedImage> rightWalk,leftWalk,upWalk,downWalk;
	public Character() {
		//allObjectPicture = new ArrayList<ObjectPicture>();
		allImage = new AllImage();
		ImageCharacter=AllImage.AlexRight;
		character = new Animation();
		character.addFrame(null);
		
		rightWalk= new ArrayList<BufferedImage>();
		rightWalk.add(AllImage.AlexRight);
		rightWalk.add(AllImage.AlexRight1);
		
		leftWalk = new ArrayList<BufferedImage>();
		leftWalk.add(AllImage.AlexLeft);
		leftWalk.add(AllImage.AlexLeft1);
		
		upWalk= new ArrayList<BufferedImage>();
		upWalk.add(AllImage.AlexBack);
		upWalk.add(AllImage.AlexBack1);
		
		downWalk= new ArrayList<BufferedImage>();
		downWalk.add(AllImage.AlexFront);
		downWalk.add(AllImage.AlexFront1);
		
		try {
			camera = ImageIO.read(new File("camera/shadowCam1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.black);
		//g.drawRect(x+15,y+130,38,20);
		g.drawImage(ImageCharacter,x,y,76,152,null);
		if(isCamera)
			g.drawImage(camera, x-2000,y-1250, null);
		
		if(event!=null)
			g.drawImage(event, 400, 0, 500, 500, null);
		
	}
	
	public void walk(char button) {
		
		if(button =='a') {
			if(isWalk) {
				character.addAnimation(leftWalk);
				if(intersec(button))
					x=x-speedWalk;
				character.update();
				ImageCharacter = character.getFrame();
			}
			
		}
		else if(button =='d') {
			if(isWalk) {
				character.addAnimation(rightWalk);
				if(intersec(button))
					x=x+speedWalk;
				character.update();
				ImageCharacter = character.getFrame();
			}
			
			
		}
		else if(button =='w') {
			if(isWalk) {
				character.addAnimation(upWalk);
				
				if(intersec(button))
					y=y-speedWalk;character.update();
				
				ImageCharacter = character.getFrame();
			}
		}
		else if(button =='s') {
			
			if(isWalk) {
				character.addAnimation(downWalk);
				if(intersec(button))
					y=y+speedWalk;
				character.update();
				ImageCharacter =  character.getFrame();
			
			}
		}
		
	}
	
	
	
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
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
	public int getSpeedWalk() {
		return speedWalk;
	}
	public void setSpeedWalk(int speedWalk) {
		this.speedWalk = speedWalk;
	}




	public ArrayList<Object> getAllObject() {
		return allObject;
	}

	public void setAllObject(ArrayList<Object> allObject) {
		this.allObject = allObject;
	}

	
	public boolean intersec(char button) {
		int width=24*2-10;
		int height=20*2-20;
		int y=this.y+130;
		int x=this.x+15;
		if(button=='w') {
			for(int i=0;i<allObject.size();i++) {
				
				if(allObject.get(i).getWidth() < ImageCharacter.getWidth()) {
					if( (allObject.get(i).getX() < x+width && allObject.get(i).getX() > x) ||
					   allObject.get(i).getX() + allObject.get(i).getWidth() <= x+width && allObject.get(i).getX()+allObject.get(i).getWidth() >= x	)
						if(y-speedWalk < allObject.get(i).getY() + allObject.get(i).getHeight() && y-speedWalk > allObject.get(i).getY()) {
							return false;
					}
				}
				else {
					if(x < allObject.get(i).getX() + allObject.get(i).getWidth() && x > allObject.get(i).getX()||
					   x +width < allObject.get(i).getX()+allObject.get(i).getWidth() && x+width > allObject.get(i).getX()	)
						if(y-speedWalk < allObject.get(i).getY()+allObject.get(i).getHeight() && y-speedWalk > allObject.get(i).getY()) {
							return false;
						}
				}
				
			}
		}
		else if(button =='s') {
			for(int i=0;i<allObject.size();i++) {
				if(allObject.get(i).getWidth() < width) {
					if( (allObject.get(i).getX() < x+width && allObject.get(i).getX() > x) ||
					   allObject.get(i).getX() + allObject.get(i).getWidth() < x+width && allObject.get(i).getX()+allObject.get(i).getWidth() > x	)
						if(y + height + speedWalk >= allObject.get(i).getY()&& y+speedWalk+height < allObject.get(i).getY()+allObject.get(i).getHeight() ) {
							return false;
					}
				}
				else {
					if(x < allObject.get(i).getX()+allObject.get(i).getWidth() && x>allObject.get(i).getX()||
					   x+width < allObject.get(i).getX()+allObject.get(i).getWidth() && x+width>allObject.get(i).getX()	)
						if(y + height + speedWalk > allObject.get(i).getY() && y+speedWalk+height < allObject.get(i).getY()+allObject.get(i).getHeight()) {
							return false;
						}
				}
			}
		}
		else if(button =='d'){
			for(int i=0;i<allObject.size();i++) {
				if(allObject.get(i).getHeight() < height) {
					if( (allObject.get(i).getY() < y+height && allObject.get(i).getY() > y) ||
					   allObject.get(i).getY() + allObject.get(i).getHeight() < y+height && allObject.get(i).getY()+allObject.get(i).getHeight() > y	)
						if(x + width + speedWalk > allObject.get(i).getX()&& x+speedWalk+width < allObject.get(i).getX()+allObject.get(i).getWidth() ) {
							return false;
					}
				}
				else {
					if(y < allObject.get(i).getY()+allObject.get(i).getHeight() && y>=allObject.get(i).getY()||
					   y+height < allObject.get(i).getY()+allObject.get(i).getHeight() && y+height>allObject.get(i).getY()	)
						if(x + width + speedWalk > allObject.get(i).getX() && x+speedWalk+height < allObject.get(i).getX()+allObject.get(i).getWidth()) {
							return false;
						}
				}
			}
		}
		else if(button =='a') {
			for(int i=0;i<allObject.size();i++) {
				if(allObject.get(i).getHeight() < height) {
					if( (allObject.get(i).getY() < y+height && allObject.get(i).getY() > y) ||
					   allObject.get(i).getY() + allObject.get(i).getHeight() < y+height && allObject.get(i).getY()+allObject.get(i).getHeight() > y	)
						if(x - speedWalk < allObject.get(i).getX()+allObject.get(i).getWidth() && x-speedWalk > allObject.get(i).getX()  ) {
							return false;
					}
				}
				else {
					if(y < allObject.get(i).getY()+allObject.get(i).getHeight() && y>allObject.get(i).getY()||
					   y+height < allObject.get(i).getY()+allObject.get(i).getHeight() && y+height>allObject.get(i).getY()	)
						if(x - speedWalk < allObject.get(i).getX()+allObject.get(i).getWidth() && x-speedWalk > allObject.get(i).getX()) {
							return false;
						}
				}
			}
		}
		
		return true;
		
	}
	public void setSideCharacter(char side) {
		if(side=='a') {
			ImageCharacter = AllImage.AlexLeft;
		}
		else if(side=='d') {
			ImageCharacter = AllImage.AlexRight;
		}
		else if(side=='s') {
			ImageCharacter = AllImage.AlexFront;
		}
		else if(side == 'w') {
			ImageCharacter = AllImage.AlexBack;
		}
	}

	public int getXFoot() {
		return x+15;
		
	}
	public int getYFoot() {
		return y+130;
	}

	public boolean isWalk() {
		return isWalk;
	}

	public void setWalk(boolean isWalk) {
		this.isWalk = isWalk;
	}

	public boolean isCamera() {
		return isCamera;
	}

	public void setCamera(boolean isCamera) {
		this.isCamera = isCamera;
	}
}
