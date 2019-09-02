package funk.shane.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import javax.inject.Singleton;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import org.apache.commons.io.IOUtils;

import io.micronaut.core.io.ResourceResolver;
import io.micronaut.core.io.scan.ClassPathResourceLoader;

@Singleton
public class Utils {
  private static final Logger log = LoggerFactory.getLogger(Utils.class);
  private static final ObjectMapper mapper = 
    new ObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

  public static String readResource(final String fileName) {
    log.info("fileName: {}", fileName);
    ClassPathResourceLoader loader = new ResourceResolver().getLoader(ClassPathResourceLoader.class).get();
    Optional<InputStream> is = loader.getResourceAsStream("classpath:funk/shane/pojo/" + fileName);

    String resource = null;
    if (is.isPresent()) {
      try {
        // USING Apache IOUtils to write file content to String (disadvantage: extra dependency)
        //resource = IOUtils.toString(is.get(), StandardCharsets.UTF_8);

        // USING Micronaut, no extra dependency, but more JDK boilerplate (probably what's under the Apache dep hood)
        resource = io.micronaut.core.io.IOUtils.readText(
          new BufferedReader(new InputStreamReader(is.get(), StandardCharsets.UTF_8)));
      } catch (IOException e) {
        log.error("bad juju: {}", e.getMessage(), e);
      }
    } else {
      log.warn("The load from resource did not work");
    }

    return resource;
  }

  public static <T> T getClassFromJsonResource(Class<T> klass, String jsonFile) {
    log.info("jsonFile [{}] for Class [{}]", jsonFile, klass.getSimpleName());
    T object = null;

    try {
      object = mapper.readValue(readResource(jsonFile), klass);
    } catch (IOException e) {
      log.error("bad juju reading file: [{}] {}", jsonFile, e.getMessage(), e);
    }

    return object;
  }
}