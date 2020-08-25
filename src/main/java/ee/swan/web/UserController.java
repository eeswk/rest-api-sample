package ee.swan.web;

import ee.swan.model.User;
import ee.swan.web.dto.UserVO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private static List<User> userList = new ArrayList<>();
    {
        userList.add(new User(1, "test01", "test1@naver.com", "test1", LocalDate.of(2020, 8, 1)));
        userList.add(new User(2, "test02", "test2@naver.com", "test2", LocalDate.of(2020, 8, 2)));
        userList.add(new User(3, "test03", "test3@naver.com", "test3", LocalDate.of(2020, 8, 3)));
    }

    @RequestMapping("/user/{userNo}")
    public ResponseEntity<User> getUserInfo(@PathVariable int userNo) {
        User user = userList.get(userNo);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping("/user")
    public ResponseEntity<List<User>> getUserList() {
        Map<String, Object> resultList = new HashMap<>();
        resultList.put("result", userList);
        return new ResponseEntity(resultList, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/regist", method = RequestMethod.POST)
    public ResponseEntity<?> registUser(@Validated @RequestBody UserVO userVo) {
        System.out.println("controller vo check::" + userVo.toString());
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
