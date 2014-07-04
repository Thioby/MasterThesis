package body;
import org.opencv.core.Rect;


public class Face extends AbstractBodyPart{	

	private AbstractBodyPart leftEye;
	private AbstractBodyPart rightEye;
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
