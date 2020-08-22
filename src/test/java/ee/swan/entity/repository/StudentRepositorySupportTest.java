package ee.swan.entity.repository;

import ee.swan.entity.Student;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StudentRepositorySupportTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentRepositorySupport studentRepositorySupport;


    @Test
    public void testQeurydsl() {
        //given
        String name = "세종대왕";
        studentRepository.save(new Student(name));

        List<Student> result = studentRepositorySupport.findByName(name);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getUserName()).isEqualTo(name);
    }
}