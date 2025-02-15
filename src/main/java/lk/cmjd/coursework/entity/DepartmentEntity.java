package lk.cmjd.coursework.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Departments")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name",unique = true)
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CourseEntity> courses;

    public DepartmentEntity() {
    }

    public DepartmentEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public DepartmentEntity(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
