package detectors;

public class PupilCornerDetectorCreator extends AbstractDetectorCreator{

	@Override
	protected AbstractDetector createDetector() {
		return new PupilCornerDetector();
	}

}
