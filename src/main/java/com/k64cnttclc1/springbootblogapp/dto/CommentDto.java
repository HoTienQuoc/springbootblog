package com.k64cnttclc1.springbootblogapp.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty(message = "Email not empty")
    private String email;
    @NotEmpty(message = "Content not empty")
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
