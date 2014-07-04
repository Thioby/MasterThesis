package main;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;


public class ShowImageTmp extends JFrame{

		  
		public static void ShowImage(String imgStr, Mat m)
		{
			Highgui.imwrite(imgStr,m);
			JFrame frame = new JFrame("My GUI");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 
			frame.setResizable(true);
			frame.setLocationRelativeTo(null);
			 
			// Inserts the image icon
			ImageIcon image = new ImageIcon(imgStr);
			frame.setSize(image.getIconWidth()+10,image.getIconHeight()+35);
			// Draw the Image data into the BufferedImage
			JLabel label1 = new JLabel(" ", image, JLabel.CENTER);
			frame.getContentPane().add(label1);
			 
			frame.validate();
			frame.setVisible(true);
		}		

		public static void ShowImage(String imgStr, ArrayList<Mat> imagesWithFaces) 
		{
			int i = 0;
			for(Mat img : imagesWithFaces)
			{
				ShowImage(String.format("%s%s.jpg",imgStr,i++), img);
			}
			
		}

		public static void ShowImageFromImage(String imgStr, ArrayList<Image> set) 
		{
			int i = 0;
			for(Image img : set)
			{
				ShowImage(String.format("%s%s.jpg",imgStr,i++), img.getImage());
			}
			
		}
		
		
	
}
