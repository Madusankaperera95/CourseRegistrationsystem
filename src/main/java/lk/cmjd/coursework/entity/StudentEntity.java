package lk.cmjd.coursework.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="students")
public class StudentEntity {

    @Id
    @Column(name="student_id")
    private String StudentId;

    @Column(name="student_name")
    private String StudentName;

    @Column(name="student_email")
    private String Studentemail;

    @Column(name="student_dob")
    private LocalDate studentDob;

    @Column(name="program")
    private String program;

    @Column(name="year")
    private int year;

    @Column(name="contact_number")
    private int contactNumber;

    @OneToOne
    @JoinColumn(name="user_id",nullable = false)
    private UserEntity user;

    public StudentEntity() {
    }

    public StudentEntity(String studentId, String studentName, String studentemail, LocalDate studentDob, String program, int year, int contactNumber, UserEntity user) {
        StudentId = studentId;
        StudentName = studentName;
        Studentemail = studentemail;
        this.studentDob = studentDob;
        this.program = program;
        this.year = year;
        this.contactNumber = contactNumber;
        this.user = user;
    }

    public String getStudentemail() {
        return Studentemail;
    }

    public void setStudentemail(String studentemail) {
        Studentemail = studentemail;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public LocalDate getStudentDob() {
        return studentDob;
    }

    public void setStudentDob(LocalDate studentDob) {
        this.studentDob = studentDob;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "StudentId='" + StudentId + '\'' +
                ", StudentName='" + StudentName + '\'' +
                ", Studentemail='" + Studentemail + '\'' +
                ", studentDob=" + studentDob +
                ", program='" + program + '\'' +
                ", year=" + year +
                ", contactNumber=" + contactNumber +
                '}';
    }
}
