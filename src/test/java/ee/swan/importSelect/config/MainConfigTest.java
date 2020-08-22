package ee.swan.importSelect.config;

import ee.swan.importSelect.UseMyBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {MainConfig.class})
class MainConfigTest {

    @Autowired
    UseMyBean useMyBean;

    @Test
    public void testImportSelector() {
        useMyBean.printMsg();
    }
}