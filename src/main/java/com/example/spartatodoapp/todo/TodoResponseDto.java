package com.example.spartatodoapp.todo;

import java.time.LocalDateTime;

import com.example.spartatodoapp.CommonResponseDto;
import com.example.spartatodoapp.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class TodoResponseDto extends CommonResponseDto {
    private Long id;
    private String title;
    private String content;
    private Boolean isCompleted;
    private UserDto user;
    private LocalDateTime createDate;

    public TodoResponseDto(String msg, Integer statusCode) {
        super(msg, statusCode);
    }

    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.isCompleted = todo.getIsCompleted();
        this.user = new UserDto(todo.getUser());
        this.createDate = todo.getCreateDate();
    }
}
