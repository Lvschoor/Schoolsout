package entities;

import javax.persistence.*;
import java.time.LocalDate;

// class/entity Exam according given UML

@Entity
public class Exam {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Column(length = 2000)
    private String description;
    private LocalDate date;
    private int weight;
    private int total;
    @ManyToOne (cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Module module;

    public Exam() {
    }

    public Exam(String name) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Module getModule() {
        return module;
    }
    public void setModule(Module module) {
        this.module = module;
    }

}
