import org.opencv.core.Rect;


public abstract class BodyPart {

	private Rect _partRect;
	
	public BodyPart(Rect faceCoords)
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
