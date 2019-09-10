package funk.shane.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import funk.shane.pojo.Person;
import funk.shane.util.Utils;
import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import lombok.extern.slf4j.Slf4j;

/**
 * Important to note the needed build dependencies
 * https://micronaut-projects.github.io/micronaut-test/latest/guide/index.html#junit5
 */
@MicronautTest
@Slf4j
public class ValidationControllerTest {
    private static EmbeddedServer server;
    private static HttpClient client;

    @BeforeAll
    public static void setup() {
        server = ApplicationContext.run(EmbeddedServer.class);
        client = server.getApplicationContext()
                 .createBean(HttpClient.class, server.getURL());
    }

    @AfterAll
    public static void tearDown() {
        if(server != null) {
            server.stop();
        }

        if(client != null) {
            client.stop();
        }
    }

    @Test
    public void testHappyPath() {
        final Person person = Utils.getClassFromJsonResource(Person.class, "person-1.json");
        log.info("test person: {}", person);

        HttpRequest<Person> request = HttpRequest.POST("/api/v1/valid", person);

        String body = client.toBlocking().retrieve(request);
        assertNotNull(body);

        // using AssertJ here
        assertThat(body).startsWith("Person ");
        assertThat(body).endsWith(" was vaild");
    }
}