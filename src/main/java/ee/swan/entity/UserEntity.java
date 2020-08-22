package ee.swan.entity;

import java.time.LocalDate;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
//@Table("tbl_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private Integer age;
    private LocalDate createAt;

    @Column(name = "role")
    @Enumerated(EnumType.ORDINAL)
    private UserRole role;


    public UserEntity(String userName, Integer age, UserRole role) {
        this.userName = userName;
        this.age = age;
        this.role = role;

    }

    @PrePersist
    public void beforeCreate() {
        createAt = LocalDate.now();
    }
}
