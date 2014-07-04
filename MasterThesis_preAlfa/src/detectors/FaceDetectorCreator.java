package detectors;

public class FaceDetectorCreator extends AbstractDetectorCreator {

	@Override
	protected AbstractDetector createDetector() {		
		return new FaceDetector();
	}

}
