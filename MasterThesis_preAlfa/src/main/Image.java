package main;
import org.opencv.core.Mat;
import org.opencv.highgui.*;
import org.opencv.imgproc.Imgproc;


public class Image 
{
	private String _path;
	private Mat image;
	
	public Image(String path)
	{
		_path = path;
	}
	
	public Image(Mat img)
	{
		this.image = img;
	}
	public boolean loadImage() 
	{
		try
		{	
			image = Highgui.imread(_path);
			//Imgproc.cvtColor( Highgui.imread(_path), image, Imgproc.COLOR_BGR2GRAY); //load and converse to greyScale
			
		}catch(Exception e)
		{
			return false;
		}
		return !image.empty();
	}
	
	public Mat getImage()
	{
		return image;
	}
	
}
