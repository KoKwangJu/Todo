package com.example.spartatodoapp.comment;

import java.util.concurrent.RejectedExecutionException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spartatodoapp.CommonResponseDto;
import com.example.spartatodoapp.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/comments")
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> postComment(@RequestBody CommentRequestDto commentRequestDTO, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentResponseDto responseDTO = commentService.createComment(commentRequestDTO, userDetails.getUser());

        return ResponseEntity.status(201).body(responseDTO);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommonResponseDto> putComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDTO, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            CommentResponseDto responseDTO = commentService.updateComment(commentId, commentRequestDTO, userDetails.getUser());
            return ResponseEntity.ok().body(responseDTO);
        } catch (RejectedExecutionException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }


    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommonResponseDto> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            commentService.deleteComment(commentId, userDetails.getUser());
            return ResponseEntity.ok().body(new CommonResponseDto("정상적으로 삭제 되었습니다.", HttpStatus.OK.value()));
        } catch (RejectedExecutionException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }
}
