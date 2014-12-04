import java.io.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;


class FileIO {

	/**
	 * Gets all data from file
	 * @param f
	 * File to read
	 * @return
	 * Collection<String> (specifically a HashSet to prevent duplicate Students) containing rest of file data,
	 */
	private static Collection<String> getData(File f) {
		HashSet<String> lines = new HashSet<>();
		try (Scanner j = new Scanner(f)) {
			while(j.hasNextLine()) {
				lines.add(j.nextLine());
			}
		} catch(Exception e) {
			System.err.println("File is empty or could not be read!");
		}
		return lines;
	}

	/**
	 * Makes student object from Map and String data
	 * @param data
	 * Strings containing data needed
	 * @return
	 * A student object
	 */
	private static Student makeStudent(String... data) {
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

		p.setGradQualified(data[9].toLowerCase().contains("t"));
		p.setAdvDate(data[10]);
		p.setSubDate(data[11]);
		
		return p;
	}

	/**
	 * Converts a StudentList back to a set of strings
	 * @param sl
	 * StudentList to be converted back to its string representations
	 * @return
	 * A set identical to the input that would form this list
	 */
	private static Set<String> readList(StudentList sl) {
		Set<String> output = new HashSet<>();

		for (Student s : sl.getStudentList()) {
			String j = "";
			for (String e : s.toArray()) {
				if (e.isEmpty())
					e = " ";
				j += e + ",";
			}

			output.add(j.substring(0, j.length() - 1)); //substring needed to drop final comma as it is not part of csv
		}

		return output;
	}

	/**
	 * Reads a file and parses information
	 * @param f
	 * File to be read. Template is extracted from this file, as well as file data.
	 * @return
	 * Student list containing all recognized information from file
	 */
	public static HashSet<Student> readFile(File f) {
		HashSet<Student> sl = new HashSet<>();
		Collection<String> data = getData(f); //Gets rest of file data
		//parses each line of file data and adds resulting student object to hashset
		sl.addAll(data.stream().map(l -> makeStudent(l.split(","))).collect(Collectors.toList()));
		return sl;
	}
	
	/*
	** Creates file with specified name and fills with text content
	** Warning: Overwrites file if it exists
	*/
	private static void writeFile(String fileName, Iterable<String> text) {
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)))) {
			for (String line : text)
				writer.write(line + "\n");
		} catch(Exception e) {
			System.err.println("Error writing to " + fileName);
		}
	}

	/**
	 * Write a student list to a file
	 *
	 * @param fileName File name of file
	 * @param sl       Student list to write to the file
	 */
	public static void writeFile(String fileName, StudentList sl) {
		writeFile(fileName, readList(sl));
	}

}
