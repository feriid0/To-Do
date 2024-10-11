package com.ltc.todo.dto.responseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskResponseDto {

    private Long id;

    private String title;
    private String description;
    private String status;

}
