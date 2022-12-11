package com.example.ttlcacheimplimentation.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Data
public class TTLObjectDto {

    @NotBlank
    private String value;
}
