package com.example.spartatodoapp.comment;

import com.example.spartatodoapp.CommonResponseDto;
import java.time.LocalDateTime;
import com.example.spartatodoapp.user.UserDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentResponseDto extends CommonResponseDto {
    private Long id;
    private String text;
    private UserDto user;
    private LocalDateTime createDate;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.text = comment.getText();
        this.user = new UserDto(comment.getUser());
        this.createDate = comment.getCreateDate();
    }
}
