import java.util.ArrayList;

import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;


public class EyesDetector extends Detector<Eye>{
	public EyesDetector(CascadeClassifier classifier)
	{
		super(classifier);
	}
	
	public EyesDetector(){	}
	public ArrayList<Eye> detec()
	{
		super.detec();
		_detector.detectMultiScale(_imgGrey, _detections, 1.2, 2, Objdetect.CASCADE_SCALE_IMAGE, new Size(10,10), new Size(10000,30000));		
		for(Rect rec : _detections.toArray())
			_detected.add(new Eye(rec));
		return _detected;
	}
}
