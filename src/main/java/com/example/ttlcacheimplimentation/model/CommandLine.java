package com.example.ttlcacheimplimentation.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CommandLine {

    @NotBlank
    private String strLine;
}
