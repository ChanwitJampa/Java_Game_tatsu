package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import Etc.playmusic;

public class Gamewindow extends JFrame implements Runnable{
	private GameOver gameOver;
	private Character character;
	private Intro intro;
	private GameScreen gameScreen;
	private Map2 map2;
	private Map3 map3;
	private Map3InTent map3InTent;
	private Map3InTentClean map3InTentClean;
	private Map4 map4;
	private Map5 map5;
	private Dungeon0 dungeon0;
	private Dungeon1 dungeon1;
	private Dungeon2 dungeon2;
	private OutDungeon outDungeon;
	private Home home;
	private Ending ending;
	
	private Thread thread,thread2;
	private int state =0;
	private boolean status=true;
	private String nameFile;
	

	private playmusic p;
	private playmusic p2;
	//private ArrayList<BufferedImage> screenEnding;
	
	public Gamewindow(int width,int height) {
		super("game");
		setSize(width,height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		 p =new playmusic();
		 p2 = new playmusic();
		 
		 try {
			 p.playMusic("music/Christmas.wav");
			 //p.playMusic("res/music/amongus.wav");
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		intro= new Intro("PlaiGame",1280,960);
	
		character = new Character();
		
		gameOver = new GameOver();
		gameScreen = new GameScreen(character);
		map2 = new Map2(character);
		map3 = new Map3(character);
		map3InTent = new Map3InTent(character);
		map3InTentClean = new Map3InTentClean(character);
		map4 = new Map4(character);
		map5 = new Map5(character);
		dungeon0 = new Dungeon0(character);
		dungeon1 = new Dungeon1(character);
		dungeon2 = new Dungeon2(character);
		outDungeon = new OutDungeon(character);
		home = new Home(character);
		//home.setScreenEnding(screenEnding);
		
		ending = new Ending("PlaiGame",1280,960);
		
		intro.start();
		setContentPane(intro.getJPanel());
		addKeyListener(intro);
		setVisible(true);
		
		
		thread2= new Thread(this);
		thread2.start();
	}
	


	@Override
	public void run() {
		int i=0;
		
		if(i>40) {
			
		}
		
		while(true) {
			
			System.out.println("main: "+i++);
			if(state ==-1) {
				if(status) {
					try {
						p.playMusic("res/music/amongus.wav");
					} catch (UnsupportedAudioFileException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (LineUnavailableException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					// p.playMusic("music/Christmas.wav");
					gameOver = new GameOver();
					thread = new Thread(gameOver);
					setContentPane(gameOver);
					addKeyListener(gameOver);
					setVisible(false);
					setVisible(true);
					thread.start();
					
					gameScreen = new GameScreen(character);
					map2 = new Map2(character);
					map3 = new Map3(character);
					map3InTent = new Map3InTent(character);
					map3InTentClean = new Map3InTentClean(character);
					map4 = new Map4(character);
					map5 = new Map5(character);
					dungeon0 = new Dungeon0(character);
					dungeon1 = new Dungeon1(character);
					dungeon2 = new Dungeon2(character);
					outDungeon = new OutDungeon(character);
					//home = new Home(character);
					
				
					status=false;
				}
				else if(gameOver.nextMap()) {
					System.out.println("ENDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
					state=1;
					removeKeyListener(gameOver);
					gameOver.setSetLoop(true);
					status=true;
					gameOver.setNext(false);
				}
				
			}
			else if(state==0) {
				if(intro.nextState()) {
					state=1;
				}
			}
			else if(state==1) {
				
				if(status)
				{
					thread = new Thread(gameScreen);
					
					character.setAllObject(gameScreen.getObject());
					character.setCamera(false);
					character.setWalk(true);
					map2.getCharacter().setX(320);
					map2.getCharacter().setY(500);
					gameScreen.setLoop(false);
					removeKeyListener(intro);
					setContentPane(gameScreen);
					addKeyListener(gameScreen);
					setVisible(false);
					setVisible(true);
					status=false;
					thread.start();
				}
				else if(gameScreen.nextMap()) {
					state=2;
					status=true;
					map2.getCharacter().setSideCharacter('d');
				}
				
			}
			else if(state == 2) {
				if(status) {
					
					thread = new Thread(map2);
					character.setAllObject(map2.getObject());
					character.setWalk(true);
					character.setCamera(false);
					map2.getCharacter().setX(10);
					map2.getCharacter().setY(400);
					
					removeKeyListener(gameScreen);
					gameScreen.setLoop(true);
					
					map2.setLoop(false);
					setContentPane(map2);
					addKeyListener(map2);
					setVisible(false);
					setVisible(true);
					status=false;
					thread.start();
				}
				else if(map2.previousMap()) 
				{
					gameScreen.getCharacter().setSideCharacter('a');
					character.setAllObject(gameScreen.getObject());
					character.setCamera(false);
					
					removeKeyListener(map2);
					gameScreen.getCharacter().setX(1150);
					thread = new Thread(gameScreen);
					
					map2.setLoop(true);
					gameScreen.setLoop(false);
					setContentPane(gameScreen);
					addKeyListener(gameScreen);
					setVisible(false);
					setVisible(true);
					thread.start();
					status=false;
					state=1;
				}
				else if(map2.nextMap()) {
					state=3;
					status=true;
					map3.getCharacter().setSideCharacter('d');
				}
				else if(map2.dead()) {
					map2.setLoop(true);
					removeKeyListener(map2);
					state=-1;
					status =true;
				}
				
			}
			else if(state ==3) {
				if(status) {	
					thread = new Thread(map3);
					
					character.setAllObject(map3.getObject());
					character.setCamera(false);
					
					map3.getCharacter().setX(10);
					map3.getCharacter().setY(300);
					removeKeyListener(map2);
					map2.setLoop(true);
					map3.setLoop(false);
					setContentPane(map3);
					addKeyListener(map3);
					setVisible(false);
					setVisible(true);
					status=false;
					thread.start();
				}
				else if(map3.previousMap()) {
					map2.getCharacter().setSideCharacter('a');
					character.setAllObject(map2.getObject());
					character.setCamera(false);
					
					removeKeyListener(map3);
					map2.getCharacter().setX(1150);
					thread = new Thread(map2);
					
					map3.setLoop(true);
					map2.setLoop(false);
					setContentPane(map2);
					addKeyListener(map2);
					setVisible(false);
					setVisible(true);
					thread.start();
					status=false;
					state=2;
				}
				else if(map3.nextMap()) {
					state=4;
					status=true;
					map3InTent.getCharacter().setSideCharacter('d');
				}
			}
			else if(state==4) {
				if(status) {	
					thread = new Thread(map3InTent);
					character.setAllObject(map3InTent.getObject());
					character.setCamera(true);
					
					
					map3InTent.getCharacter().setX(530);
					map3InTent.getCharacter().setY(640);
					
					removeKeyListener(map3);
					map3.setLoop(true);
					
					map3InTent.setLoop(false);
					setContentPane(map3InTent);
					addKeyListener(map3InTent);
					setVisible(false);
					setVisible(true);
					status=false;
					thread.start();
				}
				else if(map3InTent.previousMap()) {
					map3.getCharacter().setSideCharacter('s');
					character.setAllObject(map3.getObject());
					character.setCamera(false);
					thread = new Thread(map3);
					
					map3.getCharacter().setX(680);
					map3.getCharacter().setY(210);
					
					removeKeyListener(map3InTent);
					map3InTent.setLoop(true);
					
					map3.setLoop(false);
					setContentPane(map3);
					addKeyListener(map3);
					setVisible(false);
					setVisible(true);
					thread.start();
					status=false;
					state=3;
				}
				else if(map3InTent.isClean()) {
					map3InTent.removeMouseListener(map3InTent);
					state=5;
					status=true;
					map3InTent.getCharacter().setSideCharacter('d');
				}
			}
			else if(state==5) {
				if(status) {	
					thread = new Thread(map3InTentClean);
					character.setAllObject(map3InTentClean.getObject());
					character.setCamera(true);
					
					map3InTentClean.getCharacter().setX(528);
					map3InTentClean.getCharacter().setY(500);
					
					removeKeyListener(map3InTent);
					map3InTent.setLoop(true);
					
					map3InTentClean.setLoop(false);
					setContentPane(map3InTentClean);
					addKeyListener(map3InTentClean);
					setVisible(false);
					setVisible(true);
					status=false;
					thread.start();
				}
				else if(map3InTentClean.nextMap()) {
					state=6;
					status=true;
					map4.getCharacter().setSideCharacter('s');
				}
			}
			else if(state==6) {
				if(status) {
					thread = new Thread(map4);
					character.setAllObject(map4.getObject());
					character.setCamera(false);
					map4.getCharacter().setX(680);
					map4.getCharacter().setY(210);
					System.out.println(map4.getCharacter().getSpeedWalk());
					removeKeyListener(map3InTentClean);
					map3InTentClean.setLoop(true);
					
					map4.setLoop(false);
					setContentPane(map4);
					addKeyListener(map4);
					
					setVisible(false);
					setVisible(true);
					status=false;
					thread.start();
				}
				else if(map4.nextMap()) {
					state = 7;
					status = true;
					map5.getCharacter().setSideCharacter('a');
				}
			}
			else if(state ==7) {
				if(status) {
					thread = new Thread(map5);
					character.setAllObject(map5.getObject());
					character.setCamera(false);
					map5.getCharacter().setX(1150);
					map5.getCharacter().setY(400);
					
					removeKeyListener(map4);
					map4.setLoop(true);
					
					map5.setLoop(false);
					setContentPane(map5);
					addKeyListener(map5);
					setVisible(false);
					setVisible(true);
					status=false;
					thread.start();
				}
				else if(map5.nextMap()) {
					state = 8;
					status = true;
					dungeon0.getCharacter().setSideCharacter('w');
				}
				else if(map5.previousMap()) {
					map4.getCharacter().setSideCharacter('d');
					character.setAllObject(map4.getObject());
					character.setCamera(false);
					removeKeyListener(map5);
					map4.getCharacter().setX(10);
					map4.getCharacter().setY(430);
					thread = new Thread(map4);
					
					map5.setLoop(true);
					map4.setLoop(false);
					setContentPane(map4);
					addKeyListener(map4);
					setVisible(false);
					setVisible(true);
					thread.start();
					status=false;
					state=6;
				}
				else if(map5.Dead()) {
					System.out.println("DEADDDDD");
					map5.setLoop(true);
					removeKeyListener(map5);
					state=-1;
					status=true;
				}
				
			}
			else if(state==8) {
				if(status) {
					thread = new Thread(dungeon0);
					character.setAllObject(dungeon0.getObject());
					character.setCamera(true);
					
					dungeon0.getCharacter().setX(610);
					dungeon0.getCharacter().setY(550);
					removeKeyListener(map5);
					map5.setLoop(true);
					dungeon0.setLoop(false);
					setContentPane(dungeon0);
					addKeyListener(dungeon0);
					setVisible(false);
					setVisible(true);
					status=false;
					thread.start();
				}
				else if(dungeon0.nextMap()) {
					state = 9;
					status = true;
					dungeon1.getCharacter().setSideCharacter('d');
				}
				else if(dungeon0.previousMap()) {
					map5.getCharacter().setSideCharacter('s');
					character.setAllObject(map5.getObject());
					character.setCamera(false);
					
					removeKeyListener(dungeon0);
					map5.getCharacter().setX(980);
					map5.getCharacter().setY(210);
					thread = new Thread(map5);
					
					dungeon0.setLoop(true);
					map5.setLoop(false);
					setContentPane(map5);
					addKeyListener(map5);
					setVisible(false);
					setVisible(true);
					thread.start();
					status=false;
					state=7;
					
				}
			}
			else if(state ==9) {
				if(status) {
					thread = new Thread(dungeon1);
					character.setAllObject(dungeon1.getObject());
					character.setCamera(true);
					dungeon1.getCharacter().setX(330);
					dungeon1.getCharacter().setY(425);
					removeKeyListener(dungeon0);
					dungeon0.setLoop(true);
					dungeon1.setLoop(false);
					setContentPane(dungeon1);
					addKeyListener(dungeon1);
					setVisible(false);
					setVisible(true);
					status=false;
					thread.start();
				}
				else if(dungeon1.previousMap()) {
					if(!dungeon1.getTalk()) {
						dungeon0.getCharacter().setSideCharacter('a');
						character.setAllObject(dungeon0.getObject());
						character.setCamera(true);
						
						
						removeKeyListener(dungeon1);
						dungeon0.getCharacter().setX(900);
						dungeon0.getCharacter().setY(355);
						thread = new Thread(dungeon0);
						
						dungeon1.setLoop(true);
						dungeon0.setLoop(false);
						setContentPane(dungeon0);
						addKeyListener(dungeon0);
						setVisible(false);
						setVisible(true);
						thread.start();
						status=false;
						state=8;
					}
					else {
						state = 10;
						status = true;
						dungeon2.getCharacter().setSideCharacter('a');
					}
				}
				else if(dungeon1.nextMap()){
					state=11;
					status = true;
					outDungeon.getCharacter().setSideCharacter('d');
				}
				
			}else if(state ==10) {
					if(status) {
						thread = new Thread(dungeon2);
						character.setAllObject(dungeon2.getObject());
						character.setCamera(true);
						
						
						dungeon2.getCharacter().setX(824);
						dungeon2.getCharacter().setY(315);
						removeKeyListener(dungeon1);
						dungeon1.setLoop(true);
						dungeon2.setLoop(false);
						setContentPane(dungeon2);
						addKeyListener(dungeon2);
						setVisible(false);
						setVisible(true);
						status=false;
						thread.start();
					}
					else if(dungeon2.previousMap()) {
						if(dungeon2.isItem()) {
							dungeon1.getCharacter().setSideCharacter('d');
							character.setAllObject(dungeon1.getObject());
							character.setCamera(true);
							
							
							removeKeyListener(dungeon2);
							dungeon1.getCharacter().setX(330);
							dungeon1.getCharacter().setY(425);
							thread = new Thread(dungeon1);
							
							dungeon2.setLoop(true);
							dungeon1.setLoop(false);
							setContentPane(dungeon1);
							addKeyListener(dungeon1);
							setVisible(false);
							setVisible(true);
							thread.start();
							status=false;
							state=9;
						}
						else {
							if(!dungeon1.getTalk2())
								dungeon1.setState2();
								dungeon1.getCharacter().setSideCharacter('d');
								character.setAllObject(dungeon1.getObject());
								character.setCamera(true);
								
								
								removeKeyListener(dungeon2);
								dungeon1.getCharacter().setX(330);
								dungeon1.getCharacter().setY(425);
								thread = new Thread(dungeon1);
								
								dungeon2.setLoop(true);
								dungeon1.setLoop(false);
								setContentPane(dungeon1);
								addKeyListener(dungeon1);
								setVisible(false);
								setVisible(true);
								thread.start();
								status=false;
								state=9;
							
						}
					}
			}
			else if(state ==11) {
				if(status) {
					thread = new Thread(outDungeon);
					character.setAllObject(outDungeon.getObject());
					character.setCamera(false);
					
					outDungeon.getCharacter().setX(300);
					outDungeon.getCharacter().setY(500);
					removeKeyListener(dungeon1);
					dungeon1.setLoop(true);
					outDungeon.setLoop(false);
					setContentPane(outDungeon);
					addKeyListener(outDungeon);
					setVisible(false);
					setVisible(true);
					thread.start();
					status=false;
				}
				else if(outDungeon.nextMap()) {
					state =12;
					status =true;
					
				}
			}
			else if(state ==12) {
				if(status) {
					thread = new Thread(home);
					character.setAllObject(home.getObject());
					character.setCamera(false);
					
					
					home.getCharacter().setSideCharacter('d');
					home.getCharacter().setX(150);
					home.getCharacter().setY(200);
					removeKeyListener(outDungeon);
					outDungeon.setLoop(true);
					home.setLoop(false);
					setContentPane(home);
					addKeyListener(home);
					setVisible(false);
					setVisible(true);
					thread.start();
					status=false;
				}
				else if(home.previousMap()) {
					outDungeon.getCharacter().setSideCharacter('a');
					character.setAllObject(outDungeon.getObject());
					character.setCamera(false);
					
					
					removeKeyListener(home);
					outDungeon.getCharacter().setX(1150);
					outDungeon.getCharacter().setY(400);
					thread = new Thread(outDungeon);
					
					home.setLoop(true);
					outDungeon.setLoop(false);
					setContentPane(outDungeon);
					addKeyListener(outDungeon);
					setVisible(false);
					setVisible(true);
					thread.start();
					status=false;
					state=11;
				}
				else if(home.nextMap()) {
					state=13;
					status=true;
				}
				
			}
			else if(state ==13) {
				if(status) {
					ending.start();	
					setContentPane(ending.getJPanel());
					removeKeyListener(home);
					home.setLoop(true);
					setVisible(false);
					setVisible(true);
//					thread.start();
					status=false;
				}
			}
			
			if(i>600) {
				i=0;
				try {
					p.playMusic("music/Christmas.wav");
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			try {
				thread2.sleep(200);
			} catch (InterruptedException e) {
				System.out.println("thread 2 error");
			}
			
		}
	}
	
	
	

}
