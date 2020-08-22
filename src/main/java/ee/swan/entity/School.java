package ee.swan.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class School {

    @Id
    @GeneratedValue
    @Column(name = "SCHOOL_ID")
    private Long id;

    private String name;
    private String address;
    private String telNumber;

    @OneToMany(mappedBy = "school")
    private Set<Student> students;

    public School(String name) {
        this.name = name;
    }

    public void registerStudent(Student student) {
        if (students == null) {
            students = new HashSet<>();
        }
        students.add(student);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("School{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", telNumber='").append(telNumber).append('\'');
        sb.append(", students=").append(students);
        sb.append('}');
        return sb.toString();
    }
}
