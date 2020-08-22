package ee.swan.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SchoolServiceTest {

    @Autowired
    SchoolService schoolService;

    @Test
    public void testInfo() {
        schoolService.findStudentInfo();
    }

    @Test
    public void testSchoolInfo() {
        schoolService.findSchoolInfo();
    }
}