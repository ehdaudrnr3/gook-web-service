package com.gook.springboot.web.dto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestResponseDtoTest {

    @Test
    public void test() throws Exception {
        //given
        String name = "test";
        int amount = 1000;

        //when
        TestResponseDto dto = new TestResponseDto(name, amount);

        System.out.println("dto.getName() = " + dto.getName());
        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
