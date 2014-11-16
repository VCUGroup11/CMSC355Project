import java.io.File;
import java.util.Collection;
import static java.util.Collections.list;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;


public class StudentList implements Set<Student> {
	
	private Set<Student> studentList;
	
	public StudentList() {
		this.setStudentList(new HashSet<Student>());
	}
	
	public StudentList(Set<Student> list) {
		this.setStudentList(new HashSet<Student>(list));
	}
        
        public StudentList(File file) {
                HashSet<Student> list = new HashSet<Student>();
                FileIO fIO = new FileIO(file);
                list = fIO.readFile();
                fIO.stop();
                
               /* try{
                    Scanner input = new Scanner(file);
                    while(input.hasNextLine()) {
                        Scanner line = new Scanner(input.nextLine());
                        line.useDelimiter(",");
                        list.add(new Student(
                            line.next(),
                            line.next(),
                            line.next(),
                            line.next(),
                            Float.parseFloat(line.next()),
                            Float.parseFloat(line.next()),
                            Integer.parseInt(line.next()),
                            Integer.parseInt(line.next()),
                            Integer.parseInt(line.next()),
                            line.next(),
                            line.next()));
                    }
                }catch(Exception e) {
                    //do nothing
                }*/
		this.setStudentList(list);
	}

	public Set<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(Set<Student> studentList) {
		this.studentList = studentList;
	}
	
	public void addStudent(Student s) {
		System.out.println("Adding student: " + s.toString() + " \nto the list of students");
		if (this.contains(s)) {
			System.out.println("Student: " + s.toString() + " \nIs already in the list of students");
			return;
		}
		else {
			this.add(s);
		}
		System.out.println("Student: " + s.toString() + " \nhas been added to the list of students");
	}//End addStudent()
	
	public void removeStudent(Student s) {
		System.out.println("Removing student: " + s.toString() + " \nfrom the list of students");
		if (this.contains(s)) {
			this.remove(s);
			System.out.println("Student: " + s.toString() + " \nwas removed from the list of students");
			return;
		}
		else {
			System.out.println("Student: " + s.toString() + " \nwas not found in the list of students");
		}
	}//End removeStudent()

	@Override
	public boolean add(Student s) {
		this.studentList.add(s);
		return this.contains(s);
	}

	@Override
	public boolean addAll(Collection<? extends Student> s) {
		return this.studentList.addAll(s);
	}

	@Override
	public void clear() {
		this.studentList.clear();		
	}

	@Override
	public boolean contains(Object s) {
		return this.studentList.contains(s);
	}

	@Override
	public boolean containsAll(Collection<?> s) {
		return this.studentList.containsAll(s);
	}

	@Override
	public boolean isEmpty() {
		return this.studentList.isEmpty();
	}

	@Override
	public Iterator<Student> iterator() {
		return this.studentList.iterator();
	}

	@Override
	public boolean remove(Object s) {
		this.studentList.remove(s);
		return !this.contains(s);
	}

	@Override
	public boolean removeAll(Collection<?> s) {
		this.studentList.removeAll(s);
		return !this.containsAll(s);
	}

	@Override
	public boolean retainAll(Collection<?> s) {
		this.studentList.retainAll(s);
		return this.containsAll(s);
	}

	@Override
	public int size() {
		return this.studentList.size();
	}

	@Override
	public Object[] toArray() {
		return this.studentList.toArray();
	}

	@Override
	public <T> T[] toArray(T[] s) {
		return this.studentList.toArray(s);
	}

}
