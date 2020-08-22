package ee.swan.entity.repository;

import ee.swan.entity.UserEntity;
import ee.swan.entity.UserRole;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

//    @Autowired
//    UserRepositoryImpl userRepositoryImpl;

    @Test
    public void testSave() {
        userRepository.save(new UserEntity("이순신", 20, UserRole.ADMIN));
        userRepository.save(new UserEntity("주몽", 30, UserRole.ADMIN));

        UserEntity user = userRepository.findByUserName("주몽");
        System.out.println("이름" + user.getUserName() + ", 나이: "+  user.getAge() + ", 권한: " + user.getRole() + ", 생성일 " + user.getCreateAt());
    }

    @Test
    public void testJPQLQeury() {
        userRepository.save(new UserEntity("이순신", 20, UserRole.ADMIN));
        userRepository.save(new UserEntity("주몽", 30, UserRole.ADMIN));
        userRepository.save(new UserEntity("김유신", 16, UserRole.USER));
        userRepository.save(new UserEntity("최영", 40, UserRole.ADMIN));
        userRepository.save(new UserEntity("강감찬", 21, UserRole.ADMIN));
        userRepository.save(new UserEntity("홍길동", 34, UserRole.ADMIN));

        List<UserEntity> resultList = userRepository.findAllLike("%신%");
        System.out.println("검색키워드의 검색수" + resultList.size());

        resultList.stream().forEach(System.out::println);
    }

    @Test
    public void testSummary() {
        userRepository.save(new UserEntity("이순신", 20, UserRole.ADMIN));
        userRepository.save(new UserEntity("주몽", 30, UserRole.ADMIN));
        userRepository.save(new UserEntity("김유신", 16, UserRole.USER));
        userRepository.save(new UserEntity("최영", 40, UserRole.ADMIN));
        userRepository.save(new UserEntity("강감찬", 21, UserRole.ADMIN));
        userRepository.save(new UserEntity("홍길동", 34, UserRole.ADMIN));

        int maxAge = userRepository.maxAge();
        int minAge = userRepository.minAge();

        System.out.println("maxAge:" + maxAge + ", minAge:" + minAge);

    }



}