package detectors;
import java.util.ArrayList;

import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.objdetect.Objdetect;

import body.Eye;


public class EyesDetector extends AbstractDetector{	

	public EyesDetector() {
		super();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Eye> detec()
	{
		super.detec();
		_detected = new ArrayList<Eye>();
		_detector.detectMultiScale(_imgGrey, _detections, 1.2, 5, Objdetect.CASCADE_SCALE_IMAGE, new Size(40,40), new Size(10000,30000));		
		for(Rect rec : _detections.toArray())
			_detected.add(new Eye(rec));
		return _detected;
	}


}
