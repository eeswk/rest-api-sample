package ee.swan.importSelect;

import ee.swan.importSelect.config.MainConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ImportSelctApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        UseMyBean bean = context.getBean(UseMyBean.class);
        bean.printMsg();
    }
}
