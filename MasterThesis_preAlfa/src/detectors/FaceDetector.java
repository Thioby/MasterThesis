
package detectors;
import java.util.ArrayList;

import org.opencv.core.Rect;
import org.opencv.objdetect.*;
import org.opencv.core.Size;

import body.Face;


public class FaceDetector extends AbstractDetector {	
	
	
	public FaceDetector(){super();}
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<Face> detec()
	{
		super.detec();
		_detected = new ArrayList<Face>();
		_detector.detectMultiScale(_imgGrey, _detections, 1.1, 2, Objdetect.CASCADE_SCALE_IMAGE, new Size(50,50), new Size(10000,30000));		
		for(Rect rec : _detections.toArray())
			_detected.add(new Face(rec));
		return _detected;
		
	}

	
}
