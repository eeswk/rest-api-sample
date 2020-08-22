package ee.swan.conditional.config;

import ee.swan.conditional.MsgBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnditionalMainConfig {
    @Autowired
    MsgBean msgBean;
}
