package com.learn.taskManager.dtos;

import com.learn.taskManager.Entities.NoteEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateNoteResponseDto {
    private Integer taskId;
    private NoteEntity note;
}
