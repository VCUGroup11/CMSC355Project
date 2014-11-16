import java.io.File;
import java.io.FileNotFoundException;
<<<<<<< HEAD
import java.io.InputStream;
import java.util.HashMap;
=======
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
>>>>>>> e4001b867d03b7a35b0a675b67d46df23647a52e
import java.util.Scanner;


public class FileIO {
	private Scanner s;
	
	private File currentFile;
<<<<<<< HEAD

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
=======
	
	/*
	 * currentTemplate provides a way to go between a header string and an index corresponding to the right entry
	 */
	private Map<String, Integer> currentTemplate = new HashMap<>();
	
	/**
	 * Used to classify elements of each template
	 */
	public static final String[] DEFAULT_FILE_FORMAT = 
		{"ln","fn",
			"idnum","grade",
			"mGPA","tGPA",
			"mCrd","uCrd","tCrd"};

>>>>>>> e4001b867d03b7a35b0a675b67d46df23647a52e
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
<<<<<<< HEAD
	private void stop() {
=======
	/**
	 * Call to prevent resource leaks. Will be automated in a future revision
	 * TODO
	 */
	public void stop() {
>>>>>>> e4001b867d03b7a35b0a675b67d46df23647a52e
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
	
<<<<<<< HEAD
	//VERY basic readFile function
	public StudentList readFile() {
		StudentList sl = new StudentList();
		s.nextLine(); //skip first template line
=======
	/**
	 * Set template to one defined  by a csv header. DEFAULT_FILE_FORMAT provides the order and the check strings used
	 * @param csvHeader
	 * String array containing the comma separated values that need to be mapped 
	 */
	public void setTemplate(String... csvHeader) {
		setTemplate(getTemplate(csvHeader));
	}
	public void setTemplate(Map<String,Integer> t) {
		currentTemplate = t;
	}
	public static Map<String,Integer> getTemplate(String... csvHeader) {
		HashMap<String, Integer> t = new HashMap<>();
		
		for(String e:csvHeader) {
			t.put(e, Arrays.binarySearch(DEFAULT_FILE_FORMAT, e));
		}
		
		return t;
	}
	public Map<String, Integer> getTemplate() {
		return currentTemplate;
	}
	
	/**
	 * Reads files based on FileIO object. Use for reading multiple files with the same object,
	 * or with different templates
	 * @return
	 * HashSet containing all the students in the file.
	 */
	public HashSet<Student> readFile() {
		HashSet<Student> sl = new HashSet<>();
		
		String[] template = s.nextLine().split(","); //read template line (first line of document)
		setTemplate(template);
		
>>>>>>> e4001b867d03b7a35b0a675b67d46df23647a52e
		while(s.hasNextLine()) {
			String[] data = s.nextLine().split(","); //raw data containing all comma seperated values of line
			Student p = new Student();
			
<<<<<<< HEAD
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
	
=======
			p.setFirstName(data[currentTemplate.get("fn")]);
			p.setLastName(data[currentTemplate.get("ln")]);
			p.setIdNum(data[currentTemplate.get("idNum")]);
			p.setGrade(data[currentTemplate.get("grade")]);
			
			p.setMajorGPA(Float.parseFloat(data[currentTemplate.get("mGPA")]));
			p.setTotalGPA(Float.parseFloat(data[currentTemplate.get("tGPA")]));
			p.setMajorCrd(Integer.parseInt(data[currentTemplate.get("mCrd")]));
			p.setUpperCrd(Integer.parseInt(data[currentTemplate.get("uCrd")]));
			p.setTotalCrd(Integer.parseInt(data[currentTemplate.get("tCrd")]));
			//TODO create a general method that assigns these values to a student obj
			//lessens interclass coupling
			sl.add(p);
		}
		
		return sl;
	}
	/**
	 * Static implementation of FileIO's readFile command. Will be deprecated soon and merged with non-static implementation
	 * @param f
	 * File to be read. Template is extracted from this file.
	 * @return
	 * Student list containing all recognized information from file
	 * @throws FileNotFoundException 
	 * Scanner calls are not checked in this implementation
	 */
	public static HashSet<Student> readFile(File f) throws FileNotFoundException {
		HashSet<Student> sl = new HashSet<>();
		Scanner m = new Scanner(f);
		String[] template = m.nextLine().split(","); //read template line (first line of document)
		Map<String,Integer> ct = getTemplate(template); //turn template line into Map to use as template
		
		while(m.hasNextLine()) {
			String[] data = m.nextLine().split(","); //raw data containing all comma seperated values of line
			Student p = new Student();
			
			/*
			 * Sets each part of student equal to the correct element of the data string
			 * Matches are determined by the map
			 */
			p.setFirstName(data[ct.get("fn")]);
			p.setLastName(data[ct.get("ln")]);
			p.setIdNum(data[ct.get("idNum")]);
			p.setGrade(data[ct.get("grade")]);
			
			p.setMajorGPA(Float.parseFloat(data[ct.get("mGPA")]));
			p.setTotalGPA(Float.parseFloat(data[ct.get("tGPA")]));
			p.setMajorCrd(Integer.parseInt(data[ct.get("mCrd")]));
			p.setUpperCrd(Integer.parseInt(data[ct.get("uCrd")]));
			p.setTotalCrd(Integer.parseInt(data[ct.get("tCrd")]));
			//TODO create a general method that assigns these values to a student obj
			//lessens interclass coupling
			sl.add(p);
		}
		m.close();
		return sl;
	}
	
	/**
	 * Reads list and passes back a StudentList
	 * @param f
	 * File to be read
	 * @return
	 * Student list with a Set containing all students from list
	 * @throws FileNotFoundException
	 * Could not find file specified -- comes from File class
	 */
	public static StudentList readList(File f) throws FileNotFoundException {
		
		HashSet<Student> n = readFile(f);
		return new StudentList(n);
		
		
	}
>>>>>>> e4001b867d03b7a35b0a675b67d46df23647a52e
	
	
	

}
