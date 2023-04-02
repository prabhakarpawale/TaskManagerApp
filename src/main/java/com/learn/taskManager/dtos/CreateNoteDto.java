package com.learn.taskManager.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateNoteDto {
    String title;
    String body;
}
