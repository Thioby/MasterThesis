package body;
import java.util.ArrayList;

import org.opencv.core.Rect;


public class Face extends AbstractBodyPart{	

	private ArrayList<Eye> eyes;

	public Face(Rect faceCoords){
		super(faceCoords);
	};
	
	public ArrayList<Eye> getEyes()
	{
		return eyes;
	}
	
	public void addEyes(ArrayList<Eye> _eyes)
	{		
		eyes = new ArrayList<>();
		for(Eye eye : _eyes)
		{
			eye.setBodyPartCoords(new Rect(this.getBodyPartCoords().x + eye.getBodyPartCoords().x,
					this.getBodyPartCoords().y + eye.getBodyPartCoords().y,
					eye.getBodyPartCoords().width,  eye.getBodyPartCoords().height
					));
			eyes.add(eye);
		}
		
	}

}
