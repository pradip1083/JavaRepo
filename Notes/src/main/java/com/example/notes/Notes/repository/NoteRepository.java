/**
 * 
 */
package com.example.notes.Notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.notes.Notes.model.Note;

/**
 * @author prnikam
 *
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

}
