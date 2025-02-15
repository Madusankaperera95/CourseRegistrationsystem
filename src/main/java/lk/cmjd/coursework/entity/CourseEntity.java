package lk.cmjd.coursework.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;
import org.mindrot.jbcrypt.BCrypt;


@Entity
@Table(name = "courses")
public class CourseEntity {
    @Id
    @Column(name = "CourseId")
    private String courseId;
    @Column(name = "CourseName")
    private String courseName;

    @Column(name = "CreditHours")
    private String creditHours;

    @ManyToOne
    @JoinColumn(name="department_id",nullable = false)
    private DepartmentEntity department;


    @Column(name = "MaximumCapacity")
    private String maximumCapacity;

    @ManyToOne
    @JoinColumn(name = "prerequisite_id",nullable = false)
    private PreRequisiteEntity preRequisite;

    @Column(name = "description")
    @Lob
    private String description;

    public CourseEntity() {
    }

    public CourseEntity(String courseId, String courseName, String creditHours, DepartmentEntity department, String maximumCapacity, PreRequisiteEntity preRequisite,String description) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.department = department;
        this.maximumCapacity = maximumCapacity;
        this.preRequisite = preRequisite;
        this.description = description;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(String creditHours) {
        this.creditHours = creditHours;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public String getMaximumCapacity() {
        return maximumCapacity;
    }

    public void setMaximumCapacity(String maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }

    public PreRequisiteEntity getPreRequisite() {
        return preRequisite;
    }

    public void setPreRequisite(PreRequisiteEntity preRequisite) {
        this.preRequisite = preRequisite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));
    }
}
