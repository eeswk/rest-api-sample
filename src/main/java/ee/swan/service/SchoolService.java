package ee.swan.service;

import ee.swan.entity.School;
import ee.swan.entity.Student;
import ee.swan.entity.repository.SchoolRepository;
import ee.swan.entity.repository.StudentRepository;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public void findStudentInfo() {
        School school = new School("백제고");
        schoolRepository.save(school);

        System.out.println(school.getName());

        Student student1 = new Student("의자");
        Student student2 = new Student("온조");
        Student student3 = new Student("무령");

        student1.setSchool(school);
        student2.setSchool(school);
        student3.setSchool(school);

        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);

        List<Student> students = studentRepository.findAll();

        students.stream().forEach(s -> {
            System.out.println(s.getUserName() + ", " + s.getSchool().getName());
        });

    }

    @Transactional
    public void findSchoolInfo() {
        School school = new School("신라고");
        school.registerStudent(new Student("신문"));
        school.registerStudent(new Student("유신"));
        school.registerStudent(new Student("선덕"));

        School school1 = new School("고구려고");
        school1.registerStudent(new Student("주몽"));
        school1.registerStudent(new Student("문덕"));
        school1.registerStudent(new Student("장수"));

        schoolRepository.saveAll(new HashSet<>(){{
            add(school);
            add(school1);
        }});

        List<School> schools = schoolRepository.findAll();
        schools.stream().forEach(s -> {
            System.out.println(s.toString());
        });

    }


}
