package ee.swan.exception.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorDetail {
    private String message;
    private int code;
    private Date timeStamp;
}
