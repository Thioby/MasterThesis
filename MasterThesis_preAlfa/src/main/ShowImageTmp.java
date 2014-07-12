package main;
import java.awt.GradientPaint;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;


public class ShowImageTmp extends JFrame{

		  
		/**
	 * 
	 */
	 JFrame frame;
	 JPanel pamel;
	 JScrollPane scrollPane;
	private  final long serialVersionUID = 1L;
	
	public ShowImageTmp()
	{
		frame = new JFrame("My GUI");
		frame.setBounds(100, 100, 400, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		pamel = new JPanel();
		pamel.setLayout(new GridLayout(0, 1, 10, 10));
		scrollPane = new JScrollPane(pamel);
		
		frame.add(scrollPane);
	}

		public  void ShowImage(String imgStr, Mat m)
		{
			Highgui.imwrite(imgStr,m);
			
			ImageIcon image = new ImageIcon(imgStr);		
			JLabel label1 = new JLabel(" ", image, JLabel.CENTER);
			label1.setSize(image.getIconWidth()+10,image.getIconHeight()+35);
			pamel.add(label1);			
			 
			frame.validate();
			
		}		

		public  void ShowImage(String imgStr, ArrayList<Mat> imagesWithFaces) 
		{
			int i = 0;
			for(Mat img : imagesWithFaces)
			{
				ShowImage(String.format("%s%s.jpg",imgStr,i++), img);
			}
			
		}
		
		public  void ShowImage(String imgStr, Image img) 
		{
			ShowImage(imgStr, img.getImage());
		}

		public  void ShowImageFromImage(String imgStr, ArrayList<Image> set) 
		{
			int i = 0;
			for(Image img : set)
			{
				ShowImage(String.format("%s%s.jpg",imgStr,i++), img.getImage());
			}
			
		}
		
		
	
}
