package org.spring.as.quickstarts.kitchensink;

import org.junit.jupiter.api.Test;
import org.spring.as.quickstarts.kitchensink.model.Member;
import org.spring.as.quickstarts.kitchensink.service.MemberRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberRegistrationIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRegistration memberRegistration;

    @Test
    public void testRegister() throws Exception {
        // Create a new member
        Member newMember = new Member();
        newMember.setName("Jane Doe");
        newMember.setEmail("jane@gmail.com");
        newMember.setPhoneNumber("2125551234");

        // Register the member using the service directly
        memberRegistration.register(newMember);

        // Assert the member was persisted with an ID
        assertNotNull(newMember.getId());

    }
}

