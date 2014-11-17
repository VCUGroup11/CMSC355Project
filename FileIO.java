import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;


public class FileIO {
	private File currentFile;
	
	/*
	 * currentTemplate provides a way to go between a header string and an index corresponding to the right entry
	 */
	private Map<String, Integer> currentTemplate = new HashMap<>();
	
	/**
	 * Used to classify elements of each template. Order doesn't particularly matter
	 */
	public static final String[] DEFAULT_FILE_FORMAT = 
		{"fn","ln",
			"idnum","grade",
			"mGPA","tGPA",
			"mCrd","uCrd","tCrd"};
	/**
	 * Default constructor, useful if file is not determined yet
	 */
	public FileIO() {
	}
	/**
	 * Starts FileIO with a specific file to read
	 * @param f
	 * CSV file containing values to read in as a StudentList
	 */
	public FileIO(File f) {
		currentFile = f;
	}
	
	/**
	 * Call to prevent resource leaks. Will be automated in a future revision
	 * For the most part, should not be necessary if using static calls.
	 */
	
	public void setFile(File newFile) {
		currentFile = newFile;
	}
	public File getFile() {
		return currentFile;
	}
	
	/**
	 * Sets template to one defined by a Map. Helper method for the most part.
	 * @param t
	 * Map containing the elements which map to different indexes
	 */
	private void setTemplate(Map<String,Integer> t) {
		currentTemplate = t;
	}
	public static Map<String,Integer> getTemplate(String... csvHeader) {
		HashMap<String, Integer> t = new HashMap<>();
		for(String e:csvHeader) {
			t.put(e, Arrays.binarySearch(DEFAULT_FILE_FORMAT, e));
		}
		return t;
	}
	
	/**
	 * Gets template from a file. Tries to handle any errors along the way
	 * @param f
	 * File to read, make sure it's csv formatted and not empty
	 * @return
	 * A Map (specifically a HashMap) containing template data from the csv header
	 * parses line 0 of document.
	 */
	private static Map<String,Integer> getTemplate(File f){
		Scanner j = null;
		
		try {
			j = new Scanner(f);
		} catch (FileNotFoundException e) {
			System.err.println("Cannot find file! (Static access, FileIO getTemplate(f))");
			e.printStackTrace();
		}
		
		String[] templateData;
		if(j.hasNextLine()) {
			templateData = j.nextLine().split(",");
		}else {
			System.err.println("File is empty! (Static access, FileIO getTemplate(f))");
			templateData = null;
		}
		
		j.close();
		return getTemplate(templateData);
	}
	/**
	 * Gets all data from file besides template
	 * @param f
	 * File to read
	 * @return
	 * Collection<String> (specifically a HashSet to prevent duplicate Students) containing rest of file data,
	 * lines 1 -> n
	 */
	private static Collection<String> getData(File f) {
		Scanner j = null;
		HashSet<String> lines = new HashSet<>();
		try {
			j = new Scanner(f); j.nextLine();
			while(j.hasNextLine()) {
				lines.add(j.nextLine());
			}
		} catch(Exception e) {
			System.err.println("Could not read files! (Static access, FileIO getData(f) || File is Empty!");
			e.printStackTrace();
		}
		j.close();
		return lines;
	}
	
	public Map<String, Integer> getTemplate() {
		return currentTemplate;
	}
	/**
	 * Makes student object from Map and String data
	 * @param template
	 * @param data
	 * @return
	 */
	private static Student makeStudent(Map<String, Integer> template, String... data) {
		Student p = new Student();
		
		p.setFirstName(data[template.get("fn")]);
		p.setLastName(data[template.get("ln")]);
		p.setIdNum(data[template.get("idNum")]);
		p.setGrade(data[template.get("grade")]);
		
		p.setMajorGPA(Float.parseFloat(data[template.get("mGPA")]));
		p.setTotalGPA(Float.parseFloat(data[template.get("tGPA")]));
		p.setMajorCrd(Integer.parseInt(data[template.get("mCrd")]));
		p.setUpperCrd(Integer.parseInt(data[template.get("uCrd")]));
		p.setTotalCrd(Integer.parseInt(data[template.get("tCrd")]));
		
		return p;
	}
	
	/**
	 * Reads files based on FileIO object. Use for reading multiple files with the same object,
	 * or with different templates
	 * @return
	 * HashSet containing all the students in the file.
	 */
	public HashSet<Student> readFile() {
		HashSet<Student> sl = new HashSet<>();
		setTemplate(getTemplate(currentFile));
		Collection<String> data = getData(currentFile);
		for(String l:data)
			sl.add(makeStudent(currentTemplate,l));
		return sl;
	}
	/**
	 * Static implementation of FileIO's readFile command
	 * @param f
	 * File to be read. Template is extracted from this file, as well as file data.
	 * @return
	 * Student list containing all recognized information from file
	 */
	public static HashSet<Student> readFile(File f) {
		HashSet<Student> sl = new HashSet<>();
		Map<String,Integer> template = getTemplate(f); //Gets template from file
		Collection<String> data = getData(f); //Gets rest of file data
		for(String l:data)
			sl.add(makeStudent(template,l)); //parses each line of file data and adds resulting student object to hashset
		return sl;
	}
	
	/**
	 * Reads list and passes back a StudentList
	 * @param f
	 * File to be read
	 * @return
	 * Student list with a Set containing all students from list
	 */
	public static StudentList readList(File f) {
		HashSet<Student> n = readFile(f);
		return new StudentList(n);		
	}
	
	
	

}
