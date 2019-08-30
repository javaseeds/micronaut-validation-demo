package funk.shane.util;

import java.util.Optional;

import io.micronaut.core.io.ResourceResolver;
import io.micronaut.core.io.scan.ClassPathResourceLoader;
import io.micronaut.jackson.ObjectMapperFactory;

public class Utils {
    private static final ObjectMapperFactory mapper = new ObjectMapperFactory();
    
    public static String readResource(final String fileName) {
        ClassPathResourceLoader loader = 
          new ResourceResolver().getLoader(ClassPathResourceLoader.class).get();
          Optional<String> resource = loader.getResourceAsStream("classpath:");
        return "";
// return FileUtils.readFileToString(new ClassPathResource("/com/springventuregroup/maestro/" + fileName, Utils.class.getClass()).getFile(),
// 			StandardCharsets.UTF_8);    
    }
}