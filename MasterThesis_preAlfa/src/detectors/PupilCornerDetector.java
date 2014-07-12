package detectors;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import body.Pupil;


public class PupilCornerDetector extends AbstractDetector{
	
	private static int i = 0;
	
	public PupilCornerDetector(){
		super();
		i++;
	}
	
	
	public ArrayList<Pupil> detec()
	{
		super.detec();
		ArrayList<Pupil> tmp = new ArrayList<>();
		tmp.add(new Pupil(detectPupil(_imgGrey)));		
		return tmp;
		
	}
	
	public  Rect detectPupilV2(Mat img)
	{
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		

        Core.inRange(img, new Scalar(0,0,0), new Scalar(100,100,100), img); //threshold only dark colours
        Imgproc.findContours(img, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
		Imgproc.drawContours(img, contours, -1, new Scalar(255,255,255), -1);
		for(MatOfPoint contour: contours)
		 {
			if(contour.toArray().length > 15)
			{
				 RotatedRect temp = Imgproc.fitEllipse(new MatOfPoint2f(contour.toArray()));				
				 //Core.circle(img, new Point(temp.center.x, temp.center.y), (int)temp.size.width/2, new Scalar(0,0,255)); //draw all contours
				 //check if size is in acceptable pupil size
				 if(temp.size.height<250 && temp.size.width<250 && temp.size.height>30 && temp.size.width>30 && temp.center.y>5 && temp.center.x>5 && Math.abs(1- temp.size.width/temp.size.height)<0.6)
				 { 
					 //Core.circle(img, new Point(temp.center.x, temp.center.y), (int)temp.size.width/2, new Scalar(0,255,0));
					 return temp.boundingRect(); //return pupil contour
				 
				 }
			}
		 }
		//if there is no pupil, return null
		return null;
	}
	
	public  Rect findEyeArea(Mat img)
	{
		
		return   new Rect((int)(img.width()*0.15),(int)( img.height()*0.3),(int)(img.width()*0.7),(int)(img.height()*0.45));
	}
	
	public  Rect detectPupil(Mat img)
	{
		int size = (int)(img.height()*0.3);
		Rect eye_only_rectangle = findEyeArea(img);
		Core.MinMaxLocResult mmG = Core.minMaxLoc(img.submat(eye_only_rectangle));
    	Point iris = new Point();   	
		
    	iris.x = mmG.minLoc.x + eye_only_rectangle.x;
    	iris.y = mmG.minLoc.y + eye_only_rectangle.y;
    	Core.circle(_image, iris,2, new Scalar(0, 0, 255, 0),2);
    	//return  new Rect((int)(iris.x - size/2),(int)(iris.y - size /2) ,size,size);
    	return eye_only_rectangle;
	}

}
