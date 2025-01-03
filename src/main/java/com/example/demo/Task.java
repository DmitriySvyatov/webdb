package com.example.demo;

import lombok.Data;
import lombok.experimental.FieldNameConstants;


@Data
@FieldNameConstants
public class Task {

    private Long id;

    private String title;

    private String description;

    private int priority;
}
