package skia;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements MouseListener, ActionListener, KeyListener {
	
	int width = 1000;
	int height = 1006;
	boolean touching = false;
	boolean holding;
	Timer t;
	
	Background back = new Background();
	Counter[] counters = new Counter[36];
	Box milk = new Box(20 + 80*6 + 80*5, 700-80*2, 0);
	Box egg = new Box(20 + 80*6 + 80*5, 700-80, 1);
	Box sugar = new Box(20, 140 + 80*3, 3);
	Box flour = new Box(20, 140 + 80*2, 2);
	Box strawberry = new Box(20 + 80*6 + 80*2, 700, 4);
	Oven[] ovens = new Oven[3];
	Sink sink = new Sink(20 + 80*5, 140 + 80*4);
	Register reg = new Register(5*4+80, 35*4);
	ReturnCounter ret = new ReturnCounter(5*4+240, 35*4);
	Mixer[] mixers = new Mixer[3];
	Chef chef = new Chef();
	ArrayList<Order> orders = new ArrayList<Order>();
	int lastOrder;
	ArrayList<Customer> customers = new ArrayList<Customer>();
	SimpleAudioTester audio = new SimpleAudioTester();

	
	Counter touched; 
	int count = 0;
	Font joystix;
	int min = 0;
	int tens = 0;
	int sec = 0;
	int timer = 300;
	long time = System.currentTimeMillis();
	static int hiScore;
	int frame = 0;
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		back.paint(g);
		boolean colliding=false; //resets collision 
	
		chef.move();
		
		//stops the washing noise when not washing
		if(!sink.washing) {
			audio.stopSound("waterwashing");
		}
		//check each counter to see if the chef is colliding or touching it
		//colliding makes it so the chef can't run into it
		//touching makes it so the chef is interacting with one counter
		for(Counter i : counters) {
			
			if(i != null) {
				i.paint(g);
				if(chef.collided(i)) {
					colliding = true;
				}
				touching(i, colliding);
			}
		}
		
		//check ingredient bin collision and interaction
		milk.paint(g);
		if(chef.collided(milk)) {
			colliding = true;
			
		}
		touching(milk, colliding);
		
		egg.paint(g);
		if(chef.collided(egg)) {
			colliding = true;
			
		}
		touching(egg, colliding);
		
		sugar.paint(g);
		if(chef.collided(sugar)) {
			colliding = true;
			
		}
		touching(sugar, colliding);
		
		flour.paint(g);
		if(chef.collided(flour)) {
			colliding = true; 
			
		}
		touching(flour, colliding);
		
		strawberry.paint(g);
		if(chef.collided(strawberry)) {
			colliding = true;
			
		}
		touching(strawberry, colliding);
		
		//check oven collision and interaction
		for(Oven i : ovens) {
			i.paint(g);
			if(chef.collided(i)) {
				colliding = true;
				
			}
			touching(i, colliding);
		}
		
		//check mixer collision and interaction
		for(Mixer i : mixers) {
			i.paint(g);
			if(chef.collided(i)) {
				colliding = true;
			}
			touching(i, colliding);
		}
		
		//paint sink and sink progress bar
		sink.paint(g);
		
		sink.bar.paint(g);
		
		if(chef.collided(sink)) {
			colliding = true;
			
		}
		
		//paint return counter
		ret.paint(g);
		
		touching(ret, colliding);
		
		reg.paint(g);
		

		Graphics2D g2 = (Graphics2D) g;
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		
		//checking collision with register and return counter
		if(chef.collided(reg) || chef.collided(ret)) {
			colliding = true;
			
		}
		
		
		//move the chef off of the counters/appliances if they are colliding
		if(!colliding) {
			chef.paint(g);
		}else {
			if(chef.dir == 90) {
				chef.x-=10;
			}if(chef.dir == 0) {
				chef.y+=10;
			}if (chef.dir == 180) {
				chef.y -=10;
			}if (chef.dir == 270) {
				chef.x +=10;
			}
			
			chef.vx = 0;
			chef.paint(g);
			colliding = !colliding;
		}
		
		//move plates from register to return counter
		Plate temp = reg.remove();
		if(temp != null) {
			
			ret.plates.add(temp);
			
		}
	
		for(int i = 0; i < customers.size(); i++) {
			if(customers.get(i).move() && i+1 < customers.size()) {
				customers.get(i+1).move = true;
			}
		}
		
		if(customers.get(customers.size()-1).x <= 1000) {
			customers.add(new Customer(customers.get(customers.size()-1).x+80, 35*4-70));
			//System.out.println("new customer");
		}
		
		//System.out.println(customers.get(customers.size()-1).x);
		
		if(customers.get(0).x-customers.get(0).i*5 <= -60) {
			customers.remove(0);
			//System.out.println("gone");
		}
		
		/*if(customers.get(0).move = false) {
			customers.get(0).img = customers.get(0).rotate;
		} else {
			customers.get(0).img = customers.get(0).save;
		}*/
		
		for(Customer i : customers) {
			i.paint(g);
		}

		//timing
		if(Runner.start) {
			//timer will only go down every second
			if((int) System.currentTimeMillis()/1000 == ((int) time/1000 + 1)) {
				timer--;
				time = System.currentTimeMillis();	
			}
			//separates each place in the timer for visual representation
			min = timer/60;
			tens = timer%60/10;
			sec = timer%60%10;
			
			//draws the timer and score at the top
			g.setColor(Color.white);
			g.setFont(joystix);
			g.setFont(g.getFont().deriveFont(Font.PLAIN,32F));
			g.drawString("TIME " + min + ":" + tens + sec + "    SCORE:" + reg.score + "    HISCORE:" + hiScore, 5, 35);
		}	
		//game finishes
		if(timer == 0) {
			if(!audio.sounds.containsKey("exitsong")) {
				Runner.audio.stopMusic();
				Runner.audio.clearAllSound();
				audio.playSound("exitsong");
			}
			
			min = 0;
			tens = 0;
			sec = 0;
			timer--;
		}
		if(timer == -1) {
			try {
				FileWriter myWriter = new FileWriter("data.txt");
				g.setColor(Color.black);
				g.fillRect(40, 125, 900, 620);
				
				g.setColor(new Color(146,100,58));
				g.fillRect(65, 150, 850, 570);
				g.setColor(Color.black);
				g.fillRect(115, 400, 360, 150);
				g.fillRect(530, 400, 360, 150);
				g.setColor(new Color(196,150,108));
				g.fillRect(130, 415, 330, 120);
				g.fillRect(545, 415, 330, 120);
				g.setColor(Color.black);
				g.setFont(g.getFont().deriveFont(Font.PLAIN,80F));
				g.drawString("home", 165, 500);
				g.drawString("Quit", 580, 500);
				g.setColor(Color.white);
				g.setFont(g.getFont().deriveFont(Font.PLAIN,65F));
				g.drawString("times Up!", 114, 250);
				
				if(reg.score > hiScore) {
					myWriter.write(reg.score + "");
					myWriter.close();
					g.drawString("New High Score!", 115, 350);
					g.drawString("Score: " + reg.score, 115, 650);
				}else {
					myWriter.write(hiScore + "");
					myWriter.close();
					
					g.drawString("Nice Try, Chef!", 115, 350);
					g.drawString("Score: " + reg.score, 115, 650);
				}
				
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
			Runner.start = false;
		}
		
		if(timer%30 == 0 && Math.abs(timer-lastOrder) > 2 && orders.size() < 4) {
			orders.add(new Order());
			lastOrder = timer;
		}
		
		
		//add orders if none
		if(orders.size() == 0) {
			orders.add(new Order());
			lastOrder = timer;
		}
		
		//paint orders
		for(int i = 0; i < orders.size(); i++) {
			orders.get(i).paint(g, 20 + 210*i, 140 + 80*8+5+4*3);
		}
		frame++; //tracking variable of the frame
	}
	
	
	
	public Frame() {
		JFrame f = Runner.f;
		f.setSize(new Dimension(width, height));
		f.setBackground(Color.white);
		f.add(this);
		f.setResizable(false);
		f.addMouseListener(this);
		f.addKeyListener(this);
		
		
		//initialize counters, ovens, mixers, customers, and orders
		init(counters);
		init(ovens);
		init(mixers);
		init(customers);
		orders.add(new Order());
		lastOrder = 300;
		
		//creates the good font for the text
		try {
			joystix = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/font/joystix monospace.otf"));
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
	}
	//initializes the counters to their set locations
	public void init(Counter[] c) {
		
		c[0] = new Counter(5*4, 35*4, 1);
		c[1] = new Counter(20, 140 + 80, 1);
		c[2] = new Counter(20, 140 + 80*4, 1);
		c[3] = new Counter(20, 140 + 80*5, 1);
		c[4] = new Counter(20, 140 + 80*6, 1);
		c[5] = new Counter(20, 140 + 80*7, 1);
		c[6] = new Counter(20 + 80*2, 700, 1);
		c[7] = new Counter(20 + 80*4, 700, 1);
		c[8] = new Counter(20 + 80*6, 700, 1);
		c[9] = new Counter(20 + 80*6 + 80, 700, 1);
		c[11] = new Counter(20 + 80*6 + 80*3, 700, 1);
		c[12] = new Counter(20 + 80*6 + 80*4, 700, 1);
		c[13] = new Counter(20 + 80*6 + 80*5, 700, 1);
		c[14] = new Counter(900, 140 + 80*4, 1);
		c[15] = new Counter(900, 140 + 80*2, 1);
		c[16] = new Counter(900, 140, 1);
		c[17] = new Counter(900 - 80, 140, 1);
		c[18] = new Counter(900 - 80*3, 140, 1);
		c[19] = new Counter(900 - 80*4, 140, 1);
		c[20] = new Trashcan(900 - 80*5, 140, 1);
		c[21] = new Counter(900 - 80*6, 140, 1);
		c[22] = new Counter(900 - 80*7, 140, 1);
    
		c[23] = new Counter(20 + 80*3, 140 + 80*2, 0);
		c[24] = new Counter(20 + 80*3, 140 + 80*3, 0);
		c[25] = new Counter(20 + 80*3, 140 + 80*4, 0);
		c[26] = new Counter(20 + 80*3, 140 + 80*5, 0);
		c[27] = new Counter(20 + 80*4, 140 + 80*3, 0);
		c[28] = new Counter(20 + 80*4, 140 + 80*4, 0);
		c[29] = new Counter(20 + 80*5, 140 + 80*3, 0);
		c[30] = new Counter(20 + 80*6, 140 + 80*3, 0);
		c[31] = new Counter(20 + 80*7, 140 + 80*3, 0);
		c[32] = new Counter(20 + 80*7, 140 + 80*4, 0);
		c[33] = new Counter(20 + 80*8, 140 + 80*2, 0);
		c[34] = new Counter(20 + 80*8, 140 + 80*3, 0);
		c[35] = new Counter(20 + 80*8, 140 + 80*4, 0);
		c[10] = new Counter(20 + 80*8, 140 + 80*5, 0);
    
		//placing the objects in their spawning positions
		c[23].obj = new Bowl();
		c[29].obj = new Bowl();
		c[30].obj = new Bowl();
		c[33].obj = new Bowl();
		c[10].obj = new Plate();
		c[26].obj = new Plate();
		c[28].obj = new Plate();
		c[32].obj = new Plate();
		c[22].obj = new Extinguisher();
	}
	
	//initializes the ovens to their set locations
	public void init(Oven[] o) {
		o[0] = new Oven(900 - 80*2, 140, 0, audio);
		o[1] = new Oven(900, 140 + 80, 90, audio);
		o[2] = new Oven(900, 140 + 80*3, 90, audio);
	}
	
	//initializes the mixer to their set locations
	public void init(Mixer[] m) {
		m[0] = new Mixer(20 + 80, 700, audio);
		m[1] = new Mixer(20 + 80*3, 700, audio);
		m[2] = new Mixer(20 + 80*5, 700, audio);
	}
	
	//initializes the customers to their set locations
	public void init(ArrayList<Customer> c) {
		for(int i = 0; i < 1080; i+=80) {
			c.add(new Customer(190+i, 35*4-70));
		}
	}
	
	public void touching(Counter i, Boolean colliding) {
		//if the chef is touching counter, check which one is closer
		//set touched to the closer counter
		//makes it so that interactions will be with the closest counter to chef
		if(chef.touching(i)) {
			if(touching) {
				if(chef.dir == 0 || chef.dir == 180) {
					if((Math.abs(chef.x-10 - i.x)) <= Math.abs(chef.x-10 - touched.x)) {
						touched = i;
					}
				} else {
					if((Math.abs(chef.y-10 - i.y)) <= Math.abs(chef.y-10 - touched.y)) {
						touched = i;
					}
				}
			}else {
				touched = i;
			}
			touching = true;
		}else if(touched == i){
			touched = null;
			touching = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method st
		
		//interactions only work when game is running
		//you shouldn't be able to move when the timer is over
		if(Runner.start) {
			//move chef and change direction that its facing
			if (e.getKeyChar() == 'w' || e.getKeyCode() == 38) {
				chef.setVY(-10);
				chef.setVX(0);
				chef.dir = 0;
			} else if (e.getKeyChar() == 'a' || e.getKeyCode() == 37) {
				chef.setVX(-10);
				chef.setVY(0);
				chef.dir = 270;
			} else if (e.getKeyChar() == 's' || e.getKeyCode() == 40) {
				chef.setVY(10);
				chef.setVX(0);
				chef.dir = 180;
			} else if (e.getKeyChar() == 'd' || e.getKeyCode() == 39) {
				chef.setVX(10);
				chef.setVY(0);
				chef.dir = 90;
			}
			
			//get cleaned dishes from sink/put dirty dishes in sink
			if(e.getKeyChar() == 'e' && chef.touching(sink)  ) {
				if(chef.obj instanceof Plate) {
					Plate temp = (Plate) chef.obj;
					if(temp.isDirty()) {
						chef.obj = new Object();
						sink.dirtyPlates.add(temp);
					}
				}else if(chef.obj.empty && sink.cleanPlates.size() > 0) {
					chef.obj = sink.cleanPlates.remove(0);
				}
				
			}
			
			//clean dishes in sink
			//only works when there are actually dirty dishes in the sink
			if(e.getKeyChar() == ' ' && chef.touching(sink) && sink.dirtyPlates.size() > 0 && sink.dirtyPlates.get(0) instanceof Plate && chef.obj.empty) {
				sink.timer++;
				sink.washing = true;
				audio.loopSound("waterwashing");
				if(sink.timer == 100) {
					sink.timer = 0;
					sink.cleanPlates.add(sink.dirtyPlates.remove(sink.dirtyPlates.size()-1));
					sink.cleanPlates.get(sink.cleanPlates.size()-1).isDirty = false;
					if(sink.dirtyPlates.size() == 0) {
						sink.washing = false;
					}
				}
				
			}
			
			//put out fires with extinguisher
			if(e.getKeyChar() == ' ' && touching && chef.obj instanceof Extinguisher){
				if(touched instanceof Oven && ((Oven) touched).fire) {
					((Oven) touched).extinguish();
				}
				if(touched instanceof Mixer && ((Mixer) touched).fire){
					((Mixer)touched).extinguish();
				}
			} 
			
			//sell plates
			if(e.getKeyChar() == 'e' && chef.touching(reg) && chef.obj instanceof Plate) {
				
				Plate temp = (Plate) chef.obj; //can access plate variables/methods easier
				
				if(temp.in.contains("cake") && temp.in.contains("frosted")) { //making sure its a complete cake
					chef.obj = new Object();
					if(temp.in.contains("burnt") || temp.in.contains("green")) {
						reg.ew(temp);
					} else {
						Boolean bad = true;
						for(int i = 0; i < orders.size(); i++) {
							
							//check if cake matches an order
							if(temp.in.contains("strawberrycake") == orders.get(i).cake
									&& temp.in.contains("strawberryfrosted") == orders.get(i).frosting
									&& temp.in.contains("strawberry") == orders.get(i).topping) {
								reg.sell(temp, audio);
								orders.remove(i);
								
								//move customers
								customers.get(0).leave = true;
								customers.get(0).move = true;
								bad = false;
								break;
							}
						}
						//if wrong cake, score goes down
						if(bad) {
							reg.ew(temp);
						}
					}
					
				}
				
			}
			
			//get dirty dish from return counter
			if(e.getKeyChar() == 'e' && touching && touched.equals(ret) && chef.obj.empty && ret.plates.size() > 0) {
				chef.obj = ret.plates.remove(ret.plates.size()-1);
			}
		
			//mute button (if needed)
			if(e.getKeyChar() == 'm') {
				audio.clearAllSound();
			}
			
			if(e.getKeyChar() == 'e' && touching) {
				
				//stops the alarm from going off when something is taken out of the mixer/oven
				if(chef.obj.plate == null && chef.obj.bowl == null && (touched.obj.bowl != null || touched.obj.plate != null)) {
					audio.stopSound("alarmforoven");
				}
				//stops the alarm when swapping bowls
				if(chef.obj instanceof Bowl && touched.obj.bowl != null) {
					audio.stopSound("alarmforoven");
				}
				
				//touching counters
				if(touched.getClass().getName().equals("skia.Counter") && !(touched instanceof Mixer)) {
					
					//transfer ingredients from bowl to plate or plate to bowl
					//also combines ingredients together
					if(chef.obj instanceof Bowl && touched.obj instanceof Plate && !((Plate) touched.obj).isDirty) {
						if(chef.obj.in.contains("cake") && touched.obj.in.size() == 0) {
							touched.obj.add("cake");
							if(chef.obj.in.contains("strawberrycake")) {
								touched.obj.add("strawberrycake");	
							} else if(chef.obj.in.contains("burnt")) {
								touched.obj.add("burnt");
							} else if(chef.obj.in.contains("green")) {
								touched.obj.add("green");
							}
							chef.obj = new Bowl();
						} else if (chef.obj.in.contains("frosting") && !touched.obj.in.contains("frosted") && touched.obj.in.contains("cake")) {
							touched.obj.add("frosted");
							if(chef.obj.in.contains("strawberryfrosting")) {
								touched.obj.add("strawberryfrosted");
							}
							chef.obj = new Bowl();
						} else if (chef.obj.in.contains("strawberry") && !(touched.obj.in.contains("strawberry") || touched.obj.in.contains("frosted") || touched.obj.in.contains("cake")) && chef.obj.in.size() == 1)  {
							touched.obj.add("strawberry");
							chef.obj = new Bowl();
						}
						
					} else if (chef.obj instanceof Plate && touched.obj instanceof Bowl && !((Plate) chef.obj).isDirty) {
						if(touched.obj.in.contains("cake") && chef.obj.in.size() == 0) {
							chef.obj.add("cake");
							if(touched.obj.in.contains("burnt")) {
								chef.obj.add("burnt");	
							} else if(touched.obj.in.contains("green")) {
								chef.obj.add("green");
							} else if(touched.obj.in.contains("strawberrycake")) {
								chef.obj.add("strawberrycake");
							}
							touched.obj = new Bowl();
						} else if (touched.obj.in.contains("frosting") && !chef.obj.in.contains("frosted") && chef.obj.in.contains("cake")) {
							chef.obj.add("frosted");
							if(touched.obj.in.contains("strawberryfrosting")) {
								chef.obj.add("strawberryfrosted");
							}
							touched.obj = new Bowl();
						} else if (touched.obj.in.contains("strawberry") && !(chef.obj.in.contains("strawberry") || chef.obj.in.contains("frosted") || chef.obj.in.contains("cake")) && touched.obj.in.size() == 1)  {
							chef.obj.add("strawberry");
							touched.obj = new Bowl();
						}
						
					}
					
					//trade objects
					else {
						Object temp = touched.obj;
						touched.obj = chef.obj;
						chef.obj = temp;
					}
					
					
				//interact with oven only if youre not holding a  plate
				} else if (touched.getClass().getName().equals("skia.Oven") && chef.obj.plate == null) {
					
					if(!((Oven) touched).fire) { //cannot take things out when the oven is on fire
						Object temp = touched.obj;
						touched.obj = chef.obj;
						chef.obj = temp;
						if(temp.burnt) {
							((Oven)touched).extinguished = false;
						}
						((Oven) touched).bar.on = false; //turns off progress bar
					}
				//interact with mixer only if youre not holding a plate
				}else if (touched.getClass().getName().equals("skia.Mixer") && chef.obj.plate == null) {
				
					if(!((Mixer) touched).fire) { //cannot take things out when the mixer is on fire
						Object temp = touched.obj;
						touched.obj = chef.obj;
						chef.obj = temp;
						if(temp.in.contains("green")) {
							((Mixer)touched).extinguished = false;
						}
						((Mixer) touched).bar.on = false;
					}
					
	      //interact with ingredient bins	
				} else if (touched.getClass().getName().equals("skia.Box")) {
					if(chef.obj.bowl != null) { // can only interact with other ingredients when you have a bowl
						Box temp = (Box) touched;
						chef.obj.add(temp.getType());
					} else if (((Box) touched).getType().equals("strawberry")) {
						if(chef.obj.plate != null && chef.obj.in.contains("cake") && chef.obj.in.contains("frosted")) {
							chef.obj.add("strawberry");
						}
					}
				//interact with trash
				} else if (touched instanceof Trashcan) {
					if(chef.obj instanceof Bowl) { 
						chef.obj = new Bowl(); //replaces the old bowl with a completely new one
					} else if(chef.obj instanceof Plate && !((Plate)chef.obj).isDirty()) {
						chef.obj = new Plate(); //replaces the old plate with a completely new one
					}
				}
			}
		}
	}



	@Override
	public void keyReleased(KeyEvent e) {
		
		//stops sound and bar if the plates are no longer being washing
		if(e.getKeyChar() == ' ') {
			sink.washing = false;
		}
		
		//stop moving
		
		if (e.getKeyChar() == 'w' || e.getKeyCode() == 38) {
			chef.setVY(0);
		} else if (e.getKeyChar() == 'a' || e.getKeyCode() == 37) {
			chef.setVX(0);
		} else if (e.getKeyChar() == 's' || e.getKeyCode() == 40) {
			chef.setVY(0);
		} else if (e.getKeyChar() == 'd' || e.getKeyCode() == 39) {
			chef.setVX(0);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(Runner.start) {
			repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		//click buttons in end menu
		if(!Runner.start) {
			//home button
			if(e.getX() >= 122 && e.getX() <= 482 && e.getY() >= 430 && e.getY() <= 580) {
				Runner.f.setVisible(false);
				Runner.f.dispose();
				Runner.frame = null;
				Runner r = new Runner();
			}
			//quit button
			if(e.getX() >= 538 && e.getX() <= 898 && e.getY() >= 430 && e.getY() <= 580) {
				Runner.f.setVisible(false);
				System.exit(0);
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
