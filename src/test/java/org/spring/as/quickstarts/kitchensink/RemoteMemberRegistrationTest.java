package org.spring.as.quickstarts.kitchensink;

import org.junit.jupiter.api.Test;
import org.spring.as.quickstarts.kitchensink.model.Member;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RemoteMemberRegistrationTest {

    @Value("http://localhost:${local.server.port}/api/members")
    private String baseUrl;

    @Test
    public void testRegister() {
        // Create a new member
        Member newMember = new Member();
        newMember.setName("Jane Doe");
        newMember.setEmail("jane@mailinator.com");
        newMember.setPhoneNumber("2125551234");

        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the request entity
        HttpEntity<Member> request = new HttpEntity<>(newMember, headers);

        // Make the POST request
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.POST, request, String.class);

        // Assert the response status code and body
        assertEquals(200, response.getStatusCodeValue());
    }
}

