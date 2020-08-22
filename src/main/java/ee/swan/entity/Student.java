package ee.swan.entity;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue
    @Column(name = "STUDENT_ID")
    private Long id;
    private String userName;
    private String grade;

    @JoinColumn(name = "SCHOOL_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private School school;



    public Student(String userName) {
        this.userName = userName;
    }
}
