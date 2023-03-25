package entity;

/**
 * Entity for student.
 */
public class Student {

  private String studentId;
  private String studentName;
  private String department;
  private int mark;

  /**
   * Constructor of student.
   *
   * @param studentId id of the student.
   * @param studentName full name of the student.
   * @param department department of the student.
   * @param mark mark of the student.
   */
  public Student(
    String studentId,
    String studentName,
    String department,
    int mark
  ) {
    this.studentId = studentId;
    this.studentName = studentName;
    this.department = department;
    this.mark = mark;
  }

  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }

  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public int getMark() {
    return mark;
  }

  public void setMark(int mark) {
    this.mark = mark;
  }
}
