package com.example.spartatodoapp.comment;

import java.time.LocalDateTime;

import com.example.spartatodoapp.CommonResponseDto;
import com.example.spartatodoapp.todo.Todo;
import com.example.spartatodoapp.user.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentResponseDTO extends CommonResponseDto {
    private Long id;
    private String text;
    private UserDTO user;
    private LocalDateTime createDate;

    public CommentResponseDTO(Comment comment) {
        this.id = comment.getId();
        this.text = comment.getText();
        this.user = new UserDTO(comment.getUser());
        this.createDate = comment.getCreateDate();
    }
}
