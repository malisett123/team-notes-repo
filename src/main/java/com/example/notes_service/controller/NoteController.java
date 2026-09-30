package com.example.notes_service.controller;

import com.example.notes_service.entity.Note;
import com.example.notes_service.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<Note> createNote(
            @Valid @RequestBody CreateNoteRequest request) {

        Note note = new Note(
                request.title(),
                request.content(),
                request.owner()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(noteService.createNote(note));
    }

    @GetMapping
    public List<Note> getNotes(
            @RequestParam(required = false) String owner) {

        if (owner != null && !owner.isBlank()) {
            return noteService.getNotesByOwner(owner);
        }

        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id);
    }

    @PutMapping("/{id}")
    public Note updateNote(
            @PathVariable Long id,
            @Valid @RequestBody CreateNoteRequest request) {

        Note updatedNote = new Note(
                request.title(),
                request.content(),
                request.owner()
        );

        return noteService.updateNote(id, updatedNote);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
    }
}