package com.learn.taskManager.Controllers;

import com.learn.taskManager.Entities.NoteEntity;
import com.learn.taskManager.dtos.CreateNoteDto;
import com.learn.taskManager.dtos.CreateNoteResponseDto;
import com.learn.taskManager.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NotesController {

    private NoteService noteService;

    public NotesController(NoteService noteService){
        this.noteService=noteService;
    }
    @GetMapping("")
    public ResponseEntity<List<NoteEntity>> getNote(@PathVariable("taskId") Integer taskId){
        var notes = noteService.getNotesForTask(taskId);
        return ResponseEntity.ok(notes);
    }

    @PostMapping("")
    public ResponseEntity<CreateNoteResponseDto> addNote(@PathVariable("taskId") Integer taskId , @RequestBody CreateNoteDto body){
        var note = noteService.addNoteForTask(taskId ,body.getTitle(),body.getBody());
        return ResponseEntity.ok(new CreateNoteResponseDto(taskId , note));
    }
}
