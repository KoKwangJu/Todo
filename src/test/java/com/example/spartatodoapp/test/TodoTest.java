package com.example.spartatodoapp.test;

import com.example.spartatodoapp.todo.Todo;
import com.example.spartatodoapp.todo.TodoRequestDto;
import com.example.spartatodoapp.todo.TodoResponseDto;

public interface TodoTest extends CommonTest {

    Long TEST_TODO_ID = 1L;
    String TEST_TODO_TITLE = "title";
    String TEST_TODO_CONTENT = "content";

    TodoRequestDto TEST_TODO_REQUEST_DTO = TodoRequestDto.builder()
        .title(TEST_TODO_TITLE)
        .content(TEST_TODO_CONTENT)
        .build();
    TodoResponseDto TEST_TODO_RESPONSE_DTO = TodoResponseDto.builder()
        .title(TEST_TODO_TITLE)
        .content(TEST_TODO_CONTENT)
        .build();
    Todo TEST_TODO = Todo.builder()
        .title(TEST_TODO_TITLE)
        .content(TEST_TODO_CONTENT)
        .build();
    Todo TEST_ANOTHER_TODO = Todo.builder()
        .title(ANOTHER_PREFIX + TEST_TODO_TITLE)
        .content(ANOTHER_PREFIX + TEST_TODO_CONTENT)
        .build();
}
