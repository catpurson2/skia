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
	

	Background back = new Background();
	Counter[] counters = new Counter[36];
	Oven[] ovens = new Oven[3];
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		
		back.paint(g);
		
		for(Counter i : counters) {
			if(i != null) {
				i.paint(g);
			}
		}
		
		ovens[0].paint(g);
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
		
		/*setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon("torch.png").getImage(),
				new Point(0,0),"custom cursor"));	*/
		
		
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	public void init(Counter[] c) {
		
		c[0] = new Counter(5*4, 35*4);
		c[1] = new Counter(20, 140 + 80);
		c[2] = new Counter(20, 140 + 80*4);
		c[3] = new Counter(20, 140 + 80*5);
		c[4] = new Counter(20, 140 + 80*6);
		c[5] = new Counter(20, 140 + 80*7);
		c[6] = new Counter(20 + 80*2, 700);
		c[7] = new Counter(20 + 80*4, 700);
		c[8] = new Counter(20 + 80*6, 700);
		c[9] = new Counter(20 + 80*6 + 80, 700);
		c[10] = new Counter(20 + 80*6 + 80*2, 700);
		c[11] = new Counter(20 + 80*6 + 80*3, 700);
		c[12] = new Counter(20 + 80*6 + 80*4, 700);
		c[13] = new Counter(20 + 80*6 + 80*5, 700);
		c[14] = new Counter(900, 140 + 80*4);
		c[15] = new Counter(900, 140 + 80*2);
		c[16] = new Counter(900, 140);
		c[17] = new Counter(900 - 80, 140);
		c[18] = new Counter(900 - 80*3, 140);
		c[19] = new Counter(900 - 80*4, 140);
		c[20] = new Counter(900 - 80*5, 140);
		c[21] = new Counter(900 - 80*6, 140);
		
		
		c[22] = new Counter(20 + 80*3, 140 + 80*2);
		c[23] = new Counter(20 + 80*3, 140 + 80*3);
		c[24] = new Counter(20 + 80*3, 140 + 80*4);
		c[25] = new Counter(20 + 80*3, 140 + 80*5);
		c[26] = new Counter(20 + 80*4, 140 + 80*3);
		c[27] = new Counter(20 + 80*4, 140 + 80*4);
		c[28] = new Counter(20 + 80*5, 140 + 80*3);
		c[29] = new Counter(20 + 80*6, 140 + 80*3);
		c[30] = new Counter(20 + 80*7, 140 + 80*3);
		c[31] = new Counter(20 + 80*7, 140 + 80*4);
		c[32] = new Counter(20 + 80*8, 140 + 80*2);
		c[33] = new Counter(20 + 80*8, 140 + 80*3);
		c[34] = new Counter(20 + 80*8, 140 + 80*4);
		c[35] = new Counter(20 + 80*8, 140 + 80*5);
		
	}
	
	public void init(Oven[] o) {
		o[0] = new Oven(900 - 80*2, 140);
	}
	
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
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
