package detectors;

public abstract class AbstractDetectorCreator {
	
	AbstractDetector detector;
	protected abstract AbstractDetector createDetector();
	
	public AbstractDetector create(String classifierPath)
	{
		detector = createDetector();		
		if(detector.loadClassifier(classifierPath))
			System.out.println("Classifier: " + classifierPath+ " loaded successfully");
		else
		{
			System.out.println(String.format("Cannot load classifier from: %s, bye", classifierPath));
			System.exit(-1);
		
		}
		return detector;
	}

	public AbstractDetector create() {
		
		detector = createDetector();
		return detector;
	}
}
