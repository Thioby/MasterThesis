package body;
import org.opencv.core.Rect;


public class Eye extends AbstractBodyPart{

	AbstractBodyPart pupil;
	public Eye(Rect eyeCoords)
	{
		super(eyeCoords);
	}
	
	public Pupil getPupil()
	{
		return (Pupil)pupil;
	}
}
