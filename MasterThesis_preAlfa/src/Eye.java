import org.opencv.core.Rect;


public class Eye extends BodyPart{

	BodyPart pupil;
	public Eye(Rect eyeCoords)
	{
		super(eyeCoords);
	}
	
	public Pupil getPupil()
	{
		return (Pupil)pupil;
	}
}
