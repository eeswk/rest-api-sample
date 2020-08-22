package ee.swan.entity.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import ee.swan.entity.QStudent;
import ee.swan.entity.Student;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static ee.swan.entity.QStudent.student;


@Repository
public class StudentRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public StudentRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(Student.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Student> findByName(String name) {
        return jpaQueryFactory.selectFrom(student)
                .where(student.userName.eq(name))
                .fetch();
    }
}
