package main;
import org.opencv.core.Mat;
import org.opencv.highgui.*;


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
