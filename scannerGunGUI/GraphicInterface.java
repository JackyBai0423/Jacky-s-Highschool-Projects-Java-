package scannerGunGUI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class GraphicInterface {
		static int width = 500;
		static int height = 500;
	public static void main(String args[]) {
		JFrame mainFrame = new JFrame("Scanner Result Display");
		JPanel mainPanel = new JPanel();
		JTextField jtf = new JTextField();
		Listener lis = new Listener(jtf);
		
		mainFrame.setVisible(true);
		mainFrame.setSize(width, height);
		mainFrame.setResizable(false);
		mainPanel.setVisible(true);
		mainPanel.setSize(width, height);
		mainPanel.setBackground(Color.PINK);
		mainFrame.add(mainPanel);
		mainPanel.add(jtf);
		mainFrame.addKeyListener(lis);
		mainPanel.setLayout(null);
		jtf.setBounds(140, 60, 180, 30);
		jtf.addKeyListener(lis);
		
	}
}
class Listener implements KeyListener{
	char Keyvalue;
	char[] SerialNumber = new char[7];
	int index = 0;
	JTextField jtf  = null;
	
	public Listener(JTextField jtf) {
		this.jtf = jtf;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		this.Keyvalue = e.getKeyChar();
		try {
			if((Keyvalue<='9')&&(Keyvalue>='0')) 	// to detect whether is a number or not
			{
				SerialNumber[index] = Keyvalue;
				index++;
			}
		}
		catch(Exception e1) {	//when the index goes out of the bound, catch will clean the SerialNumber and index return to 0
			System.out.println(String.valueOf(SerialNumber));
			this.jtf.setText(String.valueOf(SerialNumber));
			index = 0;
			if((Keyvalue<='9')&&(Keyvalue>='0')) {
				SerialNumber[index] = Keyvalue;
				index++;
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	public char getValue() {
		return Keyvalue;
	}
}