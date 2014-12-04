import java.io.File;
import java.util.*;
import javax.swing.JOptionPane;

public class StudentList implements Set<Student> {

    public static final String DEFAULT_FILENAME = "studentList.csv";
    public static final String DEFAULT_TEMPNAME = "temp.csv";
    private Set<Student> studentList;

    public StudentList() {
        this.setStudentList(new HashSet<>());
    }

    public StudentList(Set<Student> list) {
        this.setStudentList(new HashSet<Student>(list));
    }

    public StudentList(File file) {
        this.setStudentList(FileIO.readFile(file));
    }

    public Set<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(Set<Student> studentList) {
        this.studentList = studentList;
    }

    public void addStudent(Student s) {
        System.out.println("Adding student: " + s.toString() + " \nto the list of students");
        if (this.containsStudent(s)) {
            System.out.println("Student: " + s.toString() + " \nIs already in the list of students");
            return;
        } else {
            this.add(s);
        }
        System.out.println("Student: " + s.toString() + " \nhas been added to the list of students");
    }//End addStudent()

    public void addStudent(File importFile) {
        this.addAll(FileIO.readFile(importFile));
    }//End addStudent()

    public void removeStudent(Student s) {
        System.out.println("Removing student: " + s.toString() + " \nfrom the list of students");
        if (this.contains(s)) {
            this.remove(s);
            System.out.println("Student: " + s.toString() + " \nwas removed from the list of students");
        } else {
            System.out.println("Student: " + s.toString() + " \nwas not found in the list of students");
        }
    }//End removeStudent()

    @Override
    public boolean add(Student s) {
        if (this.containsStudent(s)) {
            return true;
        } else {
            this.studentList.add(s);
        }
        return this.containsStudent(s);
    }

    @Override
    public boolean addAll(Collection<? extends Student> s) {
        for (Student s1 : s) {
            if (!this.containsStudent(s1)) {
                this.studentList.add(s1);
            }
        }
        return this.studentList.containsAll(s);
    }

    @Override
    public void clear() {
        int i = javax.swing.JOptionPane.showConfirmDialog(null,
                "Would you like to remove all students from the list?\nWARNING:  THIS CANNOT BE UNDONE!!!!",
                "Remove All Students From List?",
                JOptionPane.YES_NO_OPTION);
        if (i == JOptionPane.YES_OPTION) {
        this.studentList.clear();
        }
    }

    @Override
    public boolean contains(Object s) {
        return this.studentList.contains(s);
    }

    public boolean containsStudent(Student s) {
        for (Student s1 : this) {
            if ((s.getFirstName().equalsIgnoreCase(s1.getFirstName())) && (s.getLastName().equalsIgnoreCase(s1.getLastName())) && (s.getIdNum().equalsIgnoreCase(s1.getIdNum()))) {
                return true;
            }
        }
        return false;
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
        int i = javax.swing.JOptionPane.showConfirmDialog(null,
                "Would you like to remove this student from the list?\n" + s.toString(),
                "Remove Student?",
                JOptionPane.YES_NO_OPTION);
        if (i == JOptionPane.YES_OPTION) {
        this.studentList.remove(s);
        }
        
        return !this.contains(s);
    }

