package com.example.spartatodoapp.todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spartatodoapp.CommonResponseDto;
import com.example.spartatodoapp.user.UserDto;
import com.example.spartatodoapp.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/todos")
@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponseDto> postTodo(@RequestBody TodoRequestDto todoRequestDTO, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        TodoResponseDto responseDTO = todoService.createTodo(todoRequestDTO, userDetails.getUser());

        return ResponseEntity.status(201).body(responseDTO);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<CommonResponseDto> getTodo(@PathVariable Long todoId) {
        try {
            TodoResponseDto responseDTO = todoService.getTodoDto(todoId);
            return ResponseEntity.ok().body(responseDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @GetMapping
    public ResponseEntity<List<TodoListResponseDto>> getTodoList() {
        List<TodoListResponseDto> response = new ArrayList<>();

        Map<UserDto, List<TodoResponseDto>> responseDTOMap = todoService.getUserTodoMap();

        responseDTOMap.forEach((key, value) -> response.add(new TodoListResponseDto(key, value)));

        return ResponseEntity.ok().body(response);
    }


    @PutMapping("/{todoId}")
    public ResponseEntity<TodoResponseDto> putTodo(@PathVariable Long todoId, @RequestBody TodoRequestDto todoRequestDTO, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            TodoResponseDto responseDTO = todoService.updateTodo(todoId, todoRequestDTO, userDetails.getUser());
            return ResponseEntity.ok().body(responseDTO);
        } catch (RejectedExecutionException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new TodoResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }


    @PatchMapping("/{todoId}/complete")
    public ResponseEntity<TodoResponseDto> completeTodo(@PathVariable Long todoId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            TodoResponseDto responseDTO = todoService.completeTodo(todoId, userDetails.getUser());
            return ResponseEntity.ok().body(responseDTO);
        } catch (RejectedExecutionException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new TodoResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }
}