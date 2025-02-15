package lk.cmjd.coursework.dto;

import java.time.LocalDate;

public class StudentDto {
    private String studentId;

    private String studentName;

    private String program;

    private String email;

    private LocalDate dob;

    private String password;

    private int year;

    private int ContactNumber;

    public StudentDto() {
    }

    public StudentDto(String studentId, String studentName, String program, String email, LocalDate dob, String password, int year, int contactNumber) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.program = program;
        this.email = email;
        this.dob = dob;
        this.password = password;
        this.year = year;
        ContactNumber = contactNumber;
    }

    public StudentDto(String studentId, String studentName, String program, String email, LocalDate dob, int year, int contactNumber) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.program = program;
        this.email = email;
        this.dob = dob;
        this.year = year;
        ContactNumber = contactNumber;
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

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public int getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(int contactNumber) {
        ContactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", program='" + program + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", password='" + password + '\'' +
                ", year=" + year +
                ", ContactNumber=" + ContactNumber +
                '}';
    }
}
