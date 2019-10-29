package com.yisu.mock;

import com.yisu.common.constant.FwCommonConstants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @ClassName UserControllerTest
 * @Author xuyisu
 * @Description 模拟http测试
 * @Date 2019/10/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenGetUserInfoSuccess() throws Exception {
        String result = mockMvc.perform(get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(FwCommonConstants.SUCCESS))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenGetUserInfoFail() throws Exception {
        String result = mockMvc.perform(get("/user/name")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenDeleteUserInfoSuccess() throws Exception {
        String result = mockMvc.perform(delete("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(FwCommonConstants.SUCCESS))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenDeleteUserInfoFail() throws Exception {
        String result = mockMvc.perform(delete("/user/6")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(FwCommonConstants.FAIL))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }


    @Test
    public void whenAddUserInfoSuccess() throws Exception {
        String content = "{\"userName\":\"tom\",\"password\":\"123456\",\"sex\":\"男\"}";
        String result = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(FwCommonConstants.SUCCESS))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenAddUserInfoFail() throws Exception {
        String content = "{\"userName\":\"tom\",\"password\":null,\"sex\":\"男\"}";
        String result = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(FwCommonConstants.FAIL))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenUpdateUserInfoSuccess() throws Exception {
        String content = "{\"userName\":\"tom\",\"password\":\"123456\",\"sex\":\"男\",\"id\":\"1\"}";
        String result = mockMvc.perform(put("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(FwCommonConstants.SUCCESS))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenUpdateUserInfoFail() throws Exception {
        String content = "{\"userName\":\"tom\",\"password\":\"123456\",\"sex\":\"男\"}";
        String result = mockMvc.perform(put("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(FwCommonConstants.FAIL))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }


    @Test
    public void whenQuerySuccess() throws Exception {
        String content = "{\"userName\":\"tom\",\"password\":\"123456\",\"sex\":\"男\",\"id\":\"1\"}";
        String result = mockMvc.perform(
                post("/user/page").param("size", "15")
                         .param("page", "3")
                        .contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
                .andExpect(status().isOk()).andExpect(jsonPath("$.code").value(FwCommonConstants.SUCCESS))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenQueryFail() throws Exception {
        String content = "{\"userName\":\"tom\",\"password\":\"123456\",\"sex\":\"男\"}";
        String result = mockMvc.perform(
                post("/user/page").param("size", "15")
                        .param("page", "3")
                        .contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
                .andExpect(status().isOk())//.andExpect(jsonPath("$.code").value(FwCommonConstants.FAIL))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }
}
