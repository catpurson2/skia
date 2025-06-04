package skia;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Runner extends JPanel implements MouseListener, KeyListener, ActionListener {

	Timer t;
	static JFrame f;
	static Frame frame;
	BufferedImage menu = getImg("menu");
	static boolean start = false;
	Font joystix;
	static String name;
	BufferedImage chara;
	BufferedImage flynn = getChar("flynn0");
	BufferedImage atlas = getChar("atlas0");
	BufferedImage turtle = getChar("turtle0");
	BufferedImage bald = getChar("bald0");
	int selected = 0;
	
	public static void main(String[] arg) {
		Runner r = new Runner();
		
		try {
			Scanner scan = new Scanner(new File("saveData.txt"));
			Frame.hiScore = scan.nextInt();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public Runner() {
		f = new JFrame();
		f.setSize(new Dimension(1000, 828));
		f.setBackground(Color.white);
		f.add(this);
		f.setResizable(false);
		f.addMouseListener(this);
		f.addKeyListener(this);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		chara = atlas;
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
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		//tutorial writing
		g.drawImage(menu, 0,0,1000,828,null);
		g.setColor(new Color(240, 199, 240));
		g.fillRect(95, 397, 325, 205);
		g.setColor(Color.black);
		g.setFont(joystix);
		g.setFont(g.getFont().deriveFont(Font.PLAIN,32F));
		g.drawString("GAMEPLAY", 155, 420);
		g.setFont(g.getFont().deriveFont(Font.PLAIN,20F));
		g.drawString("Press 'e' to pick up, place,", 15, 465);
		g.drawString("and collect ingredients", 15, 490);
		g.drawString("Press space to interact", 15, 540);
		g.drawString("You need to hold a bowl to", 15, 590);
		g.drawString("grab ingredients", 15, 615);
		g.drawString("Mix before you bake!", 15, 665);
		g.drawString("Serve cakes on plates and", 15, 715);
		g.drawString("Dont forget to wash them!", 15, 740);
		g.setFont(g.getFont().deriveFont(Font.PLAIN,30F));
		g.drawString("" + Frame.hiScore, 790, 130);
		
		if(selected == 0) {
			chara = atlas;
			name = "atlas";
		}
		if(selected == 1) {
			chara = flynn;
			name = "flynn";
		}
		if(selected == 2) {
			chara = turtle;
			name = "turtle";
		}
		if(selected == 3) {
			chara = bald;
			name = "bald";
		}
		//character select
		g.drawImage(chara, 650, 290, 200, 224, null);
		
	}
	public BufferedImage getImg(String path) {
		
		try {
			return ImageIO.read(getClass().getResource("/imgs/" + path + ".png"));
		} catch(Exception e) {
			System.out.println("tuesday");
		}
		
		return null;
		
	}
	
	public BufferedImage getChar(String path) {
		
		try {
			return ImageIO.read(getClass().getResource("/character/" + path + ".png"));
		} catch(Exception e) {
			System.out.println("tuesday");
		}
		
		return null;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(!start) {
			repaint();
		}
			
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyChar() == 'm') {
			chara = atlas;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getX() + " " + e.getY());
		if(!start) {
			if(e.getX() >= 104 && e.getX() <= 424 && e.getY() <= 404 && e.getY() >= 354) {
				frame = new Frame();
				frame.chef.chara = name;
				
				System.out.println(name);
				start = true;
			}
			if(e.getX() >= 532 && e.getX() <= 560 && e.getY() >= 406 && e.getY() <= 486) {
				if(selected == 0) {
					selected = 3;
				}else {
					selected--;
				}
			}
			if(e.getX() >= 956 && e.getX() <= 982 && e.getY() >= 406 && e.getY() <= 486) {
				if(selected == 3) {
					selected = 0;
				}else {
					selected++;
				}
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
