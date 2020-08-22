package ee.swan.entity.repository;

import ee.swan.entity.UserEntity;
import java.util.List;

public interface UserRepositoryCustom {
    List<UserEntity> findAllLike(String keyword);

    int maxAge();

    int minAge();
}
