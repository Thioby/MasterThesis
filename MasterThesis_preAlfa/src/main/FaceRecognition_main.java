package main;
import java.util.ArrayList;

import managers.FileManager;
import managers.ImageBodyPartManager;



import org.opencv.core.*;

import body.AbstractBodyPart;
import body.Eye;
import body.Face;
import body.Pupil;
import detectors.AbstractDetector;
import detectors.AbstractDetectorCreator;
import detectors.EyesDetector;
import detectors.EyesDetectorCreator;
import detectors.FaceDetector;
import detectors.FaceDetectorCreator;
import detectors.PupilCornerDetectorCreator;



public class FaceRecognition_main {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
	
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		String pathToPhotosFolder = "./src/images/skype";
		String pathToFaceClassifier = "./src/xmls/haarcascade_frontalface_default.xml";
		String pathToEyesClassifier = "./src/xmls/haarcascade_eye.xml";
		
		ArrayList<String> pathes = FileManager.findFiles(pathToPhotosFolder, ".png");
		
		
		AbstractDetectorCreator faceDetectorCreator = new FaceDetectorCreator();	
		AbstractDetectorCreator eyesDetecorCreator = new EyesDetectorCreator();
		AbstractDetectorCreator	pupilCornerDetectorCreator = new PupilCornerDetectorCreator();
	
		AbstractDetector eyesDetector = eyesDetecorCreator.create(pathToEyesClassifier);
		AbstractDetector faceDetector = faceDetectorCreator.create(pathToFaceClassifier);
		AbstractDetector pupilCornerDetector = pupilCornerDetectorCreator.create();
		
		ImageBodyPartManager<Face> imageFaceManager = new ImageBodyPartManager<Face>();
		
		ArrayList<Image> imagesWithFaces = new ArrayList<>();
		
		ArrayList<Image> imagesWithEyes = new ArrayList<>();
		
		for(String pathToFacePhoto : pathes) //dla kazdego zdjecia, przeszukujemy obrazek i znajdujemy twarze
		{
			Image image = new Image(pathToFacePhoto);		
			if(image.loadImage())
			{
								
				System.out.println("Image: " + pathToFacePhoto + " loaded successfully");
				faceDetector.setImage(image);
				ArrayList<Face> facesInImage = faceDetector.detec();
				Mat output = image.getImage();
				for(Face face : facesInImage)
				{
					eyesDetector.setImage(output.submat(face.getBodyPartCoords()));	
					face.addEyes( eyesDetector.detec());			//do usuniecia ten warunek	
				
					
				}
				imageFaceManager.addBodyPartsToImage(image, facesInImage);
				
				
			}
			else
			{
				System.out.println(String.format("Cannot load image from: %s, next", pathToFacePhoto));				
			}			
		}				
		
		
		/*
		 * wycinanie twarzy z obrazków - zbêdne, mozna operowaæ tylko na koordynatach, mniej pamieci zajête
		 *
		for(Image image : imageFaceManager.getImages()) //dla kazdegho obrazka
		{
			Mat output = image.getImage();			
			for(Face face : imageFaceManager.getBodyParts(image)) //wyciagnij twarze
			{
				Rect rect = face.getBodyPartCoords();
				Core.rectangle(output, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
				
				//imagesWithFaces.add(new Image(new Mat(output, face.getBodyPartCoords()))); //to wycina mniejszy obrazek z wiêkszego
			}
			
		}		
		ShowImageTmp.ShowImageFromImage("d:\\output/", imagesWithFaces);*/
		
		/*ImageBodyPartManager<Eye> eyesManager = new ImageBodyPartManager<>();
		
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
		}*/
		
		for(Image img : imageFaceManager.getImages())
		{
			Mat output = img.getImage();
			for(Face face : imageFaceManager.getBodyParts(img))
			{
				for(Eye eye : face.getEyes())
				{
					//here detect pupil,
					
					pupilCornerDetector.setImage(img.getImage().submat(eye.getBodyPartCoords()));				
					ArrayList<Pupil> pupilList = pupilCornerDetector.detec();				
					for(Pupil pupil : pupilList)
					{
						eye.setPupil(pupil);
						Rect rect = eye.getPupil().getBodyPartCoords();
						Core.rectangle(output, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
					}					
					
					
				}
			}
			imagesWithFaces.add(new Image(output));
		}
		
		
		/*for(Image img : imagesWithEyes)
		{
			Mat output = img.getImage();
		
			eyesDetector.setImage(img);			
			ArrayList<Eye> eyes = eyesDetector.detec();
			for(Eye eye : eyes)
			{	
				Rect rect = eye.getBodyPartCoords();
				Core.rectangle(output, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));				
			}
			imagesWithFaces.add(new Image(output));
		}*/
		
		
		ShowImageTmp shower = new ShowImageTmp();
		shower.ShowImageFromImage("d:\\outputEyes/", imagesWithFaces);
		
		
		
		
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


/*
Wa¿ne:
	nie wczytywac na czarno bia³o tylko na kolorowo!
*/