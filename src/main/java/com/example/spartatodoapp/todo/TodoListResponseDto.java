package com.example.spartatodoapp.todo;

import java.util.List;

import com.example.spartatodoapp.user.UserDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class TodoListResponseDto {
    private UserDto user;
    private List<TodoResponseDto> todoList;

    public TodoListResponseDto(UserDto user, List<TodoResponseDto> todoList) {
        this.user = user;
        this.todoList = todoList;
    }
}
