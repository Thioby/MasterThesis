import java.util.ArrayList;
import org.opencv.core.Rect;
import org.opencv.objdetect.*;
import org.opencv.core.Size;


public class FaceDetector extends Detector<Face>{	
	
	
	public FaceDetector(CascadeClassifier classifier)
	{
		super(classifier);
	}
	
	public FaceDetector(){	}
	public ArrayList<Face> detec()
	{
		super.detec();
		_detector.detectMultiScale(_imgGrey, _detections, 1.1, 2, Objdetect.CASCADE_SCALE_IMAGE, new Size(30,30), new Size(10000,30000));		
		for(Rect rec : _detections.toArray())
			_detected.add(new Face(rec));
		return _detected;
	}
}
