package ee.swan.importSelect;

import ee.swan.importSelect.annotation.EnableAutoModule;
import ee.swan.importSelect.config.AConfig;
import ee.swan.importSelect.config.BConfig;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        AnnotationAttributes attr = AnnotationAttributes.fromMap(
                annotationMetadata.getAnnotationAttributes(EnableAutoModule.class.getName(), false)
        );
        String value = attr.getString("value");
        if ("someValue".equals(value)) {
            return new String[] {AConfig.class.getName()};
        } else {
            return new String[] {BConfig.class.getName()};
        }

    }
}
