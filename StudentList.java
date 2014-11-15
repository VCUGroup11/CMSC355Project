import java.util.ArrayList;
import java.util.Collections;

//
//Still needs method to remove a student from the list.
//


public class StudentList {
	
	private ArrayList<Student> studentList;// = new ArrayList<Student>();
	
	public StudentList() {
		this.setStudentList(new ArrayList<Student>(25));
	}
	
	public StudentList(ArrayList<Student> list) {
		this.setStudentList(list);
	}

	public ArrayList<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(ArrayList<Student> studentList) {
		this.studentList = studentList;
	}
	
	public void addStudent(Student s) {
		for (Student s1 : this.studentList) {
			if (s1.compareTo(s) == 0) {
				System.out.println("Student: " + s.toString() + " \nIs already in the list of students");
				return;
			}
		}
		this.studentList.add(s);
		Collections.sort(this.studentList);
		System.out.println("Student: " + s.toString() + " \nhas been added to the list of students");
	}

}
