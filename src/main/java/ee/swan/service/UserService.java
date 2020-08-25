package ee.swan.service;

import ee.swan.entity.UserEntity;
import ee.swan.entity.repository.UserRepository;
import ee.swan.exception.UserNotFoundException;
import ee.swan.web.dto.UserVO;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserVO findByOneUserName(String userName) {
        UserEntity userEntity = userRepository.findByUserName(userName);
        if(userEntity == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        userVO.setId(String.valueOf(userEntity.getId()));
        userVO.setUserName(userEntity.getUserName());
        return userVO;

    }
}
