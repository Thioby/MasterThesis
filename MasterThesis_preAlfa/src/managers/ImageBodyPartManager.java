package managers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import main.Image;



public class ImageBodyPartManager <T>{

	private Map<Image, ArrayList<T>> imageWithBodyPart;
	
	public ImageBodyPartManager()
	{
		imageWithBodyPart = new HashMap<Image,ArrayList<T>>();
	}
	
	public void addImage(Image img, ArrayList<T> _bodyParts)
	{
		imageWithBodyPart.put(img,_bodyParts);
	}
	public ArrayList<T> getBodyParts(Image img)
	{
		return imageWithBodyPart.get(img);
	}
	
	public Set<Image> getImages()
	{
		return imageWithBodyPart.keySet();
	}
	public void addBodyPartsToImage(Image img, ArrayList<T> _bodyParts)
	{
		ArrayList<T> tmpList = getBodyParts(img);
		if(!_bodyParts.isEmpty())
		{
			if((tmpList != null))
			{
				tmpList.addAll(_bodyParts);
				imageWithBodyPart.remove(img);
				imageWithBodyPart.put(img, tmpList);
			}
			else
			{
				addImage(img, _bodyParts);
			}
		}else
		{
			return;
		}
		
	}
}
