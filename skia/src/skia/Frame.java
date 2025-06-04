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
	int height = 828;
	boolean touching = false;
	boolean holding;
	Timer t;
	
	Boolean start = true;
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
	Counter touched; 
	int count = 0;
	Font joystix;
	int min = 0;
	int tens = 0;
	int sec = 0;
	int timer = 300;
	long time = System.currentTimeMillis();
	static int hiScore;
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		back.paint(g);
		boolean colliding=false;
	
		g.setColor(Color.white);
		g.setFont(joystix);
		g.setFont(g.getFont().deriveFont(Font.PLAIN,32F));
		
		g.drawString("TIME " + min + ":" + tens + sec + "   SCORE: " + reg.score + "  HIGH SCORE: " + hiScore, 5, 35);
		
		
		
		
		
		
		chef.move();
		
		for(Counter i : counters) {
			
			if(i != null) {
				i.paint(g);
				if(chef.collided(i)) {
					colliding = true;
				}
				touching(i, colliding);
			}
		}
		
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
		
		for(Oven i : ovens) {
			i.paint(g);
			if(chef.collided(i)) {
				colliding = true;
				
			}
			if(i.fireCheck()) {
				//System.out.println("FIREEEEEEEE OH NO");
			}
			touching(i, colliding);
		}
		
		for(Mixer i : mixers) {
			i.paint(g);
			if(chef.collided(i)) {
				colliding = true;
			}
			touching(i, colliding);
		}
		
		sink.paint(g);
		
		sink.bar.paint(g);
		
		if(chef.collided(sink)) {
			colliding = true;
			
		}
		ret.paint(g);
		
		reg.paint(g);

		Graphics2D g2 = (Graphics2D) g;
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		
		
		if(chef.collided(reg) || chef.collided(ret)) {
			colliding = true;
			
		}
		
		
		
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
		Plate temp = reg.remove();
		if(temp != null) {
			
			ret.plates.add(temp);
			
		}
		//timing
		if(start) {
			min = timer/60;
			tens = timer%60/10;
			sec = timer%60%10;
			
			if((int) System.currentTimeMillis()/1000 == ((int) time/1000 + 1)) {
				timer--;
				time = System.currentTimeMillis();	
			}
		}	
		if(timer == 0) {
			min = timer/60;
			tens = timer%60/10;
			sec = timer%60%10;
			start = false;
			try {
				FileWriter myWriter = new FileWriter("saveData.txt");
				g.setColor(Color.black);
				g.fillRect(40, 125, 900, 620);
				g.setColor(new Color(146,100,58));
				g.fillRect(65, 150, 850, 570);
				g.setColor(Color.white);
				g.setFont(g.getFont().deriveFont(Font.PLAIN,65F));
				g.drawString("Times Up!", 100, 250);
				if(reg.score > hiScore) {
					myWriter.write(reg.score + "");
					myWriter.close();
					g.drawString("New High Score!", 100, 350);
					g.drawString("Score: " + reg.score, 350, 650);
				}else {
					myWriter.write(hiScore + "");
					myWriter.close();
					
					g.drawString("Nice Try, Chef!", 100, 350);
					g.drawString("Score: " + reg.score, 350, 550);
				}
				
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
				
		}
		
	
	}
	
	
	
	public Frame() {
		JFrame f = Runner.f;
		f.setSize(new Dimension(width, height));
		f.setBackground(Color.white);
		f.add(this);
		f.setResizable(false);
		f.addMouseListener(this);
		f.addKeyListener(this);
		start = true;
		
		
		init(counters);
		init(ovens);
		init(mixers);
		
		try {
			joystix = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/font/joystix monospace.otf"));
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon("torch.png").getImage(),
				new Point(0,0),"custom cursor"));	*/
		
		
		t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
	}
	
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
		//c[10] = new Counter(20 + 80*6 + 80*2, 700, 1);
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
	
	public void init(Oven[] o) {
		o[0] = new Oven(900 - 80*2, 140, 0);
		o[1] = new Oven(900, 140 + 80, 90);
		o[2] = new Oven(900, 140 + 80*3, 90);
	}
	
	public void init(Mixer[] m) {
		m[0] = new Mixer(20 + 80, 700);
		m[1] = new Mixer(20 + 80*3, 700);
		m[2] = new Mixer(20 + 80*5, 700);
	}
	
	public void touching(Counter i, Boolean colliding) {
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
		
		if(e.getKeyChar() == ' ' && chef.touching(sink) && sink.dirtyPlates.size() > 0 && sink.dirtyPlates.get(0) instanceof Plate && chef.obj.empty) {
			
			
			//play the washing animation
			sink.timer++;
			sink.washing = true;
			if(sink.timer == 100) {
				sink.timer = 0;
				sink.cleanPlates.add(sink.dirtyPlates.remove(sink.dirtyPlates.size()-1));
				sink.cleanPlates.get(sink.cleanPlates.size()-1).isDirty = false;
				if(sink.dirtyPlates.size() == 0) {
					sink.washing = false;
				}
			}
			
		}
		
		if(e.getKeyChar() == ' ' && touching && chef.obj instanceof Extinguisher && touched instanceof Oven ){
			if(((Oven) touched).fire) {
				((Oven) touched).extinguish();
			}
		}
		
		if(e.getKeyChar() == 'e' && chef.touching(reg) && chef.obj instanceof Plate) {
			
			Plate temp = (Plate) chef.obj;
			
			if(temp.in.contains("cake") && temp.in.contains("frosted")) {
				chef.obj = new Object();
				reg.sell(temp);
				
			}
			
		}
		
		if(e.getKeyChar() == 'e' && chef.touching(ret) && chef.obj.empty && ret.plates.size() > 0) {
			
			
			chef.obj = ret.plates.remove(ret.plates.size()-1);
			
		}
		
		
		if(e.getKeyChar() == 'e' && touching) {
			
			//System.out.println(touched.getClass().getName());dw
			
			if(touched.getClass().getName().equals("skia.Counter")) {
				
				if(chef.obj instanceof Bowl && touched.obj instanceof Plate && !((Plate) touched.obj).isDirty) {
					if(chef.obj.in.contains("cake") && touched.obj.in.size() == 0) {
						touched.obj.add("cake");
						if(chef.obj.in.contains("burnt")) {
							touched.obj.add("burnt");	
						} else if(chef.obj.in.contains("strawberrycake")) {
							touched.obj.add("strawberrycake");
						}
						chef.obj = new Bowl();
					} else if (chef.obj.in.contains("frosting") && !touched.obj.in.contains("frosted") && touched.obj.in.contains("cake")) {
						touched.obj.add("frosted");
						if(chef.obj.in.contains("strawberryfrosting")) {
							touched.obj.add("strawberryfrosted");
						}
						chef.obj = new Bowl();
					} else if (chef.obj.in.contains("frosting") && !(touched.obj.in.contains("strawberry") || touched.obj.in.contains("frosted") || touched.obj.in.contains("cake")) && chef.obj.in.size() == 1)  {
						touched.obj.add("strawberry");
						chef.obj = new Bowl();
					}
					
				} else if (chef.obj instanceof Plate && touched.obj instanceof Bowl && !((Plate) chef.obj).isDirty) {
					if(touched.obj.in.contains("cake") && chef.obj.in.size() == 0) {
						chef.obj.add("cake");
						if(chef.obj.in.contains("burnt")) {
							chef.obj.add("burnt");	
						} else if(chef.obj.in.contains("strawberrycake")) {
							chef.obj.add("strawberrycake");
						}
						touched.obj = new Bowl();
					} else if (touched.obj.in.contains("frosting") && !chef.obj.in.contains("frosted") && chef.obj.in.contains("cake")) {
						chef.obj.add("frosted");
						if(chef.obj.in.contains("strawberryfrosting")) {
							chef.obj.add("strawberryfrosted");
						}
						touched.obj = new Bowl();
					} else if (touched.obj.in.contains("frosting") && !(chef.obj.in.contains("strawberry") || chef.obj.in.contains("frosted") || chef.obj.in.contains("cake")) && touched.obj.in.size() == 1)  {
						chef.obj.add("strawberry");
						touched.obj = new Bowl();
					}
					
				}
				
				else {
					
					Object temp = touched.obj;
					touched.obj = chef.obj;
					chef.obj = temp;
					
					
				}
				
				

			} else if (touched.getClass().getName().equals("skia.Oven") && chef.obj.plate == null) {
				if(!((Oven) touched).fire) {
					Object temp = touched.obj;
					touched.obj = chef.obj;
					chef.obj = temp;
					if(temp.burnt) {
						((Oven)touched).extinguished = false;
					}
					((Oven) touched).bar.on = false;
				}
			} if (touched.getClass().getName().equals("skia.Mixer") && chef.obj.plate == null) {
				if(chef.obj.mixed) {
					Object temp = touched.obj;
					touched.obj = chef.obj;
					chef.obj = temp;
				}
				
			} else if (touched.getClass().getName().equals("skia.Box")) {
				if(chef.obj.bowl != null) {
					Box temp = (Box) touched;
					chef.obj.add(temp.getType());
				} else if (((Box) touched).getType().equals("strawberry")) {
					if(chef.obj.plate != null && chef.obj.in.contains("cake") && chef.obj.in.contains("frosted")) {
						chef.obj.add("strawberry");
					}
				}
			} else if (touched instanceof Trashcan) {
				((Trashcan) touched).throwOut(chef.obj);
			}
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		//System.out.println(e.getKeyChar() + " " + e.getKeyCode() + " " + (e.getKeyCode() == 38));
		
	
		
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
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	
		
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
