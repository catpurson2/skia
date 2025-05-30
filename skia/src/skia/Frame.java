package skia;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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
import java.net.URL;

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

	Background back = new Background();
	Counter[] counters = new Counter[36];
	Oven[] ovens = new Oven[3];
	Sink sink = new Sink(20 + 80*5, 140 + 80*4);
	Register reg = new Register(5*4+80, 35*4);
	Mixer[] mixers = new Mixer[3];
	Chef chef = new Chef();
	Counter touched;
  
	public void paint(Graphics g) {
		super.paintComponent(g);
		back.paint(g);
		boolean colliding=false;
		
		chef.move();
		
		for(Counter i : counters) {
			
			if(i != null) {
				i.paint(g);
				if(chef.collided(i)) {
					colliding = true;
					
				}
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
		}
		
		for(Oven i : ovens) {
			i.paint(g);
			if(chef.collided(i)) {
				colliding = true;
				
			}
			if(i.fireCheck()) {
				System.out.println("FIREEEEEEEE OH NO");
			}
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
		
		for(Mixer i : mixers) {
			i.paint(g);
			if(chef.collided(i)) {
				colliding = true;
				
			}
		}
		
		sink.paint(g);
		if(chef.collided(sink)) {
			colliding = true;
			
		}
		
		reg.paint(g);

		if(chef.collided(reg)) {
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
		
		
	}
	
	public static void main(String[] arg) {
		Frame f = new Frame();
	}
	
	public Frame() {
		JFrame f = new JFrame("Skia");
		f.setSize(new Dimension(width, height));
		f.setBackground(Color.white);
		f.add(this);
		f.setResizable(false);
		f.addMouseListener(this);
		f.addKeyListener(this);
		
		
		init(counters);
		init(ovens);
		init(mixers);
		
		/*setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon("torch.png").getImage(),
				new Point(0,0),"custom cursor"));	*/
		
		
		Timer t = new Timer(16, this);
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
		c[10] = new Counter(20 + 80*6 + 80*2, 700, 1);
		c[11] = new Counter(20 + 80*6 + 80*3, 700, 1);
		c[12] = new Counter(20 + 80*6 + 80*4, 700, 1);
		c[13] = new Counter(20 + 80*6 + 80*5, 700, 1);
		c[14] = new Counter(900, 140 + 80*4, 1);
		c[15] = new Counter(900, 140 + 80*2, 1);
		c[16] = new Counter(900, 140, 1);
		c[17] = new Counter(900 - 80, 140, 1);
		c[18] = new Counter(900 - 80*3, 140, 1);
		c[19] = new Counter(900 - 80*4, 140, 1);
		c[20] = new Counter(900 - 80*5, 140, 1);
		c[21] = new Counter(900 - 80*6, 140, 1);
		
		
		c[22] = new Counter(20 + 80*3, 140 + 80*2, 0);
		c[23] = new Counter(20 + 80*3, 140 + 80*3, 0);
		c[24] = new Counter(20 + 80*3, 140 + 80*4, 0);
		c[25] = new Counter(20 + 80*3, 140 + 80*5, 0);
		c[26] = new Counter(20 + 80*4, 140 + 80*3, 0);
		c[27] = new Counter(20 + 80*4, 140 + 80*4, 0);
		c[28] = new Counter(20 + 80*5, 140 + 80*3, 0);
		c[29] = new Counter(20 + 80*6, 140 + 80*3, 0);
		c[30] = new Counter(20 + 80*7, 140 + 80*3, 0);
		c[31] = new Counter(20 + 80*7, 140 + 80*4, 0);
		c[32] = new Counter(20 + 80*8, 140 + 80*2, 0);
		c[33] = new Counter(20 + 80*8, 140 + 80*3, 0);
		c[34] = new Counter(20 + 80*8, 140 + 80*4, 0);
		c[35] = new Counter(20 + 80*8, 140 + 80*5, 0);
		
		c[22].obj = new Bowl();
		c[28].obj = new Bowl();
		c[29].obj = new Bowl();
		c[32].obj = new Bowl();
		c[35].obj = new Plate();
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
		
		if(e.getKeyChar() == 'e' && chef.touching(sink) && chef.obj instanceof Plate) {
			Plate temp = (Plate) chef.obj;
			chef.obj = sink.obj;
			sink.obj = temp;
		}
		
		if(e.getKeyChar() == 'l'  && chef.obj instanceof Plate) {
			Plate temp = (Plate) chef.obj;
			temp.isDirty = !temp.isDirty;
		}
		
		
		if(e.getKeyChar() == 'e' && touching) {
			
			//System.out.println(touched.getClass().getName());
			
			if(touched.getClass().getName().equals("skia.Counter")) {
				Object temp = touched.obj;
				touched.obj = chef.obj;
				chef.obj = temp;
				//System.out.println("placed");
				
			} else if (touched.getClass().getName().equals("skia.Oven")) {
				Object temp = touched.obj;
				touched.obj = chef.obj;
				chef.obj = temp;
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
