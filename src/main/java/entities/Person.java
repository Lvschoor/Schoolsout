package entities;

import javax.persistence.*;
import java.util.List;

// class/entity Person according given UML

@Entity
public class Person {
    @Id
    @GeneratedValue
    private Integer id;
    private String firstname;
    private String familyname;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Course course;
    @ManyToMany (cascade = CascadeType.MERGE)
    private List<Course> courseHistory;


    public Person() {
    }

    public Person(String firstname, String familyname, Gender gender) {
        this.firstname = firstname;
        this.familyname = familyname;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Course> getCourseHistory() {
        return courseHistory;
    }

    public void setCourseHistory(List<Course> courseHistory) {
        this.courseHistory = courseHistory;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", familyname='" + familyname + '\'' +
                ", gender=" + gender +
                ", course=" + course +
                '}';
    }
}
