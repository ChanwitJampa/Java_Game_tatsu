package project;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AllImage {
	public static BufferedImage ImageCharacter;
    public static BufferedImage AlexFront,AlexFront1,AlexLeft,AlexLeft1,AlexRight,AlexBack,AlexBack1;
    
    public static BufferedImage AlexRight1;
    private static final int height=32,width=16;
    public AllImage() {
    	init();	
    }
    public static void init(){
    	
       try {
		ImageCharacter =  ImageIO.read(new File("Character/Alex.png"));
	} catch (IOException e) {}
        
        AlexFront = ImageCharacter.getSubimage(0,0,width,height);
        AlexFront1 = ImageCharacter.getSubimage(width,0,width,height);
        
        AlexRight=ImageCharacter.getSubimage(0,height,width,height);
        AlexRight1=ImageCharacter.getSubimage(width,height,width,height);
        
        AlexBack = ImageCharacter.getSubimage(0,height*2,width,height);
        AlexBack1 = ImageCharacter.getSubimage(width,height*2,width,height);

        
        AlexLeft = ImageCharacter.getSubimage(0,height*3,width,height);
        AlexLeft1 = ImageCharacter.getSubimage(width, height*3, width, height);
    }
}
