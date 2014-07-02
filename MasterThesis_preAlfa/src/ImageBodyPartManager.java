import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;



public class ImageBodyPartManager <T>{

	private Map<Image, ArrayList<T>> _imagesFaces;
	
	public ImageBodyPartManager()
	{
		_imagesFaces = new HashMap<Image,ArrayList<T>>();
	}
	
	public void addImage(Image img, ArrayList<T> faces)
	{
		_imagesFaces.put(img,faces);
	}
	public List<T> getParts(Image img)
	{
		return _imagesFaces.get(img);
	}
	
	public Set<Image> getImages()
	{
		return _imagesFaces.keySet();
	}
}
