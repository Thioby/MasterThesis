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
	
	public void setPupil(Pupil _pupil)
	{
		pupil = new Pupil(new Rect(this.getBodyPartCoords().x + _pupil.getBodyPartCoords().x,
				this.getBodyPartCoords().y + _pupil.getBodyPartCoords().y,
				_pupil.getBodyPartCoords().width,  _pupil.getBodyPartCoords().height
				));		
		
	}
}
