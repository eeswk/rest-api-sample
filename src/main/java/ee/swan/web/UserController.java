package ee.swan.web;

import ee.swan.config.annotation.ApiLoginAuth;
import ee.swan.config.annotation.UserAuth;
import ee.swan.exception.UserNotFoundException;
import ee.swan.model.User;
import ee.swan.service.UserService;
import ee.swan.web.dto.UserDto;
import ee.swan.web.dto.UserVO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    private static List<User> userList = new ArrayList<>();
    {
        userList.add(new User(1, "test01", "test1@naver.com", "test1", LocalDate.of(2020, 8, 1)));
        userList.add(new User(2, "test02", "test2@naver.com", "test2", LocalDate.of(2020, 8, 2)));
        userList.add(new User(3, "test03", "test3@naver.com", "test3", LocalDate.of(2020, 8, 3)));
    }

    @ApiLoginAuth
    @RequestMapping("/user/{userNo}")
    public ResponseEntity<User> getUserInfo(@PathVariable int userNo, UserDto userDto) {
        log.info("userName=>"+ userDto.getUserName());
        User user = userList.get(userNo);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @UserAuth
    @RequestMapping("/user")
    public ResponseEntity<List<User>> getUserList(UserDto userDto, HttpServletRequest request) {
        log.info("userDto= {}", userDto);
        log.info("rquest", request.getAttribute("userName"));
        Map<String, Object> resultList = new HashMap<>();
        resultList.put("result", userList);
        return new ResponseEntity(resultList, HttpStatus.OK);
    }


    @RequestMapping(value = "/user/regist", method = RequestMethod.POST)
    public ResponseEntity<?> registUser(@Validated @RequestBody UserVO userVo) {
        System.out.println("controller vo check::" + userVo.toString());
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{userName}", method = RequestMethod.GET)
    public ResponseEntity<?> findByUserOne(@PathVariable("userName") String userName) {
        UserVO user = userService.findByOneUserName(userName);
        if (user == null) {
            throw new UserNotFoundException("user not found");
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }
}
