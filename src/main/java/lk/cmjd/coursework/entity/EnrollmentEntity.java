package lk.cmjd.coursework.entity;

import jakarta.persistence.*;
import lk.cmjd.coursework.util.Enums.SemesterTypes;
import lk.cmjd.coursework.util.Enums.Status;

@Entity
@Table(name = "enrollments")
public class EnrollmentEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int enrollId;

    @ManyToOne
    @JoinColumn(name="course_id",nullable = false)
    private CourseEntity courseId;

    @Column(name = "semester_type", columnDefinition = "VARCHAR(200)")
    @Enumerated(EnumType.STRING)
    private SemesterTypes semesterType;

    @Column(name = "status_type", columnDefinition = "VARCHAR(50)")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity studentEntity;

    @Column(name = "gpa", columnDefinition = "FLOAT(5,2)",nullable = true)
    private double gpa;

    @Column(name="grade",nullable = true)
    private String grade;

    public EnrollmentEntity() {
    }

    public EnrollmentEntity(int enrollId, CourseEntity courseId, SemesterTypes semesterType, Status status, StudentEntity studentEntity, double gpa, String grade) {
        this.enrollId = enrollId;
        this.courseId = courseId;
        this.semesterType = semesterType;
        this.status = status;
        this.studentEntity = studentEntity;
        this.gpa = gpa;
        this.grade = grade;
    }

    public EnrollmentEntity(int enrollId, CourseEntity courseId, SemesterTypes semesterType, Status status, StudentEntity studentEntity) {
        this.enrollId = enrollId;
        this.courseId = courseId;
        this.semesterType = semesterType;
        this.status = status;
        this.studentEntity = studentEntity;
    }

    public EnrollmentEntity(CourseEntity courseId, SemesterTypes semesterType, Status status, StudentEntity studentEntity) {
        this.courseId = courseId;
        this.semesterType = semesterType;
        this.status = status;
        this.studentEntity = studentEntity;
    }

    public int getEnrollId() {
        return enrollId;
    }

    public void setEnrollId(int enrollId) {
        this.enrollId = enrollId;
    }

    public CourseEntity getCourseId() {
        return courseId;
    }

    public void setCourseId(CourseEntity courseId) {
        this.courseId = courseId;
    }

    public SemesterTypes getSemesterType() {
        return semesterType;
    }

    public void setSemesterType(SemesterTypes semesterType) {
        this.semesterType = semesterType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }
}
