package ee.swan.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int userNo;
    private String userId;
    private String email;
    private String uname;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate regDate;


}
