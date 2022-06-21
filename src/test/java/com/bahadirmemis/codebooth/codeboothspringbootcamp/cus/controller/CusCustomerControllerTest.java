package com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.controller;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.BaseTest;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.CodeboothSpringBootcampApplication;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.config.H2TestProfileJPAConfig;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.cus.dto.CusCustomerUpdateRequestDto;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.gen.response.GenRestResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {CodeboothSpringBootcampApplication.class, H2TestProfileJPAConfig.class})
@ActiveProfiles("test")
class CusCustomerControllerTest extends BaseTest {

    private static final String BASE_PATH = "/api/v1/customers";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void shouldFindAll() throws Exception {

        MvcResult result = mockMvc.perform(
                get(BASE_PATH).content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void shouldFindById() throws Exception {

        MvcResult result = mockMvc.perform(
                get(BASE_PATH + "/101").content("101").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);

    }

    @Test
    void shouldSave() throws Exception {

        String body = "{\n" +
                "  \"name\": \"john\",\n" +
                "  \"surname\": \"grant\",\n" +
                "  \"identityNo\": 10000000000,\n" +
                "  \"password\": \"xxxyyyzzz\"\n" +
                "}";

        MvcResult result = mockMvc.perform(
                post(BASE_PATH).content(body).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void shouldDelete() throws Exception {

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.delete(BASE_PATH + "/102").content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void shouldNotDeleteWhenIdDoesNotExist() throws Exception {

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.delete(BASE_PATH + "/9999").content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isInternalServerError()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertFalse(isSuccess);
    }

    @Test
    void shouldUpdate() throws Exception {

        CusCustomerUpdateRequestDto cusCustomerUpdateRequestDto = CusCustomerUpdateRequestDto.builder()
                .id(103L)
                .name("Bahadır")
                .surname("Memiş")
                .identityNo(12345678901L)
                .password("1231231234")
                .build();

        String body = objectMapper.writeValueAsString(cusCustomerUpdateRequestDto);

        MvcResult result = mockMvc.perform(
                put(BASE_PATH).content(body).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }
}