    @Override
    public boolean removeAll(Collection<?> s) {
        int i = javax.swing.JOptionPane.showConfirmDialog(null,
                "Would you like to remove all students from the list?\nWARNING:  THIS CANNOT BE UNDONE!!!!",
                "Remove All Students From List?",
                JOptionPane.YES_NO_OPTION);
        if (i == JOptionPane.YES_OPTION) {
        this.studentList.removeAll(s);
        }
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
    public Object[][] toArray() {
        Object[][] output = new Object[studentList.size()][];
        int i = 0;
        for (Student aStudent : studentList) {
            output[i] = aStudent.toArray();
            i++;
        }
        return output;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.studentList.toArray(a);
    }

    public String getAdvisingReport() {
        String result = String.format("%-18s %-18s %-18s %-18s %s", "Name", "VID", "Grade", "Academic Advising", "Date" + "\n");
        for (Student stu : studentList) {
            result += String.format("%-18s %-18s %-18s", stu.getLastName() + ", " + stu.getFirstName(), stu.getIdNum(), stu.getGrade());
            if (stu.getAcAdvising()) {
                result += String.format("%-18s", "Yes");
            } else {
                result += String.format("%-18s", "No");
            }
            result += stu.getAdvDate() + "\n";
        }
        return result;
    }

    /**
     *
     * @param studentName "LastName, FirstName"
     * @return
     */
    public String getGradReport(String studentName) {
        if (studentName.contains("All") || studentName.contains("all")) {
            return this.getGradReport();
        }
        return this.findStudent(studentName).getGradReport();
    }

    public String getGradReport() {
        String report = "              Qualified Student Graduation Reports\n";
        report = report + "******************************************************************\n";
        report = report + this.getGradQuReport();
        report = report + "******************************************************************\n";
        report = report + "            Unqualified Student Graduation Reports\n";
        report = report + "******************************************************************\n";
        report = report + this.getGradUnQuReport();
        report = report + "******************************************************************\n";
        return report;
    }

    private String getGradQuReport() {
        String result = "";
        for (Student aStudent : studentList) {
            if (!(aStudent.getSubDate().isEmpty()) && aStudent.isGradQualified()) {
                result += aStudent.getGradReport() + "\n";
            }
        }
        return result;
    }

    private String getGradUnQuReport() {
        String result = "";
        for (Student aStudent : studentList) {
            if (aStudent.getSubDate().isEmpty() || !(aStudent.isGradQualified())) {
                result += aStudent.getGradReport() + "\n";
            }
        }
        return result;
    }

    public Student findStudent(String name) {
        String last = name.substring(0, name.indexOf(',')).trim();
        String first = name.substring(name.indexOf(',') + 1).trim();
        Student found = new Student();
        for (Student s : this.getStudentList()) {
            if (s.getLastName().equalsIgnoreCase(last) && s.getFirstName().equalsIgnoreCase(first)) {
                found = s;
            }
        }
        return found;
    }

    /**
     *
     * @return returns array of names formated as "Lastname, Firstname"
     */
    public String[] getGradNames() {
        Student[] n = new Student[this.getStudentList().size()];
        n = this.getStudentList().toArray(n);
        ArrayList<String> names = new ArrayList<String>();
        names.add("All Students");
        for (Student s : n) {
            if (!(s.getSubDate().equalsIgnoreCase(""))) {
                names.add(s.getLastName() + ", " + s.getFirstName());
            }
        }
        String[] temp = {};
        temp = names.toArray(temp);
        return temp;
    }

    public String[] getNames() {
        Student[] n = new Student[this.getStudentList().size()];
        n = this.getStudentList().toArray(n);
        ArrayList<String> names = new ArrayList<String>();
        for (Student s : n) {
            names.add(s.getLastName() + ", " + s.getFirstName());
        }
        String[] temp = {};
        temp = names.toArray(temp);
        return temp;
    }

    public void saveInfo(String fileName) {
        FileIO.writeFile(fileName, this);
    }

    public void saveInfo() {
        saveInfo(DEFAULT_TEMPNAME);
    }

    public void saveInfoPermanently() {
        saveInfo(DEFAULT_FILENAME);
    }

    // Remove student from set and rewrite file without student
    // (Deletes student from memory AND file)
    public void deleteStudent(Student student, String fileName) {
        remove(student);
        saveInfo(fileName);
    }

    /**
     *
     * @param name formated "lastname,firstname"
     */
    public void deleteStudent(String name) {
        this.deleteStudent(this.findStudent(name), DEFAULT_FILENAME);
    }

    public void gradApp(String name, float mGPA, float tGPA, int mCrd, int uCrd, int tCrd) {
        this.findStudent(name).gradApp(mGPA, tGPA, mCrd, uCrd, tCrd);
    }
}
