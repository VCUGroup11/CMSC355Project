import java.util.Date;
/**
 * the Student class. Holds all the student's information and has a method to get the grad report for the student.
 */
public class Student {

    private String firstName, lastName, idNum, grade, advDate, subDate;
    private float majorGPA, totalGPA;
    private int majorCrd, upperCrd, totalCrd;
    private boolean gradQualified, acAdvising;

    public Student() {
        this.setFirstName("New");
        this.setLastName("Student");
        this.setIdNum("Vxxxxxxxx");
        this.setGrade("");
        this.setMajorGPA(0);
        this.setTotalGPA(0);
        this.setMajorCrd(0);
        this.setUpperCrd(0);
        this.setTotalCrd(0);
        this.setGradQualified(false);
        this.setAdvDate("");
        this.setSubDate("");
        this.setAcAdvising(false);
    }

    public Student(String fn, String ln, String id, String g, float mG, float tG, int mC, int uC, int tC, String aDate, String sDate) {
        this.setFirstName(fn.trim());
        this.setLastName(ln.trim());
        this.setIdNum(id.trim());
        this.setGrade(g.trim());
        this.setMajorGPA(mG);
        this.setTotalGPA(tG);
        this.setMajorCrd(mC);
        this.setUpperCrd(uC);
        this.setTotalCrd(tC);
        this.setGradQualified(this.qualifyTest());
        this.setAdvDate(aDate);
        this.setSubDate(sDate);
        this.setAcAdvising(false);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String fn) {
        this.firstName = fn;
    }

    public boolean getAcAdvising() {
        return acAdvising;
    }

    public void setAcAdvising(boolean ac) {
        acAdvising = ac;
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

    float getMajorGPA() {
        return this.majorGPA;
    }

    public void setMajorGPA(float majorGPA) {
        this.majorGPA = majorGPA;
    }

    float getTotalGPA() {
        return this.totalGPA;
    }

    public void setTotalGPA(float totalGPA) {
        this.totalGPA = totalGPA;
    }

    int getMajorCrd() {
        return this.majorCrd;
    }

    public void setMajorCrd(int majorCrd) {
        this.majorCrd = majorCrd;
    }

    int getUpperCrd() {
        return this.upperCrd;
    }

    public void setUpperCrd(int upperCrd) {
        this.upperCrd = upperCrd;
    }

    int getTotalCrd() {
        return this.totalCrd;
    }

    public void setTotalCrd(int totalCrd) {
        this.totalCrd = totalCrd;
    }

    public void gradApp(float mG, float tG, int mC, int uC, int tC) {
        this.setMajorGPA(mG);
        this.setTotalGPA(tG);
        this.setMajorCrd(mC);
        this.setUpperCrd(uC);
        this.setTotalCrd(tC);

        //Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        //Date date = cal.getTime();
        //this.setSubDate(date.getMonth() + "/" + date.getDate() + "/" + date.getYear());
        Date date = new Date();
        this.setSubDate(date.toString());
    }

    public String getAdvDate() {
        return advDate;
    }

    public void setAdvDate(String aDate) {
        advDate = aDate;
    }

    public String getSubDate() {
        return this.subDate;
    }

    public void setSubDate(String sDate) {
        this.subDate = sDate;
    }

    public boolean isGradQualified() {
        return this.qualifyTest();
    }

    public void setGradQualified(boolean gradQualified) {
        this.gradQualified = gradQualified;
    }

    //Checks if student is qualified to graduate
    boolean qualifyTest() {
        return (this.getMajorGPA() >= 2.0) && (this.getTotalGPA() >= 2.0) && (this.getMajorCrd() >= 45) && (this.getUpperCrd() >= 45) && (this.getTotalCrd() >= 120);
    }

    String unqualReason() {
        String reason = "";
        if (!this.qualifyTest()) {
           reason = "Unqualified Reasons:\n";
        }
        if (this.getMajorCrd() < 45) {
            reason = reason + "Less than 45 Major Credits\n";
        }
        if (this.getUpperCrd() < 45) {
            reason = reason + "Less than 45 Upper Level Credits\n";
        }
        if (this.getTotalCrd() < 120) {
            reason = reason + "Less than 120 Total Credits\n";
        }
        if (this.getMajorGPA() < 2.0) {
            reason = reason + "Major GPA is less than 2.0\n";
        }
        if (this.getTotalGPA() < 2.0) {
            reason = reason + "Total GPA is less than 2.0\n";
        }
        return reason;
    }//End unqualReason()

    public String getGradReport() {
        String report = "----------------------  Student Graduation Report ----------------------\n";
        report = report + this.getLastName() + ", " + this.getFirstName() + " : " + this.getIdNum() + "\n";
        report = report + "Grade: " + this.getGrade() + "\n";
        report = report + "Graduation Application Submission Date: " + this.getSubDate() + "\n";
        report = report + "Major GPA: " + this.getMajorGPA() + "\n";
        report = report + "Total GPA: " + this.getTotalGPA() + "\n";
        report = report + "Major Credits: " + this.getMajorCrd() + "\n";
        report = report + "Upper Level Credits: " + this.getUpperCrd() + "\n";
        report = report + "Total Credits: " + this.getTotalCrd() + "\n";
        if (this.qualifyTest()) {
            report = report + "Graduation Status: Qualified to Graduate\n";
        } else {
            report = report + "Graduation Status: Unqualified to Graduate\n";
        }
        report = report + this.unqualReason() + "\n";
        report = report + "----------------------------------------------------------------------------------------";
        return report;
    }

    @Override
    public String toString() {
        return this.getLastName() + ", " + this.getFirstName() + ", " + this.getIdNum() + ", " + this.getGrade();
    }

    public String[] toArray() {
        return new String[]{firstName, lastName, idNum, grade, "" + majorGPA, "" + totalGPA, "" + majorCrd, "" + upperCrd, "" + totalCrd, "" + gradQualified, advDate, subDate};
    }

}
