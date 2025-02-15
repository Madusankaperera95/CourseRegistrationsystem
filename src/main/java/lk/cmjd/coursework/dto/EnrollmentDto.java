package lk.cmjd.coursework.dto;

import lk.cmjd.coursework.util.Enums.SemesterTypes;
import lk.cmjd.coursework.util.Enums.Status;

public class EnrollmentDto {

    private String enrollId;
    private String courseId;

    private SemesterTypes semester;

    private Status status;

    private String studentId;

    public EnrollmentDto() {
    }

    public EnrollmentDto(String courseId, SemesterTypes semester, Status status, String studentId) {
        this.courseId = courseId;
        this.semester = semester;
        this.status = status;
        this.studentId = studentId;
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
}
