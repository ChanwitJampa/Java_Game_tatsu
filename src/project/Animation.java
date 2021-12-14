package project;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation implements Runnable{
	
	private ArrayList<BufferedImage> frames;
	private int index=0;
	private double count =0;
	public Animation() {
		frames = new ArrayList<BufferedImage>();
	}
	
	public void update() {
		count+=0.4;
		if(count>1) {
			index++;
			count=0;
		}
		if(index>=frames.size())
			index=0;
	}
	
	public void addFrame(BufferedImage	 frame) {
		frames.add(frame);
	}
	public void addAnimation(ArrayList<BufferedImage> im) {
		frames = im;
	}
	
	public BufferedImage getFrame() {
		if(frames.size() > 0) {
			return frames.get(index);
		}
		return null;
	}

	@Override
	public void run() {
		update();
		
	}
	
} 
