package com.learn.taskManager.dtos;

import com.learn.taskManager.Entities.NoteEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDto {
    private int id;
    private String Title;
    private String description;
    private Date deadline;
    private boolean completed;
    private List<NoteEntity> notes;
}
