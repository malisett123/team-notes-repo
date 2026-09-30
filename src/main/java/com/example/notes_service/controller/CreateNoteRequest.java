package com.example.notes_service.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateNoteRequest(

        @NotBlank(message = "Title is required")
        @Size(max = 200, message = "Title must not exceed 200 characters")
        String title,

        @NotBlank(message = "Content is required")
        String content,

        @NotBlank(message = "Owner is required")
        @Size(max = 100, message = "Owner must not exceed 100 characters")
        String owner
) {
}