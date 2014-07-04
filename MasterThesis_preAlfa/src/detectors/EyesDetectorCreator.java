package detectors;

public class EyesDetectorCreator extends AbstractDetectorCreator {

	
	protected AbstractDetector createDetector() {
		return new EyesDetector();
	}

}
