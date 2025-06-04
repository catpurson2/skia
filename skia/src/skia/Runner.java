package skia;

import java.awt.Color;
import java.awt.Dimension;
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
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Runner extends JPanel implements MouseListener, KeyListener, ActionListener {

	static JFrame f;
	BufferedImage menu = getImg("menu");
	static boolean start = false;
	
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
	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		g.drawImage(menu, 0,0,1000,828,null);
		g.setColor(new Color(240, 199, 240));
		g.fillRect(95, 397, 325, 55);
		
	}
	public BufferedImage getImg(String path) {
		
		try {
			return ImageIO.read(getClass().getResource("/imgs/" + path + ".png"));
		} catch(Exception e) {
			System.out.println("tuesday");
		}
		
		return null;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyChar() == 'm') {
			Frame f = new Frame();
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
		if(!start) {
			if(e.getX() >= 104 && e.getX() <= 424 && e.getY() <= 404 && e.getY() >= 354) {
				Frame f = new Frame();
				start = true;
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
