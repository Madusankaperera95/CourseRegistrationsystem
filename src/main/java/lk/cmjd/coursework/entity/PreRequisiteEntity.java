package lk.cmjd.coursework.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "pre_requisites")
public class PreRequisiteEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "PreRequisiteId")
    private int PreRequisiteId;

    @Column(name = "requisiteName",unique = true)
    private  String requisiteName;

    @OneToMany(mappedBy = "preRequisite",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CourseEntity> courses;

    public PreRequisiteEntity() {
    }

    public PreRequisiteEntity(String requisiteName) {
        this.requisiteName = requisiteName;
    }

    public PreRequisiteEntity(int preRequisiteId, String requisiteName) {
        PreRequisiteId = preRequisiteId;
        this.requisiteName = requisiteName;
    }

    public int getPreRequisiteId() {
        return PreRequisiteId;
    }

    public void setPreRequisiteId(int preRequisiteId) {
        PreRequisiteId = preRequisiteId;
    }

    public String getRequisiteName() {
        return requisiteName;
    }

    public void setRequisiteName(String requisiteName) {
        this.requisiteName = requisiteName;
    }

    public List<CourseEntity> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseEntity> courses) {
        this.courses = courses;
    }
}
