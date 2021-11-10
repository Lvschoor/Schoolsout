package model;

import javax.persistence.*;
import java.util.List;

// class/entity Module according given UML

@Entity
public class Module {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Column(length = 2000)
    private String description;
    @ManyToOne(cascade = {CascadeType.MERGE})
    private Course course;
    @OneToMany(mappedBy = "module")
    private List<Exam> exams;

    public Module() {
    }

    public Module(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    @Override
    public String toString() {
        return "Module{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\''  +
                '}';
    }
}
