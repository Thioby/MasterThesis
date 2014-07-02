import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;


public abstract class Detector <T> {
	protected Mat _image;
	protected Mat _imgGrey;
	protected CascadeClassifier _detector;
	protected MatOfRect _detections;
	protected ArrayList<T> _detected;
	
	
	public Detector(CascadeClassifier classifier)
	{
		_detector = classifier;
	}
	public Detector(){	}
	public boolean loadClassifier(String path)
	{
		try
		{
			this._detector = new CascadeClassifier(path);
			
		}catch(Exception e)
		{
			return false;
		}
		return !_detector.empty();
	}
	public void setImage(Image img)
	{
		this._image = img.getImage();
	}
	
	public void setImage(Mat img)
	{
		this._image = img;
	}
	
	public ArrayList<T> detec()
	{
		_detected = new ArrayList<>();
		_detections = new MatOfRect();
		_imgGrey = new Mat();
		
		if(_image.channels()>1)
			Imgproc.cvtColor(_image, _imgGrey, Imgproc.COLOR_BGR2GRAY);
		else _imgGrey = _image;		
		return null;
	}
	
}
