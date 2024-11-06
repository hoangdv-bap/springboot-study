package com.example.hdvdemojpa.controller;

import com.example.hdvdemojpa.entity.User;
import com.example.hdvdemojpa.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private User user;

    private List<User> userList;

    @BeforeEach
    void initData(){
        user = new User();
        userList = new ArrayList<>();
    }

    @Test
    // addUser success
    void addUser_validRequest_success() throws Exception {
        // GIVEN
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(user);

        Mockito.when(userService.addUser(ArgumentMatchers.any())).thenReturn(user);

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders
                .post("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(user.getId()));
    }

    @Test
    // getAllUser success
    void getAllUser_validRequest_success() throws Exception {
        // GIVEN
        ObjectMapper objectMapper = new ObjectMapper();
        String response = objectMapper.writeValueAsString(userList);

        Mockito.when(userService.getUsers()).thenReturn(userList);

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(response));

    }

    @Test
    // getAllUser success
    void getUser_validRequest_success() throws Exception {
        // GIVEN
        int id = user.getId();

        Mockito.when(userService.getUserById(id)).thenReturn(Optional.ofNullable(user));

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(user.getId()));

    }
}
