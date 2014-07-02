import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;


public  class FileManager {
	
	public static ArrayList<String> findFiles(String path,  final String filetype)
	{
		File dir = new File(path);
		ArrayList<String> pathes = new ArrayList<>();			
		for(File file : dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String filename)
            { return filename.endsWith(filetype) || filename.endsWith(filetype.toUpperCase()) ; }
		}) )
		{
			pathes.add(file.getAbsolutePath());
		}
		
		return pathes;
	}
	

}
