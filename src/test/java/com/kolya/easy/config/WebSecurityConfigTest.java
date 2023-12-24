package com.kolya.easy.config;

import com.kolya.easy.model.AuthenticationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"server.port=0"})
public class WebSecurityConfigTest {

    @Autowired
    private Environment environment;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void whenGetAuthSignup_then200Response() {
        String port = environment.getProperty("local.server.port");

        AuthenticationRequest authenticationRequest = new AuthenticationRequest(); //Replace UserRegistrationDTO with your actual DTO class
        authenticationRequest.setUsername("testUser");
        authenticationRequest.setPassword("testUserPassword");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AuthenticationRequest> request =
                new HttpEntity<>(authenticationRequest, headers);

        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity("http://localhost:" + port + "/auth/signup", request, String.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode()); //Assuming that the creation returns a CREATED (201) status code.
    }


    @Test
    public void whenGetUnauthotizedEndpoint_then401Response() {
        String port = environment.getProperty("local.server.port");
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/authenticated-endpoint", String.class);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }
}