package ee.swan.web;

import ee.swan.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;



@SpringBootTest
class UserControllerTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testGetUserById() {
        String url = "http://localhost:8080/user/1";
        User user = restTemplate.getForObject(url, User.class);
        System.out.println("등록일:" + user.getRegDate() + ", " + user.getUname() + ", " + user.getUserId());
    }

    @Test
    public void testGetUsers() {
        String url = "http://localhost:8080/user";
        ResponseEntity<Map<String, List<User>>> result =
                restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, List<User>>>() {});

        Map<String, List<User>> tempMap = result.getBody();
        ArrayList<User> resultArr = (ArrayList<User>) tempMap.get("result");
        for (User user : resultArr) {
            System.out.println(user.getUname());
        }
    }

}