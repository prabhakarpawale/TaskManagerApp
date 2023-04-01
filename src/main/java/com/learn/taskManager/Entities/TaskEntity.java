package com.learn.taskManager.Entities;
import lombok.Data;

import java.util.*;

@Data
public class TaskEntity {
    private int id;
    private String Title;
    private String description;
    private Date deadline;
    private boolean completed;

}
