package jkDollar;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Path;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeGenerator 
{
	public static void main(String[] args) throws IOException 
	{
		Reader in;
		if(!new File("D:\\save1.dat").exists()){
			File file1 =new File("D:\\save1.dat");
	    	File file2 =new File("D:\\save2.dat");
	    	File file5 =new File("D:\\save5.dat");
	    	File file10 =new File("D:\\save10.dat");
			Writer out;
			out = new FileWriter(file1);
			String data = "1";
    		out.write(data);
    		out.close();
    		out = new FileWriter(file2);
    		data = "1";
    		out.write(data);
    		out.close();
    		out = new FileWriter(file5);
    		data = "1";
    		out.write(data);
    		out.close();
    		out = new FileWriter(file10);
    		data = "1";
    		out.write(data);out.append("1");
    		out.close();
		}
		//Swing Windows Initialization
		JFrame jf = new JFrame("QR Code Generator");
		JPanel jp = new JPanel();
		JLabel label = new JLabel("Name Of Student");
        JLabel label1 = new JLabel("Face value");
        JLabel label2 = new JLabel("Number");
		JTextField jt1 = new JTextField();
		JTextField jt2 = new JTextField();
		JTextField jt3 = new JTextField();
		JTextField text1 = new JTextField();
		JButton jb = new JButton();
		JButton jb1 = new JButton("Select the path");
		jf.setSize(600, 800);
		JFileChooser jfc = new JFileChooser();
		jf.setVisible(true);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int x = (int)(toolkit.getScreenSize().getWidth()-jf.getWidth())/2;
		int y = (int)(toolkit.getScreenSize().getHeight()-jf.getHeight())/2;
		jf.setLocation(x, y);
		jf.add(jp);
		jp.setVisible(true);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		jf.setTitle("BarCode Generator");	
		jp.setLayout(null);
		jt1.setBounds(140, 60, 180, 30);
//		jt1.addFocusListener(new JTextFieldHintListener(jt1,"Student's name"));
		jt2.setBounds(140, 100, 180, 30);
//		jt2.addFocusListener(new JTextFieldHintListener(jt2,"Face value"));
		jt3.setBounds(140, 140, 180, 30);
//		jt3.addFocusListener(new JTextFieldHintListener(jt3,"Number"));
		text1.setBounds(140, 180, 180, 30);
		label.setBounds(40, 60, 100, 30);
		label1.setBounds(40, 100, 100, 30);
		label2.setBounds(40, 140, 100, 30);
		jp.add(label);
		jp.add(label1);
		jp.add(label2);
		jp.add(jt1);
		jp.add(jt2);
		jp.add(jt3);
		jp.add(text1);
		jp.add(jb1);
		jb.setBounds(350, 100, 120, 30);
		jb1.setBounds(350, 180, 120, 30);
		jb1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				jfc.setFileSelectionMode(1); 
	            int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句  
	            if (state == 1) 
	            {  
	                return;  
	            } else {  
	                File f = jfc.getSelectedFile();// f为选择到的目录  
	                text1.setText(f.getAbsolutePath()); 
			}
			}
			});
		jb.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent e) 
            {
//            	String s=Integer.toString(i);
        		for(int a = 0;a<Integer.parseInt(jt3.getText());a++){
            	char[] facevalue = new char[3];
            	char[] serialnum = new char[]{0,0,0,0,0,0,0};//0000000
            	char[] name = jt1.getText().toCharArray();
            	char[] fv = String.format("%03d", Integer.parseInt(jt2.getText())).toCharArray();
            	char[] num = null;
            	try {
            		File f = new File("D:\\save"+jt2.getText()+".dat");
            		FileReader fr = new FileReader(f);
        			BufferedReader bf = new BufferedReader(fr);
					char[] ch = new char[3];
					int temp1 = Integer.valueOf(bf.readLine());
					System.out.println(temp1);
					bf.close();
					Writer out = new FileWriter(f);
					out.write(String.valueOf(temp1+1));
					out.close();
					num = String.format("%04d",temp1).toCharArray();
            	} catch (FileNotFoundException e1) 
            	{
					e1.printStackTrace();
				} catch (IOException e1) 
            	{
					e1.printStackTrace();
				}
            	for(int i = 0;i<=2;i++)
            		serialnum[i] = fv[i];
            	for(int i = 3;i<7;i++)
            		serialnum[i] = num[i-3];
            	Note n = new Note(serialnum,name);
            	genBar(n.toString(),String.valueOf(n.SerialNum),text1.getText());
            	System.out.println(String.valueOf(n.SerialNum));
        		}
            }
        });
		jp.add(jb);
		jb.setText("Generate");
		
	}
	
	static void genBar(String cont,String name,String path){	//enter the information you want to genegrate, name of the file, and path of the file
		int width = 264;      //Width
        int height = 50;     //Length
        String format = "png";    //Format
        String content = cont;     //Content
        
        HashMap hints=new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET,"utf-8");//Set encoding method with UTF-*
        hints.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.H);//Set error correction level with high
        hints.put(EncodeHintType.MARGIN, 2);
        try 
        {
            BitMatrix bitMatrix=new Code128Writer().encode(cont, BarcodeFormat.CODE_128, width, height, hints);
            Path file=new File(path+'/'+name+".png").toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
        } catch (Exception e) 
        {
            e.printStackTrace();
        }

	}
	static BufferedImage ApplendCode() 
	{
		
		return null;
	}
}
/*
class JTextFieldHintListener implements FocusListener {
	private String hintText;
	private JTextField textField;
	public JTextFieldHintListener(JTextField jTextField,String hintText) {
		this.textField = jTextField;
		this.hintText = hintText;
		jTextField.setText(hintText);  
		jTextField.setForeground(Color.GRAY);
	}
 
	@Override
	public void focusGained(FocusEvent e) {
		String temp = textField.getText();
		if(temp.equals(hintText)) {
			textField.setText("");
			textField.setForeground(Color.BLACK);
		}
		
	}
 
	@Override
	public void focusLost(FocusEvent e) {	
		String temp = textField.getText();
		if(temp.equals("")) {
			textField.setForeground(Color.GRAY);
			textField.setText(hintText);
		}
		
	}

}
*/