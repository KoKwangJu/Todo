package com.example.spartatodoapp.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spartatodoapp.todo.Todo;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
