package com.ltc.todo.config;


import com.ltc.todo.dto.requestDto.TaskRequestDto;
import com.ltc.todo.entity.Task;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var mapper = new ModelMapper();
        mapper.createTypeMap(TaskRequestDto.class, Task.class)
                .addMapping(src->null, Task::setId);
        return mapper;
    }

}
