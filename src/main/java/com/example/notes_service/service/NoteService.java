package com.example.notes_service.service;

import com.example.notes_service.entity.Note;
import com.example.notes_service.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public List<Note> getNotesByOwner(String owner) {
        return noteRepository.findByOwner(owner);
    }

    public Note getNoteById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() ->
                        new NoteNotFoundException("Note not found with id: " + id));
    }

    public Note updateNote(Long id, Note updatedNote) {
        Note existingNote = getNoteById(id);

        existingNote.setTitle(updatedNote.getTitle());
        existingNote.setContent(updatedNote.getContent());
        existingNote.setOwner(updatedNote.getOwner());

        return noteRepository.save(existingNote);
    }

    public void deleteNote(Long id) {
        Note existingNote = getNoteById(id);
        noteRepository.delete(existingNote);
    }
}