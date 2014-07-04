package body;
import org.opencv.core.Rect;


public abstract class AbstractBodyPart {

	private Rect _partRect;
	
	public AbstractBodyPart(Rect faceCoords)
	{
		_partRect = new Rect();
		this._partRect = faceCoords;
	}
	public Rect getBodyPartCoords()
	{
		return _partRect;
	}
	public void setBodyPartCoords(Rect faceCoords)
	{
		this._partRect = faceCoords;
	}
}
