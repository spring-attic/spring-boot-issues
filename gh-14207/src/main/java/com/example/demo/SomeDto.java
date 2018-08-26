package com.example.demo;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@ValidDto
class SomeDto {
    private String firstName;
    private String lastName;
}
