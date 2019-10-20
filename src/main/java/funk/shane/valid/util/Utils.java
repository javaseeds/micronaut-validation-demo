/**
 Copyright 2019 Michael Shane Funk - Javaseeds Consulting

 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in
 the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies 
 of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
 FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,  
 WHETHER  IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package funk.shane.valid.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import javax.inject.Singleton;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.micronaut.core.io.IOUtils;
import io.micronaut.core.io.ResourceResolver;
import io.micronaut.core.io.scan.ClassPathResourceLoader;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class Utils {
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
        resource = IOUtils.readText(
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