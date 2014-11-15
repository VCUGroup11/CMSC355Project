
public class Student implements Comparable<Student> {

	private String firstName, lastName, idNum, grade;	
	
	public Student() {
		this.setFirstName("");
		this.setLastName("");
		this.setIdNum("");
		this.setGrade("");
	}
	
	public Student(String fn, String ln, String id, String g) {
		this.setFirstName(fn.trim());
		this.setLastName(ln.trim());
		this.setIdNum(id.trim());
		this.setGrade(g.trim());
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(String fn) {
		this.firstName = fn;
	}
		
	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName(String ln) {
		this.lastName = ln;
	}
	
	public String getIdNum() {
		return this.idNum;
	}
	
	public void setIdNum(String id) {
		this.idNum = id;
	}
	
	public String getGrade() {
		return this.grade;
	}
	
	public void setGrade(String g) {
		this.grade = g;
	}

	@Override
	public int compareTo(Student s) {
		return this.toString().compareTo(s.toString());
	}
	
	public String toString() {
		return this.getLastName() + ", " + this.getFirstName() + ", " + this.getIdNum() + ", " + this.getGrade();
	}
	
	
	
}
