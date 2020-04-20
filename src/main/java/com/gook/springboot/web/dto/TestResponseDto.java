package com.gook.springboot.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class TestResponseDto {
    private String name;
    private int amount;

    public TestResponseDto(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }
}
