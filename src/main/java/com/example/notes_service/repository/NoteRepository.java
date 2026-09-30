package com.example.notes_service.repository;

import com.example.notes_service.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByOwner(String owner);
}