package com.gook.springboot;

import com.gook.springboot.config.auth.SecurityConfigurerAdapter;
import com.gook.springboot.web.TestController;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TestController.class,
excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfigurerAdapter.class)
})
public class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "USER")
    public void test() throws Exception {
          String test= "test";

          mockMvc.perform(get("/test"))
                  .andExpect(status().isOk())
                  .andExpect(content().string(test));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testDto() throws Exception {
        String name = "test";
        int amount = 1000;

        mockMvc.perform(get("/test/dto")
                            .param("name", name)
                            .param("amount", String.valueOf(amount))
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is(name)))
        .andExpect(jsonPath("$.amount", is(amount)));
    }
}
