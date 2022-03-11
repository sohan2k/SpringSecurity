package io.spring.springSecurity.Controller;

import io.spring.springSecurity.Repository.UserRepository;
import io.spring.springSecurity.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest
class UserControllerTest {
    @Autowired
    WebApplicationContext wac;

    @MockBean
    UserRepository userRepository;

    @MockBean
    UserService userService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc= MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
    }

    @Test
    void getUSer() throws Exception{

        mockMvc.perform(get("/api/user"))
                .andExpect(status().isOk());
    }

    @Test
    void getUserById() throws Exception{
        mockMvc.perform(get("/api/user/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUser() throws Exception {
        mockMvc.perform(delete("/api/user/1")
                .header("Api-Key", "user1").header("Api-Secret", "123"))
                .andExpect(status().isOk());

        //97df0c39-90c4-4ae0-b663-453e8e19c311
    }

    @Test
    void deleteWithHttpBasic() throws Exception {
        mockMvc.perform(delete("/api/user/1")
                .with(httpBasic("user1","123")))
                .andExpect(status().is2xxSuccessful());
    }
}