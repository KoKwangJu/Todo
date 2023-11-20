package com.example.spartatodoapp.todo;

import java.util.List;

import com.example.spartatodoapp.user.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoListResponseDTO {
    private UserDTO user;
    private List<TodoResponseDTO> todoList;

    public TodoListResponseDTO(UserDTO user, List<TodoResponseDTO> todoList) {
        this.user = user;
        this.todoList = todoList;
    }
}
