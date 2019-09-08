package funk.shane.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;

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
        String body = client.toBlocking().retrieve("/api/v1/valid");
        assertNotNull(body);

        assertEquals("", body);
    }
}