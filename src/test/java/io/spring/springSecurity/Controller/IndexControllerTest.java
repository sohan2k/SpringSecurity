package io.spring.springSecurity.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class IndexControllerTest {
    @Autowired
    WebApplicationContext webc;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc= MockMvcBuilders
                .webAppContextSetup(webc)
                .apply(springSecurity())
                .build();
    }
    //wee tell spring that we have a user and it just satisfy and return result
    @WithMockUser("user1")
    @Test
    void getUser() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk());

    }
    //httpbasic check the username and password and return )
    @Test
    void getUserWithHttpBasic() throws Exception {
        mockMvc.perform(get("/user").with(httpBasic("user1","1234")))
                .andExpect(status().isOk());

    }

}