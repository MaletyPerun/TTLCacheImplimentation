package com.example.ttlcacheimplimentation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class CommandLine {

    private String count;
    @NotBlank
    private String strLine;
}
