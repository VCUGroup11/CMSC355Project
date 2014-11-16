import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;


public class FileIO {
	private Scanner s;
	
	private File currentFile;

	//TODO eventually utilize a Map to switch between formats
	//public static final HashMap DEFAULT_FORMAT = new HashMap<String,Integer>();
	
	//Lists default format
	
	/*public static final String[] DEFAULT_FILE_FORMAT = 
		{"lastname","firstname",
			"idnum","grade",
			"majorGPA","totalGPA",
			"majorCrd","upperCrd","totalCrd"};
			
			*/

	/**
	 * Default Constructor, do not recommend using
	 */
	public FileIO() {
		start();
	}
	/**
	 * Starts FileIO with a specific file to read
	 * @param f
	 * CSV file containing values to read in as a StudentList
	 */
	public FileIO(File f) {
		currentFile = f;
		start();
	}
	
	private void start() {
		if(s!=null) {
			stop();
		}
		try {
			s = new Scanner(currentFile);
		} catch (FileNotFoundException e) {
			System.err.println("Could not read file (start method, FileIO)");
			e.printStackTrace();
		}
	}
	private void stop() {
		if(s!=null) {
			s.close();
		}
	}
	
	public void setFile(File newFile) {
		currentFile = newFile;
	}
	public File getFile() {
		return currentFile;
	}
	
	//VERY basic readFile function
	public StudentList readFile() {
		StudentList sl = new StudentList();
		s.nextLine(); //skip first template line
		while(s.hasNextLine()) {
			String[] data = s.nextLine().split(","); //raw data containing all comma seperated values of line
			Student p = new Student();
			
			p.setFirstName(data[0]);
			p.setLastName(data[1]);
			p.setIdNum(data[2]);
			p.setGrade(data[3]);
			
			p.setMajorGPA(Float.parseFloat(data[4]));
			p.setTotalGPA(Float.parseFloat(data[5]));
			p.setMajorCrd(Integer.parseInt(data[6]));
			p.setUpperCrd(Integer.parseInt(data[7]));
			p.setTotalCrd(Integer.parseInt(data[8]));
			
			sl.add(p);
		}
		//TODO optimize and generalize
		return sl;
	}
	
	
	
	

}
