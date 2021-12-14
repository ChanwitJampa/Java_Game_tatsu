package Etc;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;



public class playmusic {
 
 private boolean k=true;
 public void playMusic(String name) throws UnsupportedAudioFileException, LineUnavailableException  {
  
  try {
   File music =new File(name);
   if(music.exists()) {
    AudioInputStream audioInput = AudioSystem.getAudioInputStream(music);
  
    Clip clip = AudioSystem.getClip();
    clip.open(audioInput);
    clip.start();
    if(k==false) {
     clip.stop();
    }
   }
   else {
    System.out.println("Can not found");
   }
  }
  catch(Exception e) {
   e.printStackTrace();
  }
 }
 public void stopMusic() throws LineUnavailableException {
  k=false;
 }
}