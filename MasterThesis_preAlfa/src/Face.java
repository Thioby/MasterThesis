import org.opencv.core.Rect;


public class Face extends BodyPart{	

	private BodyPart leftEye;
	private BodyPart rightEye;
	public Face(Rect faceCoords){
		super(faceCoords);
	};
	
	public Eye getLeftEye()
	{
		return (Eye)leftEye;
	}
	
	public Eye getRightEye()
	{
		return (Eye)rightEye;
	}
}
