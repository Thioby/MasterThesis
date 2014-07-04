package main;
import java.util.ArrayList;

import managers.FileManager;
import managers.ImageBodyPartManager;

import org.opencv.core.*;

import body.Eye;
import body.Face;
import detectors.AbstractDetector;
import detectors.AbstractDetectorCreator;
import detectors.EyesDetector;
import detectors.EyesDetectorCreator;
import detectors.FaceDetector;
import detectors.FaceDetectorCreator;



public class FaceRecognition_main {

	public static void main(String[] args) {
	
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		String pathToPhotosFolder = "./src/images/test";
		String pathToFaceClassifier = "./src/xmls/haarcascade_frontalface_alt.xml";
		String pathToEyesClassifier = "./src/xmls/haarcascade_eye.xml";
		
		ArrayList<String> pathes = FileManager.findFiles(pathToPhotosFolder, ".jpg");
		
		
		AbstractDetectorCreator faceDetectorCreator = new FaceDetectorCreator();	
		AbstractDetectorCreator eyesDetecorCreator = new EyesDetectorCreator();
	
		AbstractDetector eyesDetector = eyesDetecorCreator.create(pathToEyesClassifier);
		AbstractDetector faceDetector = faceDetectorCreator.create(pathToFaceClassifier);
		
		ImageBodyPartManager<Face> imageFaceManager = new ImageBodyPartManager<Face>();
		
		for(String pathToFacePhoto : pathes)
		{
			Image image = new Image(pathToFacePhoto);		
			if(image.loadImage())
			{
				System.out.println("Image: " + pathToFacePhoto + " loaded successfully");
				faceDetector.setImage(image);
				imageFaceManager.addImage(image, faceDetector.detec()); //mozna zrobiæ ze zapisuje tylko z wykrytymi twarzami
			}
			else
			{
				System.out.println(String.format("Cannot load image from: %s, next", pathToFacePhoto));				
			}			
		}				
		
		ArrayList<Image> imagesWithFaces = new ArrayList<>();
		for(Image image : imageFaceManager.getImages())
		{
			Mat output = image.getImage();			
			for(Face face : imageFaceManager.getBodyParts(image))
			{
				//Rect rect = face.getFaceCoords();
				//Core.rectangle(output, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
				
				imagesWithFaces.add(new Image(new Mat(output, face.getBodyPartCoords())));
			}
			
		}		
		//ShowImageTmp.ShowImageFromImage("d:\\output/", imagesWithFaces);
		
		ImageBodyPartManager<Eye> eyesManager = new ImageBodyPartManager<>();
		
		ArrayList<Image> eyesWithFaces = new ArrayList<>();
		
		for(Image img : imagesWithFaces)
		{	
			
			eyesDetector.setImage(img);
			eyesManager.addImage(img, eyesDetector.detec());
		}
		
		for(Image img : eyesManager.getImages())
		{
			Mat output = img.getImage();
			for(Eye eye : eyesManager.getBodyParts(img))
			{
				Rect rect = eye.getBodyPartCoords();
				Core.rectangle(output, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));			
			}
			eyesWithFaces.add(new Image(output));
		}
		
		ShowImageTmp.ShowImageFromImage("d:\\outputEyes/", imagesWithFaces);
		
	}

}




/*Wyœwietlanie ca³ych obrazków
 * 	for(Image image : manager.getImages())
		{
			Mat output = image.getImage();
			boolean faceDetected = false;
			for(Face face : manager.getFaces(image))
			{
				Rect rect = face.getFaceCoords();
				Core.rectangle(output, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
				faceDetected = true;
			}
			if(faceDetected)imagesWithFaces.add(output);
		}
 */



/*CascadeClassifier faceDetector = new CascadeClassifier(pathToFaceClassifier);
boolean is = faceDetector.empty();
Mat image = Highgui.imread(pathToFacePhoto,Highgui.CV_LOAD_IMAGE_GRAYSCALE);
Highgui.imwrite("d:\\1.jpg",image);
// Detect faces in the image.
// MatOfRect is a special container class for Rect.
MatOfRect faceDetections = new MatOfRect();
faceDetector.detectMultiScale(image, faceDetections);
ShowImageTmp show =  new ShowImageTmp();
show.ShowImage("d:\\1.jpg");
System.out.println(String.format("Detected %s faces" + "Height: " + image.height() + " Width: " + image.width(), faceDetections.toArray().length));*/
