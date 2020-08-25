package ee.swan.web.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO implements Serializable {
    private String id;
    @NotNull(message = "userName 필드가 null입니다.")
    private String userName;
    private String email;

}
