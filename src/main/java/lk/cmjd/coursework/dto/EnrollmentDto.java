package lk.cmjd.coursework.dto;

import lk.cmjd.coursework.util.Enums.SemesterTypes;
import lk.cmjd.coursework.util.Enums.Status;

public class EnrollmentDto {

    private String enrollId;
    private String courseId;

    private SemesterTypes semester;

    private Status status;

    private String studentId;

    private double gpa;

    private String grade;

    private String studentName;

    private String courseName;


    public EnrollmentDto() {
    }

    public EnrollmentDto(String enrollId,double gpa) {
        this.enrollId = enrollId;
        this.gpa = gpa;

    }

    public EnrollmentDto(String enrollId, String courseId, SemesterTypes semester, Status status, String studentId, double gpa, String grade, String studentName, String courseName) {
        this.enrollId = enrollId;
        this.courseId = courseId;
        this.semester = semester;
        this.status = status;
        this.studentId = studentId;
        this.gpa = gpa;
        this.grade = grade;
        this.studentName = studentName;
        this.courseName = courseName;
    }

    public EnrollmentDto(String enrollId, String courseId, SemesterTypes semester, Status status, String studentId, double gpa, String grade) {
        this.enrollId = enrollId;
        this.courseId = courseId;
        this.semester = semester;
        this.status = status;
        this.studentId = studentId;
        this.gpa = gpa;
        this.grade = grade;
    }

    public EnrollmentDto(String courseId, SemesterTypes semester, Status status, String studentId,double gpa,String grade) {
        this.courseId = courseId;
        this.semester = semester;
        this.status = status;
        this.studentId = studentId;
        this.gpa = gpa;
        this.grade = grade;
    }

    public String getEnrollId() {
        return enrollId;
    }

    public void setEnrollId(String enrollId) {
        this.enrollId = enrollId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public SemesterTypes getSemester() {
        return semester;
    }

    public void setSemester(SemesterTypes semester) {
        this.semester = semester;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
